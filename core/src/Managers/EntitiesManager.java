package Managers;

import Entities.Creatures.Player;
import States.PlayState;

public class EntitiesManager {

    PlayState playState;
    Player currentPlayer;

    public EntitiesManager(PlayState playState) {
        this.playState = playState;
    }

    public PlayState getPlayState() {
        return playState;
    }

    public Player getCurrentPayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void changeCurrentPlayer(Player currentPlayer){
        if (currentPlayer == playState.getPlayer1()) this.currentPlayer = playState.getPlayer2();
        else this.currentPlayer = playState.getPlayer1();
    }


    public void update (float dt) {
    }
}
