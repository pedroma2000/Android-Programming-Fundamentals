package pt.ipp.estg.wordservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class ResultReceiver extends BroadcastReceiver {

    public static final String ACTION_RESPOND = "pt.ipp.estg.wordservice.REPONSE";

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity = (MainActivity) context;
        TextView output = (TextView) activity.findViewById(R.id.textview);
        String result = intent.getStringExtra(OxfordDictionaryService.WORD_OUTPUT);
        output.setText(result);
    }
}
