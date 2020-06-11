package Model;

import Controller.Save;
import Controller.StatesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ScoreState extends  State {
    public ScoreState(StatesManager sm) { super(sm);}

    public int[] scores;
    public String[] names;

    public void init() {
        Save.load();
        scores = Save.data.getScores();
        names = Save.data.getNames();
    }

    public void update(float dt) {
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        if(next){
            sm.setState(StatesManager.TypeState.MENU);
        }
    }

    public void dispose() {

    }

    public State getCurrentState() {
        return this;
    }
}
