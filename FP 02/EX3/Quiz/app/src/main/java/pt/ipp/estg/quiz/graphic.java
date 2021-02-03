package pt.ipp.estg.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class graphic extends AppCompatActivity implements View.OnClickListener {

    private int correct;
    private int wrong;
    private double total;
    private BarChart bchart;
    private TextView mtextView3;
    private TextView mtextView4;

    private Button mbutton3;

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        mbutton3 = findViewById(R.id.button3);
        mtextView3 = findViewById(R.id.textView3);
        mtextView4 = findViewById(R.id.textView4);
        mImage = findViewById(R.id.imageView2);

        bchart = (BarChart) findViewById(R.id.chart2);

        correct = Integer.parseInt(getIntent().getStringExtra("CORRECT"));
        wrong = Integer.parseInt(getIntent().getStringExtra("WRONG"));
        total = Integer.parseInt(getIntent().getStringExtra("TOTAL"));

        percentage(correct, wrong, total);

        //set the graphic
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, correct));
        entries.add(new BarEntry(1f, wrong));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.6f); // set custom bar width
        bchart.setData(data);
        bchart.setFitBars(true); // make the x-axis fit exactly all bars
        bchart.invalidate(); // refresh

        mbutton3.setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("GRAPHIC","onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("GRAPHIC","onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("GRAPHIC","onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("GRAPHIC","onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("GRAPHIC","onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("GRAPHIC","onDestroy()");
    }

    private void percentage(int correct, int wrong, double total){
        mtextView3.setText("Percentagem correta: " + ((correct / total) * 100) + "%");
        mtextView4.setText("Percentagem errada: " + ((wrong / total) * 100) + "%");

        if(correct > 8){
            mImage.setImageResource(R.drawable.a);
        } else if(correct == 5){
            mImage.setImageResource(R.drawable.b);
        } else {
            mImage.setImageResource(R.drawable.f);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}