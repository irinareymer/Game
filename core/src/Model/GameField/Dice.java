package Model.GameField;

import Controller.States.PlayState;

import java.util.Random;

public class Dice {

    PlayState em;
    int rolled;

    public Dice (PlayState em) {
        this.em = em;
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
        rolled = random(em.getCurrentPayer().getLuck());
        return rolled;
    }

    public int getRolled() {
        return rolled;
    }
}
