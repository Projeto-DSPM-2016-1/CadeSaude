package dspm.dc.ufc.br.cadesaude.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Thiago on 06/07/2016.
 */
public class WebRequest {

    public static String httpGet(String link){
        try {
            //String link = (String) params[0];
            URL url = new URL(link);

            // perform connection and read content
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = null;
            String content = "";
            while ((data = reader.readLine()) != null) {
                content += data + "\n";
            }

            return content;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
        {
            Toast.makeText(context, "Conectado a Internet", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            Toast.makeText(context, "Não foi possível conectar a internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
