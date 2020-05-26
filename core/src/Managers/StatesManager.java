package Managers;

import States.State;
import States.PlayState;

public class StatesManager {

    private State state;

    public static final int MENU = 0;
    public static final int NEW_GAME = 123;
    public static final int PLAY = 456;
    public static final int RULES = 789;

    public StatesManager(){
        setState(PLAY);
    }

    public void setState(int state){
        if (this.state != null) this.state.dispose();
        if (state == MENU) {
            //this.state = new MenuState(this);
        }
        if (state == NEW_GAME){
            //this.state = new InitState(this);
        }
        if (state == PLAY){
            this.state = new PlayState(this);
        }
        if (state == RULES){
            //this.state = new RulesState(this);
        }
    }

    public void update(float dt) throws InterruptedException {
        state.update(dt);
    }
    public void draw(){
        state.draw();
    }
}
