package Model;

import Controller.MyGdxGame;
import Controller.StatesManager;
import Model.Creatures.Monster;
import Model.Creatures.Player;
import Model.Creatures.Position;
import Model.GameField.Dice;
import Model.GameField.Field;
import Model.GameField.Items;
import Model.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MenuState extends State {
    public MenuState(StatesManager sm){
        super(sm);
    }
   // int currentItem;

    public void init(){

    }


    @Override
    public State getCurrentState() {
        return this;
    }

    public void update (float dt) throws InterruptedException {

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


    public void dispose() {

    }
}
