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
    boolean isStarted = false;

    public GameLogic(EntitiesManager em) {
        this.em = em;
    }

    public void init() {
    }

    public void update(float dt) {
        while(!isStarted)
            play();
}

    public void play() {
        em.setCurrentPlayer(em.getPlayState().getPlayer1());

        for (int i = 0; i < 2; i++) {
            em.getCurrentPayer().setName("OLEG"); //todo
            //System.out.println("Current" + em.getCurrentPayer().getName());

            //setParameters todo show param
            boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
            if (!isSetLuck && act) {
                rolled = em.getPlayState().getDice().rollDice();
                em.getCurrentPayer().setLuck(rolled);
                System.out.println("luck" + em.getPlayState().getDice().getRolled());
                System.out.println(em.getCurrentPayer().getLuck());
                isSetLuck = true;
                act = false;
            }
            if (!isSetPower && isSetLuck && act) {
                rolled = em.getPlayState().getDice().rollDice();
                em.getCurrentPayer().setPower(rolled);
                System.out.println("power" + em.getPlayState().getDice().getRolled());
                System.out.println(em.getCurrentPayer().getPower());
                isSetPower = true;
                act = false;
            }
            if (!isSetSpeed && isSetPower && isSetLuck && act) {
                rolled = em.getPlayState().getDice().rollDice();
                em.getCurrentPayer().setSpeed(rolled);
                System.out.println("speed" + em.getPlayState().getDice().getRolled());
                System.out.println(em.getCurrentPayer().getSpeed());
                isSetSpeed = true;
                act = false;
            }
            isSetSpeed = false;
            isSetLuck = false;
            isSetPower = false;
            em.changeCurrentPlayer(em.getCurrentPayer());
        }
        isStarted = true;
/*


        //loop
        while (em.getPlayState().getPlayer1().getCountItems() != 3 || em.getPlayState().getPlayer2().getCountItems() != 3) {
            em.changeCurrentPlayer(em.getCurrentPayer());
            //roll
            rollDice();
            // }
        }*/

    }
}
