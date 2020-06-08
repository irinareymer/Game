package Controller;

import Model.MenuState;
import Model.PlayState;
import Model.RulesState;
import Model.State;
import View.MenuStateView.MenuStateView;
import View.PlayStateView.PlayView;
import View.RulesStateView.RulesStateView;
import View.View;

public class StatesManager {

    private State state;
    View view;
    //SpriteBatch batch;
    //ViewManager viewManager;

    public enum TypeState{
        MENU,
        NEW_GAME,
        PLAY,
        RULES,
        STATS
    }
    public static TypeState ts;

   // public static final int MENU = 0;
    //public static final int NEW_GAME = 123;
    //public static final int PLAY = 456;
    //public static final int RULES = 789;




    public  StatesManager(){
        setState(TypeState.MENU);

    }

    public void init(){
        ts = TypeState.MENU;

        //View.PlayStateView.TextView.init(WIDTH, HEIGHT);
    }

    public void setState(TypeState state){
        if (this.state != null) this.state.dispose();
        if (state == TypeState.MENU) {
            this.state = new MenuState(this);
            this.view = new MenuStateView((MenuState) this.getCurrentState());

        }
        if (state == TypeState.NEW_GAME){
            //this.state = new InitState(this);
        }
        if (state == TypeState.PLAY){
            this.state = new PlayState(this);
            System.out.println(this.state.toString());
            this.view = new PlayView((PlayState) this.getCurrentState());

        }
        if (state == TypeState.RULES){
            this.state = new RulesState(this);
            this.view = new RulesStateView((RulesState) this.getCurrentState());
        }
    }
    public State getCurrentState(){
        return state;
    }


    public void update(float dt) throws InterruptedException {
        state.update(dt);
        //viewManager.draw();
        //viewManager.update(dt);

        view.update(dt);
    }

}
