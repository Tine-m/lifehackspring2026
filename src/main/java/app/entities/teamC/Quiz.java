package app.entities.teamC;

public class Quiz {
    private String questionText;
    private String answerCorrect;
    private String answerWrong1;
    private String answerWrong2;
    private String imgCorrect;
    private String imgWrong;
    private String soundCorrect;
    private String soundWrong;

    public Quiz(String questionText, String answerCorrect, String answerWrong1, String answerWrong2, String imgCorrect, String imgWrong, String soundCorrect, String soundWrong) {
        this.questionText = questionText;
        this.answerCorrect = answerCorrect;
        this.answerWrong1 = answerWrong1;
        this.answerWrong2 = answerWrong2;
        this.imgCorrect = imgCorrect;
        this.imgWrong = imgWrong;
        this.soundCorrect = soundCorrect;
        this.soundWrong = soundWrong;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "questionText='" + questionText + '\'' +
                ", answerCorrect='" + answerCorrect + '\'' +
                ", answerWrong1='" + answerWrong1 + '\'' +
                ", answerWrong2='" + answerWrong2 + '\'' +
                ", imgCorrect='" + imgCorrect + '\'' +
                ", imgWrong='" + imgWrong + '\'' +
                ", soundCorrect='" + soundCorrect + '\'' +
                ", soundWrong='" + soundWrong + '\'' +
                '}';
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public String getAnswerWrong1() {
        return answerWrong1;
    }

    public String getAnswerWrong2() {
        return answerWrong2;
    }

    public String getImgCorrect() {
        return imgCorrect;
    }

    public String getImgWrong() {
        return imgWrong;
    }

    public String getSoundCorrect() {
        return soundCorrect;
    }

    public String getSoundWrong() {
        return soundWrong;
    }
}
