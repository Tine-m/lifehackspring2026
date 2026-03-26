package app.entities.TeamN;

public class FunQuote {
    private int id;
    private String setup;
    private String punchLine;

    public FunQuote(int id, String setup, String punchLine) {
        this.id = id;
        this.setup = setup;
        this.punchLine = punchLine;

    }

    public String getSetup(){
        return setup;
    }

    public String getPunchLine(){
        return punchLine;
    }

}
