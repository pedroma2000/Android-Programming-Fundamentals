package pt.ipp.estg.recyclerviewquestions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ANSWER = 2;

    private RecyclerView recyclerView;
    private List<Question> questionList;
    protected QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        questionList = new ArrayList<>();
        questionList.add(new Question("Eleições nos EUA em 2020!", "Quem ganhou as eleições nos EUA em 2020?", "Joe Biden"));

        questionList.add(new Question("Eleições nos EUA em 2016!", "Quem ganhou as eleições nos EUA em 2016?", "Dump"));
        Question question = questionList.get(1);
        question.answerQuestion("Dump");

        questionList.add(new Question("Eleições nos EUA em 2012!", "Quem ganhou as eleições nos EUA em 2012?", "Yobama"));
        Question yobama = questionList.get(2);
        yobama.answerQuestion("Obama");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ANSWER)
        {
            if(data != null) {
                int position = data.getIntExtra(QuestionAdapter.SELECTED_QUESTION_ID, -1);
                Question question = (Question) data.getSerializableExtra(QuestionAdapter.SELECTED_QUESTION);
                questionList.remove(position);
                questionList.add(position, question);
                questionAdapter.notifyItemChanged(position);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        questionAdapter = new QuestionAdapter(this, questionList);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}