package dspm.dc.ufc.br.cadesaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dspm.dc.ufc.br.cadesaude.models.Posto;
import dspm.dc.ufc.br.cadesaude.server.GetPostosServer;

public class ApresentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        new GetPostosServer().execute();
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
}
