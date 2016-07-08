package dspm.dc.ufc.br.cadesaude;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import dspm.dc.ufc.br.cadesaude.server.PostComentariosServer;

/**
 * Created by RH on 07/07/2016.
 */
public class DialogActivity extends AppCompatActivity {


    EditText et_nome;
    EditText et_titulo;
    EditText et_corpo;
    Bundle sendComentarioInfo;
    Intent postoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
    }

    /** Adicionar os comentários do usuário para ser colocado no array e no listview
     *  e depois ser adicionado no banco de dados
     * */
    public void adicionarComentatiosPosto(View view){

        et_nome = (EditText) findViewById(R.id.et_nome_comentario);
        et_titulo = (EditText) findViewById(R.id.et_titulo_comentario);
        et_corpo = (EditText) findViewById(R.id.et_corpo_comentario);

        //backing
        postoActivity = new Intent(DialogActivity.this,PostoActivity.class);

        /** Mandando para o server*/
        new PostComentariosServer().execute(et_nome.getText().toString()
                , et_titulo.getText().toString(),
                et_corpo.getText().toString());

        sendComentarioInfo = new Bundle();
        sendComentarioInfo.putInt("CommentDone",1);
        postoActivity.putExtras(sendComentarioInfo);
        setResult(1,postoActivity);

        finish();
    }

}
