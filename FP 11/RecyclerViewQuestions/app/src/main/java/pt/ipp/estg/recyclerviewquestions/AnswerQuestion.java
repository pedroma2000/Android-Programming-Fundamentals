package pt.ipp.estg.recyclerviewquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class AnswerQuestion extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private EditText et;
    private Button bt;
    private Question question;
    private int position;
    private Intent startService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        Intent intent = getIntent();

        question = (Question) intent.getSerializableExtra(QuestionAdapter.SELECTED_QUESTION);
        position = intent.getIntExtra(QuestionAdapter.SELECTED_QUESTION_ID, -1);

        tv1 = findViewById(R.id.textView1);
        tv1.setText(question.getTitle());

        tv2 = findViewById(R.id.textView2);
        tv2.setText(question.getDescription());

        et = findViewById(R.id.editText);

        bt = findViewById(R.id.button2);
        bt.setOnClickListener(this);
        startService = new Intent(this, AudioService.class);
        startService(startService);
    }

    @Override
    public void onClick(View v) {
        String answer = et.getText().toString();
        question.answerQuestion(answer);
        Intent intent = new Intent();
        intent.putExtra(QuestionAdapter.SELECTED_QUESTION_ID, position);
        intent.putExtra(QuestionAdapter.SELECTED_QUESTION, question);
        setResult(MainActivity.REQUEST_ANSWER,intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(startService);
    }
}