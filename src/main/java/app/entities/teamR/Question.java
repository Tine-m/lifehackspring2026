package app.entities.teamR;

public class Question {
    private int id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correct;
    private int difficulty;
    private int questionNumber;
    private String setName;

    public Question(int id, String question, String optionA, String optionB,
                    String optionC, String optionD, String correct,
                    int difficulty, int questionNumber, String setName) {

        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correct = correct;
        this.difficulty = difficulty;
        this.questionNumber = questionNumber;
        this.setName = setName;
    }

    // getters
    public String getQuestion() { return question; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public String getCorrect() { return correct; }
}
