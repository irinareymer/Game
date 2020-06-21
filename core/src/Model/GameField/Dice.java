package Model.GameField;

import Controller.States.PlayState;

import java.util.Random;

public class Dice {

    private PlayState ps;
    private int rolled;

    public Dice (PlayState ps) {
        this.ps = ps;
    }

    public int random(int luck){
        Random rndm = new Random();
        int random = rndm.nextInt(5) + 1;

        if (luck == 3 || luck == 4) random++;
        if (luck >= 5) random +=2;
        if (random > 6) random = 6;

        return random;
    }

    public int rollDice() {
        rolled = random(ps.getCurrentPayer().getLuck());
        return rolled;
    }

    public int getRolled() {
        return rolled;
    }
}