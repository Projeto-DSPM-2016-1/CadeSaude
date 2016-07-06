package dspm.dc.ufc.br.cadesaude;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PostoReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1;

    /**
     * Trata uma notificação enviada pela classe PostoActivity quando a avaliação de um posto for alterada
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_ID);
        Toast.makeText(context, "Notificação enviada!", Toast.LENGTH_SHORT).show();
    }

}
