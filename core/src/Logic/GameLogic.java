package Logic;

import Entities.Creatures.Player;
import Entities.Creatures.Position;
import Managers.EntitiesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class GameLogic {

    protected EntitiesManager em;
    boolean isSetLuck = false;
    boolean isSetPower = false;
    boolean isSetSpeed = false;
    int rolled;
    boolean moveStarted = false;
    int mapFieldKey = 0;
    boolean ready = false;
    boolean decided = false;
    boolean speedIncrease = false;
    boolean powerIncrease = false;
    boolean luckIncrease = false;


    public GameLogic(EntitiesManager em) {
        this.em = em;
    }

    public void init() {
    }

    public void update(float dt) throws InterruptedException {
        play();
    }

    private void start(Player player) {
        em.setCurrentPlayer(player);
        player.setName("OLEG"); //todo
        //setParameters todo show param
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        //set luck
        if (!isSetLuck && act) {
            act = false;
            rolled = em.getPlayState().getDice().rollDice();
            player.setLuck(rolled);
            System.out.println("luck" + em.getPlayState().getDice().getRolled());
            System.out.println(player.getLuck());
            isSetLuck = true;
            System.out.println(player.toString());
        } //set power
        if (!isSetPower && isSetLuck && act) {
            act = false;
            rolled = em.getPlayState().getDice().rollDice();
            player.setPower(rolled);
            System.out.println("power" + em.getPlayState().getDice().getRolled());
            System.out.println(player.getPower());
            isSetPower = true;
            System.out.println(player.toString());
        } //set speed
        if (!isSetSpeed && isSetPower && isSetLuck && act) {
            act = false;
            rolled = em.getPlayState().getDice().rollDice();
            player.setSpeed(rolled);
            System.out.println("speed" + em.getPlayState().getDice().getRolled());
            System.out.println(player.getSpeed());
            isSetSpeed = true;
            System.out.println(player.toString());
            if (em.getPlayState().getPlayer1().isParametersSet() &&
                    em.getPlayState().getPlayer2().isParametersSet()) {
                System.out.println("Players parameters has been set");
            } else {
                System.out.println("Change player by press to SPACE");
            }
        }
        if (isSetPower && isSetLuck && isSetSpeed && next) {
            isSetSpeed = false;
            isSetLuck = false;
            isSetPower = false;
            System.out.println("Now other player is playing");
        }
    }

    public void settingsAfterStart(boolean firstReady, boolean secondReady){
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (firstReady && secondReady && next) {
            em.changeCurrentPlayer(em.getCurrentPayer());
            ready = true;
            System.out.println("ready");
            next = false;
        }
    }

    public void checkSpecialFields(Player player){
        if(player.getCurrentField() == 9 && !speedIncrease) {
            player.setSpeed(player.getSpeed() + 1);
            System.out.println("speedIncreased");
            speedIncrease = true;
        }
        if(player.getCurrentField() == 14 && !luckIncrease) {
            player.setSpeed(player.getLuck() + 1);
            System.out.println("luckIncreased");
            luckIncrease = true;
        }
        if(player.getCurrentField() == 23 && !powerIncrease) {
            player.setSpeed(player.getPower() + 1);
            System.out.println("powerIncreased");
            powerIncrease = true;
        }
    }

    public void isWannaFight(Player player) {
        boolean yes = Gdx.input.isKeyJustPressed(Input.Keys.Y);
        boolean no = Gdx.input.isKeyJustPressed(Input.Keys.N);
        if (yes) {
            //start fight + may find item
            System.out.println("yes");
            no = false;
            player.setFight(true);
            decided = true;
        }
        if (no) {
            //one field away + end of round
            System.out.println("no");
            yes = false;
            decided = true;
            player.setCurrentField(28 + player.getCurrentField() - 1);
            player.setPose(em.getPlayState().getField().getFieldPosition(player.getCurrentField()));
            checkSpecialFields(player);
            player.setFight(false);
            player.setWannaFight(false);
            pressSpaceToContinue(player);
        }
    }

    public void fight(Player player) throws InterruptedException {
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if (act){
            rolled = em.getPlayState().getDice().rollDice();
            int currentPower = rolled + player.getPower();
            System.out.println("current power is " + currentPower);
            System.out.println("monster roll dice...");
            Thread.sleep(2000);
            rolled = em.getPlayState().getDice().random(1);
            int currentPowerOfMonster = em.getPlayState().getMonster().gePowerOfMonster() + rolled;
            System.out.println("now monster's power is " +  currentPowerOfMonster);
            System.out.println("Fighting!!!");
            Thread.sleep(3000);
            //win if
            if (currentPower > currentPowerOfMonster && player.isFight()){
                System.out.println("Easy!");
                player.setPower(player.getPower() + 1);
                System.out.println("You win! Now your power is " + player.getPower());
                player.setCountItems(player.getCountItems() + 1);
                System.out.println("oh! you find one more item!");
                player.setFight(false);
                System.out.println("press space to cont");
                pressSpaceToContinue(player);
            }
            if (currentPower == currentPowerOfMonster && player.isFight()){
                if (player.getLuck() >= 3) {
                    System.out.println("Luck is on your side..");
                    player.setLuck(player.getLuck() + 1);
                    System.out.println("You win. Now your luck is "+ player.getLuck());
                }
                else {
                    System.out.println("Luck is on your side..");
                    System.out.println("You win!");
                }
                player.setFight(false);
                System.out.println("press space to cont");
                pressSpaceToContinue(player);
            } // lose if
            if (currentPower < currentPowerOfMonster && player.isFight()){
                System.out.println("ouch! You lose");
                if (player.getPower() >= 2) {
                    player.setPower(player.getPower() - 1);
                    System.out.println("Now your power is " + player.getPower());
                }
                else {
                    player.setBlocked(true);
                    System.out.println("You lose next round");
                    player.setCurrentField(28 + player.getCurrentField() - 1);
                    player.setPose(em.getPlayState().getField().getFieldPosition(player.getCurrentField()));
                    checkSpecialFields(player);
                }
                player.setFight(false);
                System.out.println("press space to cont");
                pressSpaceToContinue(player);
            }
            act = false;
            decided=false;
        }
    }

    public void pressSpaceToContinue(Player player){
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (next) {
            powerIncrease = false;
            luckIncrease = false;
            speedIncrease = false;
            em.changeCurrentPlayer(player);
            System.out.println("Continue with  " + em.getCurrentPayer());
            moveStarted = false;
        }
    }

    public void startPlay(Player player) {
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        //start
        //if player not blocked
        if (!moveStarted && !player.isBlocked() && act) {
            System.out.println("start with  " + em.getCurrentPayer());
            //rollDice
            rolled = em.getPlayState().getDice().rollDice();
            System.out.println("Player get " + rolled + "points");
            //change position
            mapFieldKey = (player.getCurrentField() + rolled) % 28;
            em.changeCurrentPlayer(player);
            Player checkPose = em.getCurrentPayer();
            if (checkPose.getCurrentField() == mapFieldKey) {
                checkPose.setPose(new Position(checkPose.getPosition().getX(), checkPose.getPosition().getY() + 20));
            }
            em.changeCurrentPlayer(checkPose);
            player.setCurrentField(mapFieldKey);
            player.setPose(em.getPlayState().getField().getFieldPosition(mapFieldKey));
            checkSpecialFields(player);

            act = false;
            //if monsters
            if (em.getPlayState().getMonster().isMonsterHere(player.getPosition())){
                //set power of monster
                em.getPlayState().getMonster().setPowerOfMonster(em.getPlayState().getDice().random(1));
                System.out.println("monster power is "+ em.getPlayState().getMonster().gePowerOfMonster());
                //fight?
                System.out.println("do you wanna fight? press Y if yes, N if not");
                decided=false;
                player.setWannaFight(true);
            }
            //if no monster && item
            if (!em.getPlayState().getMonster().isMonsterHere(player.getPosition()) &&
                    em.getPlayState().getItems().isItemFound(player.getPosition())) {
                player.setCountItems(player.getCountItems() + 1);
                System.out.println(player.getCountItems() + " items found");
            }
            moveStarted = true;
        }
        //if player blocked
        if (player.isBlocked() && !moveStarted && act) {
            System.out.println("You are waiting this round :(");
            player.setBlocked(false);
            System.out.println("press space");
            moveStarted = true;
        }
        //change player
        if (moveStarted && !player.isWannaFight()) pressSpaceToContinue(em.getCurrentPayer());

    }






    public void play() throws InterruptedException {
        //todo show params
        //start params for players
        if (!em.getPlayState().getPlayer1().isParametersSet()) start(em.getPlayState().getPlayer1());
        if (!em.getPlayState().getPlayer2().isParametersSet() && em.getPlayState().getPlayer1().isParametersSet()) {
            start(em.getPlayState().getPlayer2());
        }
        //game on field
        //press space to start this part
        if(em.getPlayState().getPlayer1().isParametersSet() && em.getPlayState().getPlayer2().isParametersSet() && !ready){
            settingsAfterStart(true, true);
        }
        //until someone get 3 items
        if (em.getPlayState().getPlayer1().getCountItems() != 3 &&
                em.getPlayState().getPlayer2().getCountItems() != 3 && ready){
            if (!em.getCurrentPayer().isWannaFight() && !em.getCurrentPayer().isFight()) startPlay(em.getCurrentPayer());
            if (em.getCurrentPayer().isWannaFight() && !decided) isWannaFight(em.getCurrentPayer());
            if (em.getCurrentPayer().isFight() && decided) {
                    fight(em.getCurrentPayer());
                    em.getCurrentPayer().setWannaFight(false);
                }
            }
        if (em.getPlayState().getPlayer1().getCountItems() == 3 ||
                em.getPlayState().getPlayer2().getCountItems() == 3) System.out.println("end of the game");
        }
}


