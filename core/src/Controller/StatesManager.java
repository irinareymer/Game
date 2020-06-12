package Controller;

import Model.*;
//import View.GameOverView.GameOverView;
import View.MenuStateView.MenuStateView;
import View.PlayStateView.PlayView;
import View.RulesStateView.RulesStateView;
import View.ScoreView.ScoreView;
import View.View;

public class StatesManager {

    private State state;
    View view;

    public enum TypeState{
        MENU,
        PLAY,
        RULES,
        STATS,
        GAMEOVER
    }
    public static TypeState ts;

    public  StatesManager(){
        setState(TypeState.MENU);
    }

    public void init(){
        ts = TypeState.MENU;
    }

    public void setState(TypeState state){
        if (this.state != null) this.state.dispose();
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
            this.view = new PlayView((PlayState) this.getCurrentState());

        }
        if (state == TypeState.RULES){
            this.state = new RulesState(this);
            this.view = new RulesStateView((RulesState) this.getCurrentState());
        }
        /*if (state == TypeState.GAMEOVER){
            this.state = new GameOverState(this);
            this.view = new GameOverView((GameOverState) this.getCurrentState());
        }*/
    }
    public State getCurrentState(){
        return state;
    }

    public void update(float dt) throws InterruptedException {
        state.update(dt);
        view.update(dt);

    }

}
