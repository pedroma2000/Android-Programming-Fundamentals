package pt.ipp.estg.mysmssender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MyBradcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity = (MainActivity) context;
        if (intent.getAction() == activity.ACTION_SEND_SMS) {
            Toast.makeText(context, "SMS sent!", Toast.LENGTH_LONG).show();
        } else if (intent.getAction() == activity.ACTION_SMS_DELIVERED) {
            Toast.makeText(context, "SMS delivered!", Toast.LENGTH_LONG).show();
        }
    }

}
