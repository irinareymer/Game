package Entities.GameField;

import Managers.EntitiesManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Dice {

    EntitiesManager em;
    Texture dice;
    int rolled;

    public Dice (EntitiesManager em) {
        this.em = em;
    }

    public void init() {
        diceImg(6);
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

    public Texture getDice() {
        return dice;
    }

    public Texture diceImg(int random){
        if (random == 1) dice = new Texture("img/dice/dice1.png");
        if (random == 2) dice = new Texture("img/dice/dice2.png");
        if (random == 3) dice = new Texture("img/dice/dice3.png");
        if (random == 4) dice = new Texture("img/dice/dice4.png");
        if (random == 5) dice = new Texture("img/dice/dice5.png");
        if (random == 6) dice = new Texture("img/dice/dice6.png");
        return dice;
    }

    public void update(float dt) {
        diceImg(rolled);
    }

}
