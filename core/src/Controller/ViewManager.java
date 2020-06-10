/*package Controller;

import Model.PlayState;
import Model.State;
import View.PlayStateView.PlayView;
import View.View;

public class ViewManager {
    private View view;
    public static final int MENU = 0;
    public static final int NEW_GAME = 123;
    public static final int PLAY = 456;
    public static final int RULES = 789;
    PlayState ps;
    StatesManager sm;

    public ViewManager(State state){
        setView(state);
    }

    public void setView(State state){
        if (this.view != null) this.view.dispose();
        //if (state == MENU) {
            //this.view = new MenuView(this);
        //}
       // if (view == NEW_GAME){
            //this.view = new InitView(this);
        //}
        if (state == ps){
            System.out.println(state.toString());
            this.ps = (PlayState) state;
            System.out.println(ps.toString());
            this.view = new PlayView(this, ps);
        }
        //if (view == RULES){
            //this.view = new RulesView(this);
        //}
    }

    public void draw(){
       // view.draw();
    }

    public void update(float dt) throws InterruptedException {
        //view.draw();
        //view.update(dt);
    }

}*/
