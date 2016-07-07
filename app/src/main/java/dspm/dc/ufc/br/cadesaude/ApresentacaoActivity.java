package dspm.dc.ufc.br.cadesaude;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import dspm.dc.ufc.br.cadesaude.DAO.BDcore;
import dspm.dc.ufc.br.cadesaude.server.GetComentariosServer;
import dspm.dc.ufc.br.cadesaude.server.GetPostosServer;


public class ApresentacaoActivity extends AppCompatActivity {

    ProgressBar mProgress;
    public static final int NOTIFICATION_ID = 1; // cada notificação precisa de um número único

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        mProgress = (ProgressBar) findViewById(R.id.progressbar);


        //new GetPostosServer().execute();
        verificarPostosCompletos();
        new GetComentariosServer(this).execute();
    }

    private void verificarPostosCompletos(){
        // Se postoscompletos nao existir entao chamar gerenciarpostosdownload
        SharedPreferences sharedPreferences = getSharedPreferences(Statics.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        int comp = sharedPreferences.getInt(Statics.SP_POSTOSCOMPLETOS_KEY, 0);

        if(comp == 0)
        {
            new GetPostosServer(this, mProgress).execute();
        }
        else
        {
            mProgress.setProgress(100);
            Toast.makeText(this, "Postos salvos anteriormente" , Toast.LENGTH_LONG).show();
        }
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

    public void onClickZerarSharedPrefs(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(Statics.SHARED_PREFERENCES_NAME , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        BDcore bdCore = new BDcore(this);
        bdCore.truncateDB();
    }

    public void sendNotification(View view) {
        Intent intent = new Intent(this, PostoActivity.class); // intenção do usuário quando clicar na notificação, atualmente não ta fazendo nada
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_cast_on_0_light); // setar ícone pequeno
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true); // a notificação vai se auto cancelar, isso significa que a notifação vai desaparecer depois de clicado
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_media_play)); //setar ícone grande
        builder.setContentTitle("Veja os postos que melhoraram ou pioraram");
        builder.setContentText("Os postos da sua cidade mudaram bastante desde a última consulta");
        builder.setSubText("clica e confira");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }
}
