package pt.ipp.estg.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity = (MainActivity) context;
        if(intent.getAction() == MainActivity.ACTION_CLICK) {
            Log.d("DISABLE", "Disable Buttons!");
            activity.disableButtons();
        }
        if (intent.getAction() == MainActivity.ACTION_UPDATE) {
            activity.updateNotification();
            Log.d("UPDATE", "Update task!");
        }
    }
}
