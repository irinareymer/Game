package Controller;

import Controller.States.*;
import View.ScoreView;
import View.MenuStateView;
import View.RulesStateView;
import View.PlayStateView.PlayView;
import View.View;

public class StatesManager {

    private State state;
    private View view;

    public enum TypeState{
        MENU,
        PLAY,
        RULES,
        STATS
    }

    public StatesManager(){
        setState(TypeState.MENU);
    }

    public void setState(TypeState state){
        if (this.view != null) this.view.dispose();
        if (state == TypeState.MENU) {
            this.state = new MenuState(this);
            this.view = new MenuStateView((MenuState) this.state);
            Save.load();
        }
        if (state == TypeState.STATS){
            this.state = new ScoreState(this);
            this.view = new ScoreView((ScoreState) this.state);
        }
        if (state == TypeState.PLAY){
            this.state = new PlayState(this);
            this.view = new PlayView((PlayState) this.state);

        }
        if (state == TypeState.RULES){
            this.state = new RulesState(this);
            this.view = new RulesStateView((RulesState) this.state);
        }
    }

    public void update(float dt) {
        state.update(dt);
        view.update(dt);
    }
}