package pt.ipp.estg.recyclerviewquestions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    public static final String SELECTED_QUESTION = "pt.ipp.pt.estg.cmu.selectedquestion";
    public static final String SELECTED_QUESTION_ID = "pt.ipp.pt.estg.cmu.selectedquestionid";

    private Context context;
    private List<Question> questionList;

    public QuestionAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View questionView = inflater.inflate(R.layout.question, parent, false);

        return new QuestionViewHolder(questionView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionList.get(position);

        TextView textView = holder.textView;
        textView.setText(question.getTitle());


        ImageView imageView = holder.imageView;
        if (question.getState().equals(Question.NOT_COMPLETED)) {
            imageView.setImageResource(R.mipmap.ic_yellow_round);
        } else if (question.getState().equals(Question.WRONG)) {
            imageView.setImageResource(R.mipmap.ic_red_round);
        } else {
            imageView.setImageResource(R.mipmap.ic_green_round);
        }

        Button button = holder.button;

        if (!question.getState().equals(Question.NOT_COMPLETED)) {
            button.setEnabled(false);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startActivityIntent = new Intent(context, AnswerQuestion.class);
                startActivityIntent.putExtra(SELECTED_QUESTION_ID, position);
                startActivityIntent.putExtra(SELECTED_QUESTION, question);
                MainActivity activity = (MainActivity) context;
                activity.startActivityForResult(startActivityIntent, MainActivity.REQUEST_ANSWER);
            }
        });

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public Button button;
        public ImageView imageView;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
