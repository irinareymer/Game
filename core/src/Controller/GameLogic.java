package Controller;

import Controller.States.PlayState;
import Model.Creatures.Player;
import Model.Creatures.Position;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameLogic {

    protected PlayState ps;
    private int rolled;
    private boolean over;

    public GameLogic(PlayState ps) {
       this.ps = ps;
    }

    //set parameters
    private void parameters(Player player) {
        ps.setCurrentPlayer(player);
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        //set luck
        if (!player.isLuckSet() && act) {
            rolled = ps.getDice().rollDice();
            player.setLuck(rolled);
        }
        //set power
        else if (!player.isPowerSet() && player.isLuckSet() && act) {
            rolled = ps.getDice().rollDice();
            player.setPower(rolled);
        }
        //set speed
        else if (!player.isSpeedSet() && player.isPowerSet() && player.isLuckSet() && act) {
            rolled = ps.getDice().rollDice();
            player.setSpeed(rolled);
        }
        else if (player.isSpeedSet() && player.isPowerSet() && player.isLuckSet() && next) {
            player.setIsParametersSet(true);
        }
    }

    public void settingsAfterStart(boolean firstReady, boolean secondReady){
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (firstReady && secondReady && next) {
            ps.changeCurrentPlayer(ps.getCurrentPayer());
            ps.setReady(true);
        }
    }

    //start
    public void startPlay(Player player) {
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        //if player not blocked
        if (!player.isMoveStarted() && !player.isBlocked() && act) {
            //rollDice
            rolled = ps.getDice().rollDice();
            player.setReadyToShowPoints(true);
            //change position
            int mapFieldKey = (player.getCurrentField() + rolled) % 28;
            //check for other player
            ps.changeCurrentPlayer(player);
            Player checkPose = ps.getCurrentPayer();
            if (checkPose.getCurrentField() == mapFieldKey)
                checkPose.setPose(new Position(checkPose.getPosition().getX(),checkPose.getPosition().getY() + 20));
            ps.changeCurrentPlayer(checkPose);
            player.setCurrentField(mapFieldKey);
            player.setPose(ps.getField().getFieldPosition(mapFieldKey));

            //checking for field to increased
            checkSpecialFields(player);

            //monster
            if (ps.getMonster().isMonsterHere(player.getPosition())){
                //set monster's power
                ps.getMonster().setPowerOfMonster(ps.getDice().random(1));
                //wanna fight?
                player.setWannaFight(true);
            }

            //item without monster
            if (!ps.getMonster().isMonsterHere(player.getPosition()) &&
                    ps.getItems().isItemFound(player.getPosition())){
                player.setCountItems(player.getCountItems() + 1);
                player.setItemFound(true);
            }

            //move started
            player.setMoveStarted(true);
        }

        //if player blocked
        if (player.isBlocked() && !player.isMoveStarted()) {
            pressSpaceToContinue(player);
        }

        //change player
        if (player.isMoveStarted() && (!player.isBlocked() || !player.isWannaFight())){
            pressSpaceToContinue(player);
        }
    }

    public void checkSpecialFields(Player player){
        //Speed
        if(player.getCurrentField() == 9 && !player.isSpeedIncreased()) {
            player.setSpeed(player.getSpeed() + 1);
            player.setSpeedIncreased(true);
        }
        //Luck
        if(player.getCurrentField() == 14 && !player.isLuckIncreased()) {
            player.setLuck(player.getLuck() + 1);
            player.setLuckIncreased(true);
        }
        //Power
        if(player.getCurrentField() == 23 && !player.isPowerIncreased()) {
            player.setPower(player.getPower() + 1);
            player.setPowerIncreased(true);
        }
    }

    //fight?
    public void isWannaFight(Player player) {
        boolean yes = Gdx.input.isKeyJustPressed(Input.Keys.Y);
        boolean no = Gdx.input.isKeyJustPressed(Input.Keys.N);
        if (yes) player.setFight(true);
        else if (no) player.setNotWannaFight(true);
    }

    //wanna fight
    public void fight(Player player) {
        player.setReadyToShowPoints(false);
        boolean act = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if (act){
            //roll dice to increase power
            rolled = ps.getDice().rollDice();
            player.setIncreasedPower(rolled);
            player.setPowerIncreased(true);

            //monster's roll
            rolled = ps.getDice().random(1);
            ps.getMonster().setIncreasedPowerOfMonster(rolled);

            //flags
            player.setFight(false);
            player.setMonsterRolled(true);
            player.setReadyToShowResult(true);
        }
    }

    //results of fight
    public void showResults(Player player) {
        boolean show = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        int currentPower = player.getIncreasedPower() + player.getPower();
        int currentPowerOfMonster = ps.getMonster().getPowerOfMonster()+ ps.getMonster().getIncreasedPowerOfMonster();
        if(show) {
            player.setShow(true);
            //win
            if (currentPower > currentPowerOfMonster ){
                player.setPower(player.getPower() + 1);
                player.setCountItems(player.getCountItems() + 1);
                player.setPowerIncreased(false);
            }
            if (currentPower == currentPowerOfMonster){
                if (player.getLuck() >= 3) {
                    player.setLuck(player.getLuck() + 1);
                }
            }
            //lose
            if (currentPower < currentPowerOfMonster && player.getPower() >= 2) {
                    player.setPower(player.getPower() - 1);
            }
            if(currentPower < currentPowerOfMonster && player.getPower() == 1){
                    player.setBlocked(true);
                    player.setLose(true);
            }
            player.setWannaFight(false);
        }
    }

    public void pressSpaceToContinue(Player player){
        boolean next = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (next) {
            //player blocked
            if(player.isBlocked()) player.setBlocked(false);
            //run away
            if (player.notWannaFight()) {
                if (player.getSpeed() > 1) {
                    player.setSpeed(player.getSpeed() - 1);
                    player.setBlocked(false);
                } else player.setBlocked(true);
            }
            //false other flags
            player.setItemFound(false);

            player.setMoveStarted(false);

            player.setWannaFight(false);
            player.setNotWannaFight(false);
            player.setFight(false);
            player.setMonsterRolled(false);
            player.setLose(false);

            player.setLuckIncreased(false);
            player.setPowerIncreased(false);
            player.setSpeedIncreased(false);

            player.setReadyToShowPoints(false);
            player.setReadyToShowResult(false);
            player.setShow(false);

            ps.changeCurrentPlayer(player);
        }
    }

    public void gameOver(Player player){
        Save.load();
        int total = player.getPower() + player.getSpeed() + player.getLuck();
        if(Save.getData().isScoreHigher(total) && !over){
            Save.getData().addScore(total, player.getName());
            Save.save();
            over = true;
        }

    }

    public void play() {
        //set name
        if(!ps.getPlayer1().isNameSet()) ps.inputName(ps.getPlayer1());
        else if(!ps.getPlayer2().isNameSet() && ps.getPlayer1().isNameSet()) ps.inputName(ps.getPlayer2());

        //set parameters for players
        if (ps.getPlayer2().isNameSet() &&!ps.getPlayer1().isParametersSet() && !ps.getPlayer1().isExit())
            parameters(ps.getPlayer1());
        if (!ps.getPlayer2().isParametersSet() && ps.getPlayer1().isParametersSet() && !ps.getPlayer2().isExit())
            parameters(ps.getPlayer2());

        //to start
        if(ps.getPlayer1().isParametersSet() && ps.getPlayer2().isParametersSet()
                && !ps.isReady() && !ps.getCurrentPayer().isExit()){
            settingsAfterStart(true, true);
        }

        //until someone get 3 items
        if (ps.getPlayer1().getCountItems() != 3 && ps.getPlayer2().getCountItems() != 3
                && ps.isReady() && !ps.getCurrentPayer().isExit()){
            //play
            if (!ps.getCurrentPayer().isWannaFight() && !ps.getCurrentPayer().isFight())
                startPlay(ps.getCurrentPayer());

            //is wanna fight?
            if (ps.getCurrentPayer().isWannaFight() && !ps.getCurrentPayer().isFight()
                    && !ps.getCurrentPayer().notWannaFight())
                isWannaFight(ps.getCurrentPayer());
            //fight
            if (ps.getCurrentPayer().isFight()) fight(ps.getCurrentPayer());
            //results of fight
            if(!ps.getCurrentPayer().isFight() && ps.getCurrentPayer().isReadyToShowResult())
                showResults(ps.getCurrentPayer());
            //not wanna fight
            if (ps.getCurrentPayer().notWannaFight()) pressSpaceToContinue(ps.getCurrentPayer());
        }

        //correct last fight before win
        if ((ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3 &&
                !ps.getCurrentPayer().isExit()) && ps.getCurrentPayer().isReadyToShowResult()){
            pressSpaceToContinue(ps.getCurrentPayer());
        }

        //the end
        if ((ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3)) {
            if(ps.getPlayer1().getCountItems() == 3) gameOver(ps.getPlayer1());
            if(ps.getPlayer2().getCountItems() == 3) gameOver(ps.getPlayer2());
            boolean esc = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
            if(esc){
                ps.getSM().setState(StatesManager.TypeState.MENU);
            }
        }
    }

    public void update(){
        play();
    }
}