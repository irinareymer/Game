package Controller.States;

import Controller.Save;
import Controller.StatesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ScoreState extends State {
    public ScoreState(StatesManager sm) { super(sm);}

    private int[] scores;
    private String[] names;

    public String[] getNames() {
        return names;
    }

    public int[] getScores() {
        return scores;
    }

    @Override
    public void init() {
        Save.load();
        scores = Save.getData().getScores();
        names = Save.getData().getNames();
    }

    @Override
    public void update(float dt) {
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        if(next){
            sm.setState(StatesManager.TypeState.MENU);
        }
    }
}