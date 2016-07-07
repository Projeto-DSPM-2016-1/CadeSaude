package dspm.dc.ufc.br.cadesaude;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dspm.dc.ufc.br.cadesaude.models.Comentario;

/**
 * Created by RH on 07/07/2016.
 */
public class DialogActivity extends Activity {

    AlertDialog.Builder dialogBuilder;
    ArrayList<Comentario> array;
    LayoutInflater inflater;
    int i = 1;
    Comentario comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
    }

    /** Adicionar os comentários do usuário para ser colocado no array e no listview
     *  e depois ser adicionado no banco de dados
     * */
    public void adicionarComentatiosPosto(View view){

        dialogBuilder = new AlertDialog.Builder(this);
        final View comentarioView = inflater.inflate(R.layout.layout_dialog,null);
        final EditText nomeComentarioEditText = (EditText) findViewById(R.id.et_nome_comentario);
        final EditText tituloComentarioEditText = (EditText) findViewById(R.id.et_titulo_comentario);
        final EditText corpoComentarioEditText = (EditText) findViewById(R.id.et_corpo_comentario);

        dialogBuilder.setView(comentarioView);
        dialogBuilder.setView(nomeComentarioEditText);
        dialogBuilder.setView(tituloComentarioEditText);
        dialogBuilder.setView(corpoComentarioEditText);

        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String nome = nomeComentarioEditText.getText().toString();
                final String titulo = tituloComentarioEditText.getText().toString();
                final String corpo = corpoComentarioEditText.getText().toString();

                comentario = new Comentario(i,nome, titulo, corpo);
                array.add(i, comentario);

                Intent intent = new Intent(DialogActivity.this, PostoActivity.class);
                intent.putExtra("ARRAY", array);
                i++;
                setResult(RESULT_OK, intent);
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

}
