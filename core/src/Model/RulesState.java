package Model;

import Controller.StatesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class RulesState extends State {
    public RulesState(StatesManager sm){
        super(sm);
    }


    public void init() {

    }


    public void update(float dt) throws InterruptedException {
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        if(next){
            sm.setState(StatesManager.TypeState.MENU);

        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public State getCurrentState() {
        return this;
    }
}
