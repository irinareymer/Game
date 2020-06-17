package Controller.States;

import Controller.StatesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;

public class RulesState extends State {
    public RulesState(StatesManager sm) {
        super(sm);
    }
    public String text;

    public void init() {
        FileHandle rules = Gdx.files.internal("rules.txt");
        text = rules.readString("UTF-8");
    }

    public void update(float dt) {
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        if (next) {
            sm.setState(StatesManager.TypeState.MENU);

        }
    }
}