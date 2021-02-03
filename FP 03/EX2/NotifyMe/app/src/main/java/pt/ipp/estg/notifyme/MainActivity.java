package pt.ipp.estg.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);



        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MAIN_ACTIVITY","onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MAIN_ACTIVITY","onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MAIN_ACTIVITY","onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MAIN_ACTIVITY","onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MAIN_ACTIVITY","onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MAIN_ACTIVITY","onDestroy()");
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onClick(View v) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Foi Notificado!")
                .setContentText("Este é o texto da notificação")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if(v.getId() == R.id.button) {
            notificationManager.notify(1, builder.build());
        }

        if(v.getId() == R.id.button2){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.descarregar);

            builder.setContentTitle("Título Alterado!");
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap)).build();
            notificationManager.notify(1, builder.build());
        }

        if(v.getId() == R.id.button3){
            notificationManager.cancel(1);
        }
    }
}