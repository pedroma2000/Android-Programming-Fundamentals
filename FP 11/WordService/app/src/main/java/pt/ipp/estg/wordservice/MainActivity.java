package pt.ipp.estg.wordservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isWifiConn = false;
    private boolean isMobileConn = false;

    private ResultReceiver resultReceiver;

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter(ResultReceiver.ACTION_RESPOND);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        resultReceiver = new ResultReceiver();
        registerReceiver(resultReceiver, intentFilter);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void checkConnectivity () {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        for(Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn = networkInfo.isConnected();
            }
            if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn = networkInfo.isConnected();
            }
        }

        Log.d("Test", "Wifi connected: " + isWifiConn);
        Log.d("Test", "Mobile connected: " + isMobileConn);
    }

    @Override
    public void onClick(View v) {
        checkConnectivity();

        String word = editText.getText().toString();

        if(isWifiConn || isMobileConn){
            Intent startService = new Intent(this, OxfordDictionaryService.class);
            startService.putExtra(OxfordDictionaryService.WORD_INPUT, word);
            startService(startService);
        }
    }
}