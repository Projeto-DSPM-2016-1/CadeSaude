package dspm.dc.ufc.br.cadesaude;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostoActivity extends AppCompatActivity {

    AlertDialog.Builder dialogBuilder;
    ArrayAdapter<String> adapter;
    ArrayList<String> array;
    LayoutInflater inflater;
    ListView listView ;
    Button addComent;


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
     *    IMPLEMENTAR ALGUMAS FUNÇÕES PARA RECUPERAR INFORMAÇÕES DO POSTO
     *   CORRETO DE ACORDO COM O QUE FOI CLICADO E USAR ESSAS INFORMAÇÕES NESSA ACTIVITY
     *   APÓS A DEFINIÇÃO DESSAS INFROMAÇÕES.....
     * */



    /** Essa função vai pegar a lista de comentários de cada
     * posto e colocar no array e mostrar no listview
     * */
    public void listComments(){

    }

    // esta função vai adicionar um comentario na listview e de alguma forma esse
    // comentario deve ir para o banco e depois atualizado para o webservice
    public void addComment(){
        dialogBuilder = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        addComent = (Button) findViewById(R.id.bt_add);
        listView = (ListView) findViewById(R.id.list);

        array = new ArrayList<String>(){};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,array);
        listView.setAdapter(adapter);
    }

}
