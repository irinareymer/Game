package Entities.GameField;

import Managers.EntitiesManager;
import com.badlogic.gdx.graphics.Texture;

public class Dice {

    Texture dice;
    EntitiesManager em;

    public Dice (EntitiesManager em) {
        this.em = em;
    }

    public void init() {
        diceImg(6);
    }

    public void update(float dt) {
        diceImg(em.getPlayState().getLogic().getRolled());
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

}
