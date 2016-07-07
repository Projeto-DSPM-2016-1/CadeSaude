package dspm.dc.ufc.br.cadesaude.server;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

import dspm.dc.ufc.br.cadesaude.DAO.ComentarioDAO;
import dspm.dc.ufc.br.cadesaude.Statics;
import dspm.dc.ufc.br.cadesaude.models.Comentario;
import dspm.dc.ufc.br.cadesaude.models.Posto;

/**
 * Created by Thiago on 07/07/2016.
 */
public class GetComentariosServer extends AsyncTask<Void, Void, Void> {

    ComentarioDAO comentarioBD;
    Context context;

    public GetComentariosServer(Context context){
        this.context = context;
        comentarioBD = new ComentarioDAO(context);
    }

    @Override
    protected Void doInBackground(Void... params) {

        String jsonStr = null;

        String url = Statics.GET_COMENTARIOS_SERVER_URL; // + "?from=" + from + "&to=" + to ;

        // Fazendo requisição para o web service pelo metodo estatico httpGet
        jsonStr = WebRequest.httpGet(url);

        Log.d("Response: ", "Servidor: " + jsonStr);

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
