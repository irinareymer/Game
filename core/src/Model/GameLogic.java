package Model;

import Controller.Save;
import Controller.StatesManager;
import Model.Creatures.Player;
import Model.Creatures.Position;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameLogic {

    protected PlayState em;
    boolean isSetLuck = false;
    boolean isSetPower = false;
    boolean isSetSpeed = false;
    int rolled;
    int mapFieldKey = 0;
    boolean decided = false;
    boolean res = false;
    boolean br = false;
    boolean over = false;

    public GameLogic(PlayState em) {
       this.em = em;
    }

    public void init() {}

    //exit
    public void isWannaExit(Player player){
        boolean yes = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        boolean no = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if(yes){
            no = false;
            em.getSM().setState(StatesManager.TypeState.MENU);
        }
        if(no) {
            player.setExit(false);
            yes = false;
        }
    }

    //start
    private void start(Player player) {
        em.setCurrentPlayer(player);
        //setParameters
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        //set luck
        if (!isSetLuck && act) {
            act = false;
            rolled = em.getDice().rollDice();
            player.setLuck(rolled);
            isSetLuck = true;
        } //set power
        else if (!isSetPower && isSetLuck && act  ) {
            act = false;
            rolled = em.getDice().rollDice();
            player.setPower(rolled);
            isSetPower = true;
        } //set speed
        else if (!isSetSpeed && isSetPower && isSetLuck && act) {
            act = false;
            rolled = em.getDice().rollDice();
            player.setSpeed(rolled);
            isSetSpeed = true;
        }
        else if (isSetPower && isSetLuck && isSetSpeed && next) {
            isSetSpeed = false;
            isSetLuck = false;
            isSetPower = false;
            player.setIsParametersSet(true);
        }
    }

    public void settingsAfterStart(boolean firstReady, boolean secondReady){
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (firstReady && secondReady && next) {
            em.changeCurrentPlayer(em.getCurrentPayer());
            em.setReady(true);
            next = false;
        }
    }

    public void checkSpecialFields(Player player){
        //Speed increased
        if(player.getCurrentField() == 9 && !player.isSpeedIncreased()) {
            player.setSpeed(player.getSpeed() + 1);
            player.setSpeedIncreased(true);
        }//Luck increased
        if(player.getCurrentField() == 14 && !player.isLuckIncreased()) {
            player.setLuck(player.getLuck() + 1);
            player.setLuckIncreased(true);
        }//Power increased
        if(player.getCurrentField() == 23 && !player.isPowerIncreased()) {
            player.setPower(player.getPower() + 1);
            player.setPowerIncreased(true);
        }
    }

    //if monster is here:
    public void isWannaFight(Player player) {
        boolean yes = Gdx.input.isKeyJustPressed(Input.Keys.Y);
        boolean no = Gdx.input.isKeyJustPressed(Input.Keys.N);
        if (yes) {
            //start fight
            no = false;
            player.setFight(true);
            decided = true;
        }
        if (no) {
            //one field away + end of round
            yes = false;
            br = true;
            player.setNotWannaFight(true);
            decided = true;
        }
    }

    //if not wanna fight:
    public void notFight(Player player){
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (next) {
            decided = false;
            player.setLuckIncreased(false);
            player.setPowerIncreased(false);
            player.setSpeedIncreased(false);

            player.setMoveStarted(false);
            player.setReadyToShowPoints(false);
            player.setReadyToShowResult(false);
            player.setFight(false);
            player.setShow(false);
            player.setWannaFight(false);
            player.setNotWannaFight(false);
            player.setItemFound(false);
            if(player.getSpeed() > 1){
                player.setSpeed(player.getSpeed() - 1);
                player.setBlocked(false);
            }
            else player.setBlocked(true);
            em.changeCurrentPlayer(player);
            br = false;
            next = false;
        }
    }

    //if wanna fight:
    public void fight(Player player) {
        player.setReadyToShowPoints(false);
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if (act){
            rolled = em.getDice().rollDice();
            player.setIncreasedPower(rolled);
            player.setPowerIncreased(true);
            int currentPower = rolled + player.getPower();
            rolled = em.getDice().random(1);
            em.getMonster().setIncreasedPowerOfMonster(rolled);
            int currentPowerOfMonster = em.getMonster().getPowerOfMonster() + rolled;
            player.setFight(false);
            res = true;
            player.setWin(true);
            player.setReadyToShowResult(true);
            act = false;
            decided=false;
            pressSpaceToContinue(player);

        }

    }

    //results of fight:
    public void showResults(Player player) {
        boolean show = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        boolean r = true;
        int currentPower = player.getIncreasedPower() + player.getPower();
        int currentPowerOfMonster = em.getMonster().getPowerOfMonster()+em.getMonster().getIncreasedPowerOfMonster();
        if(show) {
            player.setShow(true);
            //win
            if (currentPower > currentPowerOfMonster && r ){
                player.setPower(player.getPower() + 1);
                player.setCountItems(player.getCountItems() + 1);
                r = false;
                player.setPowerIncreased(false);
                show = false;

            }
            if (currentPower == currentPowerOfMonster && r){
                if (player.getLuck() >= 3) {
                    player.setLuck(player.getLuck() + 1);
                    r = false;
                    show = false;

                }

            } //lose
            if (currentPower < currentPowerOfMonster && player.getPower() >= 2 && r) {
                    player.setPower(player.getPower() - 1);
                    r = false;
                show = false;

            }
            if(currentPower < currentPowerOfMonster && player.getPower() == 1 && r){
                    player.setBlocked(true);
                    player.setLose(true);
                    r = false;
                show = false;

            }
            player.setWannaFight(false);
            res = false;
        }
    }




    public void pressSpaceToContinue(Player player){
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (next) {
            player.setLuckIncreased(false);
            player.setPowerIncreased(false);
            player.setSpeedIncreased(false);
            em.changeCurrentPlayer(player);
            player.setMoveStarted(false);
            player.setReadyToShowPoints(false);
            player.setReadyToShowResult(false);
            player.setFight(false);
            player.setShow(false);
            player.setWannaFight(false);
            player.setNotWannaFight(false);
            player.setItemFound(false);
            player.setWin(false);
            br = false;
            player.setLose(false);
        }
    }

    public void startPlay(Player player) {
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        //start
        //if player not blocked
        if (!player.isMoveStarted() && !player.isBlocked() && act) {
            //rollDice
            rolled = em.getDice().rollDice();
            player.setReadyToShowPoints(true);
            //change position
            mapFieldKey = (player.getCurrentField() + rolled) % 28;
            em.changeCurrentPlayer(player);
            Player checkPose = em.getCurrentPayer();
            if (checkPose.getCurrentField() == mapFieldKey) {
                checkPose.setPose(new Position(checkPose.getPosition().getX(), checkPose.getPosition().getY() + 20));
            }
            em.changeCurrentPlayer(checkPose);
            player.setCurrentField(mapFieldKey);
            player.setPose(em.getField().getFieldPosition(mapFieldKey));
            checkSpecialFields(player);

            act = false;
            //if monsters
            if (em.getMonster().isMonsterHere(player.getPosition())){
                //set power of monster
                em.getMonster().setPowerOfMonster(em.getDice().random(1));
                //fight?
                decided=false;
                player.setWannaFight(true);
            }
            //if no monster && item
            if (!em.getMonster().isMonsterHere(player.getPosition()) &&
                    em.getItems().isItemFound(player.getPosition())) {
                player.setCountItems(player.getCountItems() + 1);
                player.setItemFound(true);
            }
            player.setMoveStarted(true);
        }
        //if player blocked
        if (player.isBlocked() && !player.isMoveStarted()) {
            boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
            if (next) {
                player.setLuckIncreased(false);
                player.setPowerIncreased(false);
                player.setSpeedIncreased(false);
                em.changeCurrentPlayer(player);
                player.setMoveStarted(false);
                player.setReadyToShowPoints(false);
                player.setPowerIncreased(false);
                player.setReadyToShowResult(false);
                player.setFight(false);
                player.setShow(false);
                player.setWannaFight(false);
                player.setNotWannaFight(false);
                player.setItemFound(false);
                player.setBlocked(false);
                br = false;
            }
        }
        //change player
        if (player.isMoveStarted() && !player.isWannaFight()) {
            pressSpaceToContinue(player);
        }
        if (player.isMoveStarted() && !player.isBlocked()) {
            pressSpaceToContinue(player);
        }
    }


    public void play() {
        //exit
        boolean exit = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        if(exit) {
            em.getCurrentPayer().setExit(true);
            exit=false;
        }
        //set name
        if(!em.getPlayer1().isNameSet()) em.inputName(em.getPlayer1());
        else if(!em.getPlayer2().isNameSet() && em.getPlayer1().isNameSet()) em.inputName(em.getPlayer2());
        //start params for players
        if (em.getPlayer2().isNameSet() &&!em.getPlayer1().isParametersSet() && !em.getPlayer1().isExit()) start(em.getPlayer1());
        if (!em.getPlayer2().isParametersSet() && em.getPlayer1().isParametersSet() && !em.getPlayer2().isExit()) start(em.getPlayer2());
        //game on field
        //press space to start this part
        if(em.getPlayer1().isParametersSet() && em.getPlayer2().isParametersSet() && !em.isReady() && !em.getCurrentPayer().isExit()){
            settingsAfterStart(true, true);
        }
        if(em.getCurrentPayer().isExit()){
            isWannaExit(em.getCurrentPayer());
        }
        //until someone get 3 items
        if (em.getPlayer1().getCountItems() != 3 &&
                em.getPlayer2().getCountItems() != 3 && em.isReady() && !em.getCurrentPayer().isExit()){
            if (!em.getCurrentPayer().isWannaFight() && !em.getCurrentPayer().isFight() && !res && !br) startPlay(em.getCurrentPayer());
            if (em.getCurrentPayer().isWannaFight() && !decided) isWannaFight(em.getCurrentPayer());
            if (em.getCurrentPayer().isFight() && decided) {
                    fight(em.getCurrentPayer());
                }
            if (!em.getCurrentPayer().isFight() && decided && br) {
                notFight(em.getCurrentPayer());
            }
            if(!em.getCurrentPayer().isFight()&& em.getCurrentPayer().isReadyToShowResult() && res){
                showResults(em.getCurrentPayer());
            }

        }

        if ((em.getPlayer1().getCountItems() == 3 ||
            em.getPlayer2().getCountItems() == 3 && !em.getCurrentPayer().isExit())  && em.getCurrentPayer().isReadyToShowResult() ){
            pressSpaceToContinue(em.getCurrentPayer());
        }

        if ((em.getPlayer1().getCountItems() == 3 ||
                em.getPlayer2().getCountItems() == 3)) {
            if(em.getPlayer1().getCountItems() == 3) gameOver(em.getPlayer1());
            if(em.getPlayer2().getCountItems() == 3) gameOver(em.getPlayer2());
            boolean next = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
            if(next){
                em.getSM().setState(StatesManager.TypeState.MENU);
            }
        }
    }

    public void gameOver(Player player){
        Save.load();
        if(Save.data.isScoreHigher(player.getPower()) && !over){
            Save.data.addScore(player.getPower() + player.getSpeed() + player.getLuck(), player.getName());
            Save.save();
            over = true;
        }
    }

    public void update(float dt){
        play();
    }
}


