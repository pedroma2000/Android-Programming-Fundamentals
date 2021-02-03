package pt.ipp.estg.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView question;
    private EditText answer;
    private Button next;
    private int nQuestion;
    private int nCorrect;
    private int nWrong;
    private int a;
    private int b;
    private Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.textView2);
        answer = findViewById(R.id.editTextNumber);
        next = findViewById(R.id.button);

        //numero da questão
        nQuestion = 0;
        nCorrect = 0;
        nWrong = 0;

        r = new Random();
        a = 0;
        b = 0;
        a = r.nextInt((9) + 1);
        b = r.nextInt((9) + 1);
        question.setText(a + " + " + b + " = ");

        next.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MAIN_ACTIVITY", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        nCorrect = 0;
        nWrong = 0;
        nQuestion = 0;
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

        if(nQuestion == 10){
            Intent intent = new Intent(this, graphic.class);
            intent.putExtra("CORRECT", String.valueOf(nCorrect));
            intent.putExtra("WRONG", String.valueOf(nWrong));
            intent.putExtra("TOTAL", String.valueOf(nQuestion));
            startActivity(intent);
        }

        if(answer.getText().toString().matches("")){
            answer.setText("0");
        }

        if (Integer.valueOf(answer.getText().toString()) == (a + b)) {
            nCorrect++;
        } else {
            nWrong++;
        }

        a = r.nextInt((9) + 1);
        b = r.nextInt((9) + 1);
        question.setText(a + " + " + b + " = ");

        nQuestion++;
    }

}