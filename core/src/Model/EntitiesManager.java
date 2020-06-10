package Model;

import Model.Creatures.Monster;
import Model.Creatures.Player;
import Model.GameField.Dice;
import Model.GameField.Field;
import Model.GameField.Items;

public class EntitiesManager {

    PlayState playState;
    Player currentPlayer;


    public EntitiesManager(PlayState playState) {
        this.playState = playState;
    }

    public PlayState getPlayState() {
        return playState;
    }






    public void update (float dt) {
    }

}
