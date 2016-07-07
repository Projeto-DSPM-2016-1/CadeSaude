package dspm.dc.ufc.br.cadesaude;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PostoActivity extends AppCompatActivity {

    AlertDialog.Builder dialogBuilder;
    ArrayAdapter<String> adapter;
    ArrayList<String> array;
    LayoutInflater inflater;
    ListView listView ;
    Button addComent;
    TextView tv_posto_name;
    Button bt_submit;
    RatingBar rb_ratingbar;

    Button backHome;
    Intent apresentacaoActivity;

    private int index; // vai servir para alguma coisa ainda isso.....
    public void setIndex(int indexView){ this.index = indexView;}
    public int getIndex(){return index;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posto);

        // Seta as configurações da ActionBar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Posto");
        ab.setDisplayHomeAsUpEnabled(true);

        rb_ratingbar = (RatingBar) findViewById(R.id.rb_ratingBar);
        bt_submit = (Button) findViewById(R.id.bt_submit);

        tv_posto_name = (TextView) findViewById(R.id.tv_posto_name);

        int id = getIntent().getIntExtra("ID", 0);
        String name = getIntent().getStringExtra("NOME");

        tv_posto_name.setText("Id: " + id + " - " + name);

        listarComentatiosPosto(); // assim que cria já lista os comentários daquele posto.
    }

    // Seta ação do botão de voltar na ActionBar
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void listernerForRatingBar(){ // em qualquer mudança na rating bar, essa função será chamada
        rb_ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //tv_posto_name.setText(String.valueOf(rating));
                Intent intent = new Intent();
                intent.setAction("android.intent.action.ATUALIZA_AVALIACAO");
                sendBroadcast(intent);
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

    /** Adicionar os comentários do usuário para ser colocado no array e no listview
     *  e depois ser adicionado no banco de dados
     *  */
    public void adicionarComentatiosPosto(View view){

        dialogBuilder = new AlertDialog.Builder(this);
        final View comentarioView = inflater.inflate(R.layout.layout_dialog,null);
        final EditText comentarioEditText = (EditText) findViewById(R.id.et_novo_comentario);


        dialogBuilder.setView(comentarioView);
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    /** Mostrar os comentários de cada posto para ser adicionado no listview*/

    public void listarComentatiosPosto(){
        dialogBuilder = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        addComent = (Button) findViewById(R.id.bt_add);
        listView = (ListView) findViewById(R.id.list);

        array = new ArrayList<String>(){};

        array.add(0,"test");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,array);
        listView.setAdapter(adapter);

        /**
         *  pegar informações do posto, não vai ter ação ao clicar nos items...
         *
         * */


    }
    /** Desnecessaŕio....
     *
    // voltar tela principal
    public void backHome(View view){
        backHome = (Button) findViewById(R.id.bt_voltar);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apresentacaoActivity = new Intent(PostoActivity.
                        this,ApresentacaoActivity.class);
                startActivity(apresentacaoActivity);
                finish();
            }
        });

    }*/

}
