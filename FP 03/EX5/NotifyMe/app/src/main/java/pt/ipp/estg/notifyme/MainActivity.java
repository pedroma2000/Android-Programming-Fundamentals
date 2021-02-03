package pt.ipp.estg.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
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
    private final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "01";
    static final String ACTION_CLICK = "pt.ipp.estg.CLICK";
    static final String ACTION_UPDATE = "pt.ipp.estg.UPDATE_NOTIFICATION";
    private Button mButton;
    private Button mButton2;
    private Button mButton3;
    private IntentFilter filter;
    private MyBroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);

        mButton2.setEnabled(false);
        mButton3.setEnabled(false);


        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);

        /**
         * Instanciar um reciever pra receber intents com
         * filtros especificos
         */
        br = new MyBroadcastReceiver();
        /**
         * instanciar Intentfilter para adicionar filtros
         */
        filter = new IntentFilter(ACTION_CLICK);
        filter.addAction(ACTION_UPDATE);

        /**
         * registar o reciever com os filtros
         */
        this.registerReceiver(br, filter);
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
        this.unregisterReceiver(br);
        Log.d("MAIN_ACTIVITY", "onDestroy()");
    }

    public void updateNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.descarregar);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Título Alterado!")
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Foi Notificado!")
                .setContentText("Este é o texto da notificação")
                .setPriority(NotificationCompat.PRIORITY_MAX);

        /*Intent para clicar na notificaçao e abrir a app*/
        Intent clickIntent = new Intent(this, MainActivity.class);
        clickIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent clickPendingIntent =
                PendingIntent.getActivity(this, 0, clickIntent, 0);
        builder.setAutoCancel(true);
        builder.setContentIntent(clickPendingIntent);

        /*intent e pending intent com action de dar update a notificação*/
        Intent updateNotification = new Intent();
        updateNotification.setAction(ACTION_UPDATE);
        PendingIntent piUpdateNotification =
                PendingIntent.getBroadcast(this, 0, updateNotification, 0);
        builder.addAction(R.drawable.ic_launcher_foreground,
                "Update Notification", piUpdateNotification);

        /*Desativa os botoes ao dar swipe ou clear*/
        Intent disableButtons = new Intent();
        disableButtons.setAction(ACTION_CLICK);
        PendingIntent piDisableButtons =
                PendingIntent.getBroadcast(this, 0, disableButtons, 0);
        builder.setDeleteIntent(piDisableButtons);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void deleteNotification() {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            createNotification();
            mButton2.setEnabled(true);
            mButton3.setEnabled(true);
        }

        if (v.getId() == R.id.button2) {
            updateNotification();
        }

        if (v.getId() == R.id.button3) {
            deleteNotification();
            mButton2.setEnabled(false);
            mButton3.setEnabled(false);
        }
    }

    public void disableButtons() {
        mButton2.setEnabled(false);
        mButton3.setEnabled(false);
    }
}