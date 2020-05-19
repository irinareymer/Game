package Logic;

import Managers.EntitiesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameLogic {
    //todo

    protected EntitiesManager em;
    boolean isSetLuck = false;
    boolean isSetPower = false;
    boolean isSetSpeed = false;
    int rolled;

    public GameLogic(EntitiesManager em) {
        this.em = em;
    }

    public void init() {

    }

    public void update(float dt) {
        play();
}

    public void play() {

        //player1
        em.setCurrentPlayer(em.getPlayState().getPlayer1());
        em.getCurrentPayer().setName("OLEG"); //todo
        //System.out.println("Current" + em.getCurrentPayer().getName());

        //setParameters for player1 //todo show param
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if (!isSetLuck && act){
            rolled = em.getPlayState().getDice().rollDice();
            em.getCurrentPayer().setLuck(rolled);
            System.out.println("luck" + em.getPlayState().getDice().getRolled());
            System.out.println(em.getCurrentPayer().getLuck());
            isSetLuck = true;
            act = false;
        }
        if (!isSetPower && isSetLuck && act){
            rolled = em.getPlayState().getDice().rollDice();
            em.getCurrentPayer().setPower(rolled);
            System.out.println("power" + em.getPlayState().getDice().getRolled());
            System.out.println(em.getCurrentPayer().getPower());
            isSetPower = true;
            act = false;
        }
        if (!isSetSpeed && isSetPower && isSetLuck && act){
            rolled = em.getPlayState().getDice().rollDice();
            em.getCurrentPayer().setSpeed(rolled);
            System.out.println("speed" + em.getPlayState().getDice().getRolled());
            System.out.println(em.getCurrentPayer().getSpeed());
            isSetSpeed = true;
        }


        /*if (em.getPlayState().getKm().isAction() && isSetLuck && !isSetPower) {
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
        }*/

    }
}
