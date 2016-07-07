package dspm.dc.ufc.br.cadesaude.server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

import dspm.dc.ufc.br.cadesaude.Statics;
import dspm.dc.ufc.br.cadesaude.models.Comentario;

/**
 * Created by Thiago on 07/07/2016.
 */
public class PostComentariosServer extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        Integer posto_id = Integer.parseInt(params[0]);
        String nome = params[1];
        String titulo = params[2];
        String corpo = params[3];

        String jsonParam = createJsonParam(posto_id, nome, titulo, corpo);


        // Inserindo no servidor
        String jsonStr = null;

        String url = Statics.POST_COMENTARIOS_SERVER_URL + "?comentarioJson=" + jsonParam ;

        // Fazendo requisição para o web service pelo metodo estatico httpGet
        jsonStr = WebRequest.httpGet(url);

        Log.d("Response: ", "Servidor Inserir: " + jsonStr);

        // Monta a lista de postos
        Vector<Comentario> listaComentarios = parseJsonComentarios(jsonStr);
        for(int i = 0; i<listaComentarios.size(); i++)
        {
            Comentario c = listaComentarios.get(i);
            Log.d("Response" , "Comentario to String: " + c.toString());
        }

        if(listaComentarios == null)
        {
            Log.d("Response", "Lista postos = null");
            return null;
        }
        else
        {
            //TODO Insiro no SQLite
            //comentarioBD.preencherBanco(listaComentarios);
            return null;
        }

    }

    public String createJsonParam(Integer posto_id, String nome, String titulo, String corpo) {
        JSONObject json = new JSONObject();

        try {

            json.put("posto_id" , posto_id);
            json.put("nome" , nome);
            json.put("titulo" , titulo);
            json.put("corpo" , corpo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    // Metodo responsavel por quebrar o JSON em Comentarios
    private Vector<Comentario> parseJsonComentarios(String json){
        if (json != null)
        {
            try {

                Vector<Comentario> listarComentarios = new Vector<>();

                // Transforma a string JSON em objeto
                JSONObject jsonObj = new JSONObject(json);

                if(jsonObj.getInt("status") == 1)
                {

                    // Extrai o array results do objeto JSON
                    JSONArray comentariosJson = jsonObj.getJSONArray("results");

                    // Percorrendo todas os Postos
                    for (int i = 0; i < comentariosJson.length(); i++) {
                        // Extrai o i-esimo objeto
                        JSONObject pJson = comentariosJson.getJSONObject(i);

                        // Extrai as informações do objeto
                        int comentario_id = pJson.getInt("comentario_id");
                        int posto_id = pJson.getInt("posto_id");
                        String nome = pJson.getString("nome");
                        String titulo = pJson.getString("titulo");
                        String corpo = pJson.getString("corpo");
                        String horario = pJson.getString("horario");


                        // Adiciona o Posto obtido na lista de postos
                        listarComentarios.add(new Comentario(comentario_id, nome, titulo, corpo, horario, posto_id));
                    }

                }

                return listarComentarios;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "No data received from HTTP request");
            return null;
        }

    }
}
