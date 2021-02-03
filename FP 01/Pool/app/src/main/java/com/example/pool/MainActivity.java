package com.example.pool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private LineChart chart;
    private BarChart bchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
        bchart = (BarChart) findViewById(R.id.chart2);
        chart = (LineChart) findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<Entry>();
        for (int i =0; i <10; i++) {
            entries.add(new Entry(i, (float) Math.random() ) );
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();

        Log.d("MAIN_ACTIVITY","onCreate()");
    }

    @Override
    public void onClick(View v) {


        List<Entry> entries = new ArrayList<Entry>();
        for (int i =0; i <30; i++) {
            entries.add(new Entry(i, (float) Math.random() ) );
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();

        /*
        if(findViewById(R.id.chart) !=null){
            View oldchart = findViewById(R.id.chart);
            ViewGroup parent = (ViewGroup) oldchart.getParent();
            parent.removeView(oldchart);
        }

        findViewById(R.id.chart2).setVisibility(View.VISIBLE);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        // gap of 2f
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));
        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        bchart.setData(data);
        bchart.setFitBars(true); // make the x-axis fit exactly all bars
        bchart.invalidate(); // refresh
        */
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
}