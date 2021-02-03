package pt.ipp.estg.recyclerviewquestions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pt.ipp.estg.recyclerviewquestions.adapters.QuestionAdapter;
import pt.ipp.estg.recyclerviewquestions.models.Question;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecycler;
    private QuestionAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Question> questions = createQuestionList(3);

        myAdapter = new QuestionAdapter(this, questions);

        myRecycler = findViewById(R.id.recycler1);
        myRecycler.setAdapter(myAdapter);

        myRecycler.setLayoutManager(new LinearLayoutManager(this));

        myRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Log.d("MAIN_ACTIVITY","onCreate()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QuestionAdapter.REQUEST_CODE && resultCode == RESULT_OK) {
            Question passedItem = (Question) data.getExtras().get("ITEM");
            int position = (Integer) data.getExtras().get("RE_POS");
            // deal with the item yourself
            myAdapter.update(position,passedItem);
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter.notifyDataSetChanged();
        System.out.println("UPDATE DATA");
        Log.d("MAIN_ACTIVITY","onResume()");
    }

    private List<Question> createQuestionList(int size){
        List<Question> tmp = new ArrayList<>(size);
        Random rd = new Random();

        for (int i = 0; i < size; i++){

            int a = rd.nextInt(10 - 1 + 1) + 1;
            int b = rd.nextInt(10 - 1 + 1) + 1;
            int answer = a+b;

            String description = "Qual o valor de " + a + "+" + b + "?";
            tmp.add(i, new Question("Questão " + (i+1), description, String.valueOf(answer)));

        }
        return tmp;
    }
}