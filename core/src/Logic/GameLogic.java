package Logic;

import Entities.Creatures.Player;
import Managers.EntitiesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputEventQueue;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Random;

public class GameLogic {
    //todo

    protected EntitiesManager em;
    boolean isSetLuck = false;
    boolean isSetPower = false;
    int rolled;

    public GameLogic(EntitiesManager em) {
        this.em = em;
    }

    public void init() {
        play();
    }

    public void update(float dt) {
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
        if(em.getPlayState().getKm().isAction()) {
            rolled = random(em.getCurrentPayer().getLuck());
        }
        return rolled;
    }

    public int getRolled() {
        return rolled;
    }

    public void play() {

        //player1
        em.setCurrentPlayer(em.getPlayState().getPlayer1());
        System.out.println("Current" + em.getCurrentPayer().toString());
        em.getCurrentPayer().setName("OLEG"); //todo

        //setParameters for player1 //todo show param
        if (em.getPlayState().getKm().isAction() && !isSetLuck) {
            em.getCurrentPayer().setLuck(rollDice());
            System.out.println(em.getCurrentPayer().getLuck());
            isSetLuck = true;
        }
        if (em.getPlayState().getKm().isAction() && isSetLuck && !isSetPower) {
            em.getCurrentPayer().setPower(rollDice());
            System.out.println(em.getCurrentPayer().getPower());
            isSetPower = true;
        }
        em.getCurrentPayer().setSpeed(rollDice());
            System.out.println(em.getCurrentPayer().getSpeed());

        //player2
        em.setCurrentPlayer(em.getPlayState().getPlayer2());
        em.getCurrentPayer().setName("MARY"); //todo
        //setParameters for player2 //todo show param
        em.getCurrentPayer().setLuck(rollDice());
        em.getCurrentPayer().setPower(rollDice());
        em.getCurrentPayer().setSpeed(rollDice());

        //loop
        while (em.getPlayState().getPlayer1().getCountItems() != 3 || em.getPlayState().getPlayer2().getCountItems() != 3) {
            em.changeCurrentPlayer(em.getCurrentPayer());
            //roll
            rollDice();
            // }

        }

    }
}
