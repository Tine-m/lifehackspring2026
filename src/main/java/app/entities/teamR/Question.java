package app.entities.teamR;

public class Question {
    public int id;
    public String question;
    public String option_a;
    public String option_b;
    public String option_c;
    public String option_d;
    public String correct;
    public String difficulty;

    public Question(int id, String question, String option_a, String option_b,
                    String option_c, String option_d, String correct, String difficulty) {
        this.id = id;
        this.question = question;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.correct = correct;
        this.difficulty = difficulty;
    }
}