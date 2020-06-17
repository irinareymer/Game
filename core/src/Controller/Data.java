package Controller;

import java.io.Serializable;

public class Data implements Serializable {

    private static final int serialVersionUID = 1;
    private final int MAX_SCORE = 5;
    private int[] scores;
    private String[] names;

    public Data(){
        scores = new int[MAX_SCORE];
        names = new String[MAX_SCORE];
    }

    public void init(){
        for (int i = 0; i < MAX_SCORE; i++){
            scores[i] = 0;
            names[i] = "--------";
        }
    }

    public int[] getScores(){return scores;}
    public String[] getNames(){return names;}

    public boolean isScoreHigher(int currentScore){
        return currentScore > scores[MAX_SCORE - 1];
    }

    public void addScore(int currentScore, String currentName){
        if(isScoreHigher(currentScore)){
            scores[MAX_SCORE - 1] = currentScore;
            names[MAX_SCORE - 1] = currentName;
            sortScores();
        }
    }

    public void sortScores(){
        for (int i = 0; i < MAX_SCORE; i++) {
            int s = scores[i];
            String n = names[i];
            int k;
            for (k = i - 1; k >= 0 && scores[k] < s; k--) {
                scores[k + 1] = scores[k];
                names[k + 1] = names[k];
            }
            scores[k + 1] = s;
            names[k + 1] = n;
        }
    }
}