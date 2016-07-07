package dspm.dc.ufc.br.cadesaude.server;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

import dspm.dc.ufc.br.cadesaude.BD;
import dspm.dc.ufc.br.cadesaude.Statics;
import dspm.dc.ufc.br.cadesaude.models.Posto;
import dspm.dc.ufc.br.cadesaude.server.WebRequest;

/**
 * Created by Thiago on 06/07/2016.
 */
public class GetPostosServer extends AsyncTask<Void, Void, Void> {

    Context context;
    int numPostos;
    ProgressBar mProgress;
    BD postosBD;

    public GetPostosServer(Context context, ProgressBar mProgress){
        this.context = context;
        this.mProgress = mProgress;

        postosBD = new BD(context);
    }

    @Override
    protected Void doInBackground(Void... params) {
        getNumPostosServer();

        int range = 200;

        int ultimoPostoSalvoInicio = ultimoPostoSalvo() + 1;
        inicializarProgressbar(numPostos, ultimoPostoSalvoInicio);

        int ultimoPostoSalvoFinal = ultimoPostoSalvoInicio + range;

        while(ultimoPostoSalvoInicio < numPostos)
        {
            if (ultimoPostoSalvoFinal > numPostos) {
                ultimoPostoSalvoFinal = numPostos;
            }

            boolean res = getPostosServer(ultimoPostoSalvoInicio, ultimoPostoSalvoFinal);

            if(res) {
                salvarUltimoPosto(ultimoPostoSalvoFinal);
                ultimoPostoSalvoInicio = ultimoPostoSalvoFinal + 1;
                ultimoPostoSalvoFinal = ultimoPostoSalvoInicio + range;
                atualizarProgressBar(range);
            }
        }

        if(ultimoPostoSalvoInicio == numPostos)
        {
            salvarPostosCompletos();
        }

        return null;
    }

    private void getNumPostosServer(){
        String jsonStr = null;

        // Fazendo requisição para o web service pelo metodo estatico httpGet
        jsonStr = WebRequest.httpGet(Statics.GET_NUM_POSTOS_SERVER_URL);
        Log.d("Response: ", "Numero de Postos: " + jsonStr);


        try {
            // Transforma a string JSON em objeto
            JSONObject jsonObj = new JSONObject(jsonStr);
            numPostos = jsonObj.getInt("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void salvarPostosCompletos(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Statics.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Statics.SP_POSTOSCOMPLETOS_KEY, 1);
        editor.commit();
    }

    private int ultimoPostoSalvo(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Statics.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        int ultimoPostoSalvo = sharedPreferences.getInt(Statics.SP_ULTIMO_POSTO_SALVO_KEY, 0);

        return ultimoPostoSalvo;
    }

    private void salvarUltimoPosto(int ultimoPosto){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Statics.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Statics.SP_ULTIMO_POSTO_SALVO_KEY, ultimoPosto);
        editor.commit();
    }

    private boolean getPostosServer(int from , int to){
        String jsonStr = null;

        String url = Statics.GET_POSTOS_SERVER_URL + "?from=" + from + "&to=" + to ;

        // Fazendo requisição para o web service pelo metodo estatico httpGet
        jsonStr = WebRequest.httpGet(url);

        Log.d("Response: ", "Servidor: " + jsonStr);

        // Monta a lista de postos
        Vector<Posto> listaPostos = parseJsonPostos(jsonStr);

        if(listaPostos == null)
        {
            Log.d("Response", "Lista postos = null");
            return false;
        }
        else
        {
            //TODO Insiro no SQLite
            postosBD.preencherBanco(listaPostos);
            return true;
        }

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

    private void inicializarProgressbar(int max , int ini){
        mProgress.setMax(max);
        mProgress.setProgress(ini);
    }

    private void atualizarProgressBar(int increment){
        mProgress.incrementProgressBy(increment);
    }

}
