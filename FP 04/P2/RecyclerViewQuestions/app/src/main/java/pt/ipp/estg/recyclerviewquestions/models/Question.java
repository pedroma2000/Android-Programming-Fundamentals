package pt.ipp.estg.recyclerviewquestions.models;

import java.io.Serializable;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Question implements Serializable {

    private String title;
    private String description;
    private String answer;
    private String status;

    public Question(String title, String description,
                    String answer) {

        this.title = title;
        this.description = description;
        this.answer = answer;
        this.status = "";
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
