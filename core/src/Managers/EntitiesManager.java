package Managers;

import Entities.Creatures.Player;
import States.PlayState;

public class EntitiesManager {

    PlayState playState;
    Player currentPlayer;

    public EntitiesManager(PlayState playState) {
        this.playState = playState;
    }

    public Player getCurrentPayer() {
        return currentPlayer;
    }

    public void update (float dt) {
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void changeCurrentPlayer(Player currentPlayer){
        if (currentPlayer == playState.getPlayer1()) currentPlayer = playState.getPlayer2();
        else currentPlayer = playState.getPlayer1();
    }

    public PlayState getPlayState() {
        return playState;
    }
}
