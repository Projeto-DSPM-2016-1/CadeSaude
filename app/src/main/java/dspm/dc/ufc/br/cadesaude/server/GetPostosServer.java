package dspm.dc.ufc.br.cadesaude.server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Vector;

import dspm.dc.ufc.br.cadesaude.Statics;
import dspm.dc.ufc.br.cadesaude.models.Posto;

import static java.lang.Integer.parseInt;

/**
 * Created by Thiago on 06/07/2016.
 */
public class GetPostosServer extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... params) {
        String jsonStr = null;

        // Fazendo requisição para o web service pelo metodo estatico httpGet
        jsonStr = WebRequest.httpGet(Statics.GET_POSTOS_SERVER_URL);

        Log.d("Response: ", "Servidor: " + jsonStr);

        // Monta a lista de postos
        Vector<Posto> listaPostos = parseJsonPostos(jsonStr);

        
        return null;
    }

    // Metodo responsavel por quebrar o JSON em Postos
    private Vector<Posto> parseJsonPostos(String json){
        if (json != null)
        {
            try {

                Vector<Posto> listarPostos = new Vector<>();

                // Transforma a string JSON em objeto
                JSONObject jsonObj = new JSONObject(json);

                if(jsonObj.getInt("status") == 1)
                {

                    // Extrai o array results do objeto JSON
                    JSONArray postosJson = jsonObj.getJSONArray("results");

                    // Percorrendo todas os Postos
                    for (int i = 0; i < postosJson.length(); i++) {
                        // Extrai o i-esimo objeto
                        JSONObject pJson = postosJson.getJSONObject(i);

                        // Extrai as informações do objeto
                        int id = pJson.getInt("posto_id");
                        double latitude = pJson.getDouble("latitude");
                        double longitude = pJson.getDouble("longitude");
                        String name = pJson.getString("nome_posto");
                        int codMunic = pJson.getInt("cod_municipio");
                        int codCnes = pJson.getInt("cod_cnes");
                        String endereco = pJson.getString("endereco_posto");
                        String bairro = pJson.getString("bairro_posto");
                        String cidade = pJson.getString("cidade_posto");
                        String telefone = pJson.getString("telefone_posto");
                        String dscEstrutFisicAmbiencia = pJson.getString("dsc_estrut_fisic_ambiencia");
                        String dscAdapDeficFisicIdosos = pJson.getString("dsc_adap_defic_fisic_idosos");
                        String dscEquipamentos = pJson.getString("dsc_equipamentos");
                        String dscNedicamentos = pJson.getString("dsc_medicamentos");

                        // Adiciona o Posto obtido na lista de postos
                        listarPostos.add(new Posto(id, latitude, longitude, name, codMunic, codCnes, endereco, bairro, cidade, telefone, dscEstrutFisicAmbiencia, dscAdapDeficFisicIdosos, dscEquipamentos, dscNedicamentos));
                    }

                }

                return listarPostos;
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
