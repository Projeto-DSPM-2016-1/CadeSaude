package dspm.dc.ufc.br.cadesaude;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ListView;
=======
import android.widget.RatingBar;
>>>>>>> b4fa37ab88db5186e451dfa7beb8ee4229b99424
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PostoActivity extends AppCompatActivity {

    AlertDialog.Builder dialogBuilder;
    ArrayAdapter<String> adapter;
    ArrayList<String> array;
    LayoutInflater inflater;
<<<<<<< HEAD
    ListView listView ;
    Button addComent;
=======
    TextView tv_posto_name;
    Button bt_submit;
    RatingBar rb_ratingbar;
>>>>>>> b4fa37ab88db5186e451dfa7beb8ee4229b99424


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posto);
        rb_ratingbar = (RatingBar) findViewById(R.id.rb_ratingBar);
        bt_submit = (Button) findViewById(R.id.bt_submit);

        tv_posto_name = (TextView) findViewById(R.id.tv_posto_name);

        int id = getIntent().getIntExtra("ID", 0);
        String name = getIntent().getStringExtra("NOME");
<<<<<<< HEAD
        tvPostoName.setText("Id: " + id + " - " + name);
=======

        tv_posto_name.setText("Id: " + id + " - " + name);
>>>>>>> b4fa37ab88db5186e451dfa7beb8ee4229b99424
    }

    public void listernerForRatingBar(){ // em qualquer mudança na rating bar, essa função será chamada
        rb_ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //tv_posto_name.setText(String.valueOf(rating));
            }
        });
    }

    public void onButtonClickListener(){ // No click do botão
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rb_ratingbar.getRating();
            }
        });
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
