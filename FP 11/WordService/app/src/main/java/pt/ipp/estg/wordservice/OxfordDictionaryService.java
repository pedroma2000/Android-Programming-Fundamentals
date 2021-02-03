package pt.ipp.estg.wordservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class OxfordDictionaryService extends IntentService {

    public static final String WORD_INPUT = "pt.ipp.estg.wordservice.WORD";
    public static final String WORD_OUTPUT = "pt.ipp.estg.wordservice.RESULT";

    private static HashMap<String, String> searches = new HashMap<String, String>();

    private String baseURL = "https://od-api.oxforddictionaries.com/api/v2/entries/";
    private String language = "en-gb";
    final String appId = "0fcd62e8";
    final String appKey = "ea9997baae0d4b86d0853a335518f666";

    public OxfordDictionaryService() {
        super("OxfordDictionaryService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String word = intent.getStringExtra(WORD_INPUT);
            String word_id = word.toLowerCase();
            String params = baseURL + language + "/" + word_id;

            if(!searches.containsKey(word_id)) {
                try {
                    URL url = new URL(params);

                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    urlConnection.setRequestProperty("Accept","application/json");
                    urlConnection.setRequestProperty("app_id",appId);
                    urlConnection.setRequestProperty("app_key",appKey);

                    // read the output from the server
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    Log.d("Test", "GOT A RESULT!");
                    searches.put(word_id, stringBuilder.toString());

                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(ResultReceiver.ACTION_RESPOND);
                    broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    broadcastIntent.putExtra(OxfordDictionaryService.WORD_OUTPUT, stringBuilder.toString());
                    sendBroadcast(broadcastIntent);

                } catch (Exception e) {
                    try {
                        throw e;
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else {
                String result = searches.get(word_id);
                Log.d("Test", "GOT A HIT ON HASHMAP!");
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(ResultReceiver.ACTION_RESPOND);
                broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                broadcastIntent.putExtra(OxfordDictionaryService.WORD_OUTPUT, result);
                sendBroadcast(broadcastIntent);
            }
        }
    }
}