package dspm.dc.ufc.br.cadesaude.server;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import dspm.dc.ufc.br.cadesaude.Statics;

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

        // Monta a lista de subtopicos
        //listaSubtopicos = parseJsonMaterias(jsonStr);
        
        return null;
    }
}
