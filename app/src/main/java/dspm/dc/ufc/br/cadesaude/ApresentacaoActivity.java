package dspm.dc.ufc.br.cadesaude;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import dspm.dc.ufc.br.cadesaude.DAO.BDcore;
import dspm.dc.ufc.br.cadesaude.server.GetPostosServer;


public class ApresentacaoActivity extends AppCompatActivity {

    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        mProgress = (ProgressBar) findViewById(R.id.progressbar);


        //new GetPostosServer().execute();
        verificarPostosCompletos();
    }

    private void verificarPostosCompletos(){
        // Se postoscompletos nao existir entao chamar gerenciarpostosdownload
        SharedPreferences sharedPreferences = getSharedPreferences(Statics.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        int comp = sharedPreferences.getInt(Statics.SP_POSTOSCOMPLETOS_KEY, 0);

        if(comp == 0)
        {
            new GetPostosServer(this, mProgress).execute();
        }
        else
        {
            mProgress.setProgress(100);
            Toast.makeText(this, "Postos salvos anteriormente" , Toast.LENGTH_LONG).show();
        }
    }

    public void onClickInfoPosto(View view){
        Intent intent = new Intent(this, PostoActivity.class);
        intent.setAction("br.ufc.dc.dspm.cadesaude.POSTOACTIVITY");
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    public void onClickMaps(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.setAction("br.ufc.dc.dspm.cadesaude.MAPSACTIVITY");
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    public void onClickZerarSharedPrefs(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(Statics.SHARED_PREFERENCES_NAME , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        BDcore bdCore = new BDcore(this);
        bdCore.truncateDB();
    }
}
