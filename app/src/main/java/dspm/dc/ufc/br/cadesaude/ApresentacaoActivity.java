package dspm.dc.ufc.br.cadesaude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dspm.dc.ufc.br.cadesaude.server.GetPostosServer;

public class ApresentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        new GetPostosServer().execute();
    }
}
