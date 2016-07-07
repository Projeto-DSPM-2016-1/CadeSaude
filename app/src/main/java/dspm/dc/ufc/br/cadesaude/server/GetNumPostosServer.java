package dspm.dc.ufc.br.cadesaude.server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import dspm.dc.ufc.br.cadesaude.Statics;

/**
 * Created by Thiago on 06/07/2016.
 */
public class GetNumPostosServer extends AsyncTask<Void, Void, Void> {

    GerenciarPostosDownload gpd;

    public GetNumPostosServer(GerenciarPostosDownload gpd){
        this.gpd = gpd;
    }

    @Override
    protected Void doInBackground(Void... params) {

        String jsonStr = null;

        // Fazendo requisição para o web service pelo metodo estatico httpGet
        jsonStr = WebRequest.httpGet(Statics.GET_NUM_POSTOS_SERVER_URL);
        Log.d("Response: ", "Numero de Postos: " + jsonStr);


        try {
            // Transforma a string JSON em objeto
            JSONObject jsonObj = new JSONObject(jsonStr);
            int numPostos = jsonObj.getInt("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
