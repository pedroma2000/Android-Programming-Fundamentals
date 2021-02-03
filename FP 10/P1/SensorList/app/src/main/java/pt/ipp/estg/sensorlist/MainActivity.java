package pt.ipp.estg.sensorlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout);
        ll.setPadding(40,0,0,0);
        ll.setDividerPadding(40);

        Iterator<Sensor> sensors = sensorList.iterator();

        while(sensors.hasNext()){
            TextView tv1 = new TextView(this);
            tv1.setTextSize(25.f);
            tv1.setText(sensors.next().getName());
            tv1.setOnClickListener(this);
            ll.addView(tv1);
        }

    }

    @Override
    public void onClick(View v) {
        Intent startActivityIntent = new Intent(this, DetailsSensor.class);
        startActivity(startActivityIntent);
    }
}