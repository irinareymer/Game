package Controller;

import Controller.States.*;
import View.ScoreView;
import View.MenuStateView;
import View.RulesStateView;
import View.PlayStateView.PlayView;
import View.View;

public class StatesManager {

    private State state;
    View view;

    public enum TypeState{
        MENU,
        PLAY,
        RULES,
        STATS
    }
    public static TypeState ts;

    public  StatesManager(){
        setState(TypeState.MENU);
    }

    public void init(){
        ts = TypeState.MENU;
    }

    public void setState(TypeState state){
        if (this.view != null) this.view.dispose();
        if (state == TypeState.MENU) {
            this.state = new MenuState(this);
            this.view = new MenuStateView((MenuState) this.getCurrentState());
            Save.load();
        }
        if (state == TypeState.STATS){
            this.state = new ScoreState(this);
            this.view = new ScoreView((ScoreState) this.getCurrentState());
        }
        if (state == TypeState.PLAY){
            this.state = new PlayState(this);
            this.view = new PlayView((PlayState) this.state);

        }
        if (state == TypeState.RULES){
            this.state = new RulesState(this);
            this.view = new RulesStateView((RulesState) this.getCurrentState());
        }
    }

    public State getCurrentState(){
        return state;
    }

    public void update(float dt) {
        state.update(dt);
        view.update(dt);
    }

}
