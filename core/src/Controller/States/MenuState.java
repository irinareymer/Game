package Controller.States;

import Controller.StatesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MenuState extends State {
    public MenuState(StatesManager sm){
        super(sm);
    }
    public int currentItem;

    public void init(){
        currentItem = 0;
    }

    public void update (float dt) {
        input();
    }

    public void select(int currentItem){
        if(currentItem == 0){
            sm.setState(StatesManager.TypeState.PLAY);
        }
        else if(currentItem == 1){
            sm.setState(StatesManager.TypeState.RULES);
        }
        else if(currentItem == 2){
            sm.setState(StatesManager.TypeState.STATS);
        }
        else if (currentItem == 3){
            System.exit(0);
        }
    }
    public void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(currentItem >0) currentItem--;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(currentItem < 3) currentItem++;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            select(currentItem);
        }
    }
}
