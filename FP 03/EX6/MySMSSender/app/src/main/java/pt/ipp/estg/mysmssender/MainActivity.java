package pt.ipp.estg.mysmssender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_SELECT_PHONE_NUMBER = 1;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 100;

    public static final String ACTION_SEND_SMS = "pt.ipp.estg.SEND_SMS";
    public static final String ACTION_SMS_DELIVERED = "pt.ipp.estg.SMS_DELIVERED";

    private EditText myText;
    private Button sendButton;
    private String message;
    private SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = findViewById(R.id.editText);
        sendButton = findViewById(R.id.button);

        smsManager = SmsManager.getDefault();

        sendButton.setOnClickListener(this);

        IntentFilter filter = new IntentFilter(ACTION_SEND_SMS);
        filter.addAction(ACTION_SMS_DELIVERED);
        MyBradcastReciever smsReceiver = new MyBradcastReciever();
        this.registerReceiver(smsReceiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MAIN_ACTIVITY", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MAIN_ACTIVITY", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MAIN_ACTIVITY", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MAIN_ACTIVITY", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MAIN_ACTIVITY", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MAIN_ACTIVITY", "onDestroy()");
    }

    @Override
    public void onClick(View v) {
        message = myText.getText().toString();
        checkPermissions();
    }

    private void checkPermissions(){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            selectContact();
        } else {
            // You can directly ask for the permission.
            requestPermissions(this, new String[] { Manifest.permission.SEND_SMS },
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    private void requestPermissions(MainActivity mainActivity, String[] strings,
                                    int myPermissionsRequestSendSms) {

        if (ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        strings,
                        myPermissionsRequestSendSms);
            }

        }
    }

    private void selectContact() {
        // Start an activity for the user to pick a phone number from contacts
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                sendSMS(number);
            }
        }
    }

    private void sendSMS(String num){

        Intent sentIntent = new Intent(ACTION_SEND_SMS);
        PendingIntent piSentIntent = PendingIntent.getBroadcast(this, 0
                , sentIntent, 0);

        Intent deliveredIntent = new Intent(ACTION_SMS_DELIVERED);
        PendingIntent piDeliveredIntent = PendingIntent.getBroadcast(this, 0
                , deliveredIntent, 0);

        smsManager.sendTextMessage(num, null, message,piSentIntent, piDeliveredIntent);
    }

}