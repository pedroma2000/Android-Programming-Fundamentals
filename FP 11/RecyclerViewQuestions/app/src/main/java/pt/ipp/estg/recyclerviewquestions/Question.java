
package pt.ipp.estg.recyclerviewquestions;

import android.util.Log;

import java.io.Serializable;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Question implements Serializable {

    public final static String NOT_COMPLETED = "Não respondida.";
    public final static String RIGHT = "Respondida corretamente.";
    public final static String WRONG = "Respondida incorretamente.";

    private String title;
    private String description;
    private String response;
    private String state;

    public Question(String title, String description, String response) {
        this.title = title;
        this.description = description;
        this.response = response;
        this.state = NOT_COMPLETED;
    }

    public void answerQuestion(String answer) {
        if (answer.equals(response)) {
            state = RIGHT;
        } else {
            state = WRONG;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getResponse() {
        return response;
    }

    public String getState() {
        return state;
    }
}
