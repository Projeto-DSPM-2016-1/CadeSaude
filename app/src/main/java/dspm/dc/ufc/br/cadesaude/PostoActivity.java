package dspm.dc.ufc.br.cadesaude;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dspm.dc.ufc.br.cadesaude.models.Comentario;

public class PostoActivity extends AppCompatActivity {

    AlertDialog.Builder dialogBuilder;
    ArrayAdapter<Comentario> adapter;
    ArrayList<Comentario> array;
    LayoutInflater inflater;
    ListView listView ;
    Button addComent;
    TextView tv_posto_name;
    Button bt_submit;
    RatingBar rb_ratingbar;
    int i = 0;

    static final int CONTACT_REQ = 1;
    private static final int NOTIFICATION_ID = 1;

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
        array = new ArrayList<Comentario>();

        listarComentatiosPosto(); // assim que cria já lista os comentários daquele posto.
       // adicionarComentatiosPosto();
        
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

    public void adicionarComentatiosPosto(View view){
        /*
        addComent = (Button) findViewById(R.id.bt_add);

        addComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/

                //Uri comentatiosUri = Uri.parse("content://comentatios");
                //Intent pickContactIntent = new Intent(Intent.ACTION_PICK, comentatiosUri);
                //pickContactIntent.setAction("br.ufc.dc.dspm.cadesaude.DIALOGACTIVITY");

                Intent commentsActivity = new Intent(PostoActivity.this,DialogActivity.class);
                startActivityForResult(commentsActivity, CONTACT_REQ);

                /*

                Bundle bundle = new Bundle();
                startActivityForResult(commentsActivity, CONTACT_REQ);
                */
          //  }
        //});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent	data)	{
        if (requestCode	==	CONTACT_REQ)	{
            //	Possible values:	RESULT_OK,	RESULT_CANCELED	or	RESULT_FIRST_USER
            if (resultCode	==	RESULT_OK)	{
                listarComentatiosPosto();
            }
        }
    }

    /** Mostrar os comentários de cada posto para ser adicionado no listview*/

    public void listarComentatiosPosto(){
        dialogBuilder = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        listView = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,array);
        listView.setAdapter(adapter);

        /**
         *  pegar informações do posto, não vai ter ação ao clicar nos items...
         *
         * */


    }

    public void recebeNotificacao(Bundle bundle){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_ID);
        Intent intent = new Intent(this, ApresentacaoActivity.class);
        startActivity(intent);
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

