package dspm.dc.ufc.br.cadesaude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PostoActivity extends AppCompatActivity {


    ArrayAdapter<String> adapter;
    ArrayList<String> array;
    LayoutInflater inflater;
    TextView tv_posto_name;
    Button bt_submit;
    RatingBar rb_ratingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posto);
        rb_ratingbar = (RatingBar) findViewById(R.id.rb_ratingBar);
        bt_submit = (Button) findViewById(R.id.bt_submit);

        tv_posto_name = (TextView) findViewById(R.id.tv_posto_name);

        int id = getIntent().getIntExtra("ID", 0);
        String name = getIntent().getStringExtra("NOME");

        tv_posto_name.setText("Id: " + id + " - " + name);
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
     *       IMPLEMENTAR ALGUMAS FUNÇÕES PARA RECUPERAR INFORMAÇÕES DO POSTO
     *   CORRETO DE ACORDO COM O QUE FOI CLICADO E USAR ESSAS INFORMAÇÕES NESSA ACTIVITY
     *   APÓS A DEFINIÇÃO DESSAS INFROMAÇÕES.....
     * */


}
