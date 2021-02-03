package pt.ipp.estg.sensorlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class DetailsSensor extends AppCompatActivity implements SensorEventListener {

    private LineChart chart;
    private int counter1 = 0;
    private int counter2 = 0;
    private int counter3 = 0;
    private int counter4 = 0;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private Sensor proximitySensor;
    private Sensor accelerationSensor;
    private Sensor gyroscopeSensor;
    private TextView lightSensorTextView;
    private TextView proximitySensorTextView;
    private TextView accelerationSensorTextView;
    private TextView gyroscopeSensorTextView;
    private List<Entry> entries = new ArrayList<Entry>();
    private List<Entry> entriesY = new ArrayList<Entry>();
    private List<Entry> entriesZ = new ArrayList<Entry>();
    private boolean showLightSensorGraph = false;
    private boolean showProximitySensorGraph = false;
    private boolean showAccelerationSensorGraph = false;
    private boolean showGyroscopeSensorGraph = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_sensor);

        this.chart = (LineChart) findViewById(R.id.chart);
        chart.setVisibility(View.GONE);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        lightSensorTextView = findViewById(R.id.lightView);
        lightSensorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart.setVisibility(View.VISIBLE);
                showLightSensorGraph = true;
                showProximitySensorGraph = false;
                showAccelerationSensorGraph = false;
                showGyroscopeSensorGraph = false;
                entries = new ArrayList<Entry>();
            }
        });
        lightSensorTextView.setText("Light Sensor: ");
        proximitySensorTextView = findViewById(R.id.proximityView);
        proximitySensorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart.setVisibility(View.VISIBLE);
                showLightSensorGraph = false;
                showProximitySensorGraph = true;
                showAccelerationSensorGraph = false;
                showGyroscopeSensorGraph = false;
                entries = new ArrayList<Entry>();
            }
        });
        proximitySensorTextView.setText("Proximity Sensor: ");
        accelerationSensorTextView = findViewById(R.id.accelerationView);
        accelerationSensorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart.setVisibility(View.VISIBLE);
                showLightSensorGraph = false;
                showProximitySensorGraph = false;
                showAccelerationSensorGraph = true;
                showGyroscopeSensorGraph = false;
                entries = new ArrayList<Entry>();
            }
        });
        accelerationSensorTextView.setText("Acceleration Sensor: ");
        gyroscopeSensorTextView = findViewById(R.id.gyroView);
        gyroscopeSensorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart.setVisibility(View.VISIBLE);
                showLightSensorGraph = false;
                showProximitySensorGraph = false;
                showAccelerationSensorGraph = false;
                showGyroscopeSensorGraph = true;
                entries = new ArrayList<Entry>();
            }
        });
        gyroscopeSensorTextView.setText("Gyro Sensor: ");


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightSensorTextView.setText("Light Sensor: " + event.values[0]);
            if(showLightSensorGraph) {
                entries.add(new Entry(counter1, event.values[0] ) );
                counter1 ++;
                LineDataSet dataSet = new LineDataSet(entries, "");
                LineData lineData = new LineData(dataSet);
                this.chart.setData(lineData);
                this.chart.invalidate();
            }
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximitySensorTextView.setText("Proximity Sensor: " + event.values[0]);
            if(showProximitySensorGraph) {
                entries.add(new Entry(counter2, event.values[0] ) );
                counter2 ++;
                LineDataSet dataSet = new LineDataSet(entries, "");
                LineData lineData = new LineData(dataSet);
                this.chart.setData(lineData);
                this.chart.invalidate();
            }
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerationSensorTextView.setText("Acceleration Sensor: " + event.values[0]);
            if(showAccelerationSensorGraph) {
                entries.add(new Entry(counter2, event.values[0] ) );
                counter2 ++;
                LineDataSet dataSet = new LineDataSet(entries, "");
                LineData lineData = new LineData(dataSet);
                this.chart.setData(lineData);
                this.chart.invalidate();
            }
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            gyroscopeSensorTextView.setText("Gyro Sensor: X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2]);
            if(showGyroscopeSensorGraph) {
                entries.add(new Entry(counter3, event.values[0] ) );
                entriesY.add(new Entry(counter3, event.values[1]));
                entriesZ.add(new Entry(counter3, event.values[2]));
                counter3 ++;
                LineDataSet dataSet = new LineDataSet(entries, "X");
                dataSet.setColor(Color.RED);
                dataSet.setCircleColor(Color.RED);
                LineDataSet dataSetY = new LineDataSet(entriesY, "Y");
                dataSetY.setColor(Color.BLUE);
                dataSetY.setCircleColor(Color.BLUE);
                LineDataSet dataSetZ = new LineDataSet(entriesZ, "Z");
                dataSetZ.setColor(Color.YELLOW);
                dataSetZ.setCircleColor(Color.YELLOW);
                LineData lineData = new LineData(dataSet, dataSetY, dataSetZ);
                this.chart.setData(lineData);
                this.chart.invalidate();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, 500000000, 500000000);
        sensorManager.registerListener(this, proximitySensor, 500000000, 500000000);
        sensorManager.registerListener(this, accelerationSensor, 500000000, 500000000);
        sensorManager.registerListener(this, gyroscopeSensor, 500000000, 500000000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}