package dspm.dc.ufc.br.cadesaude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PostoActivity extends AppCompatActivity {


    ArrayAdapter<String> adapter;
    ArrayList<String> array;
    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posto);

        TextView tvPostoName = (TextView) findViewById(R.id.tv_posto_name);

        int id = getIntent().getIntExtra("ID", 0);
        String name = getIntent().getStringExtra("NOME");

        tvPostoName.setText("Id: " + id + " - " + name);
    }

    /**
     *       IMPLEMENTAR ALGUMAS FUNÇÕES PARA RECUPERAR INFORMAÇÕES DO POSTO
     *   CORRETO DE ACORDO COM O QUE FOI CLICADO E USAR ESSAS INFORMAÇÕES NESSA ACTIVITY
     *   APÓS A DEFINIÇÃO DESSAS INFROMAÇÕES.....
     * */
}
