package Model.Creatures;

import Controller.States.PlayState;

public class Player extends Creatures {

    protected PlayState em;

    private String name;
    private boolean isNameSet;
    private boolean parametersSet;

    private Position pose;
    private int currentField;

    private int countItems;
    private boolean itemFound;

    private boolean moveStarted;
    private boolean blocked;

    private boolean wannaFight;
    private boolean notWannaFight;
    private boolean fight;
    private int increasedPower;
    private boolean monsterRolled;
    private boolean lose;

    private boolean isPowerIncreased;
    private boolean isLuckIncreased;
    private boolean isSpeedIncreased;

    private boolean readyToShowPoints;
    private boolean showResult;
    private boolean show;

    private boolean exit;

    public Player(PlayState em, Position pose) {
        this.pose = pose;
        this.em = em;
    }
    //name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setIsNameSet(boolean isNameSet){
        this.isNameSet = isNameSet;
    }
    public boolean isNameSet() {
        return isNameSet;
    }

    //parameters
    public void setIsParametersSet(Boolean isSet) {
        this.parametersSet = isSet;
    }
    public boolean isParametersSet(){
        return parametersSet;
    }

    //player position
    public void setPose(Position pose) {
        this.pose = pose;
    }
    public Position getPosition() {
        return pose;
    }

    //field number
    public int getCurrentField() {
        return currentField % 28;
    }
    public void setCurrentField(int currentField) {
        this.currentField = currentField % 28;
    }

    //items
    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }
    public int getCountItems() {
        return countItems;
    }

    public boolean isItemFound(){
        return itemFound;
    }
    public void setItemFound(boolean found){
        this.itemFound = found;
    }

    //move started
    public void setMoveStarted(boolean ms){
        this.moveStarted = ms;
    }
    public boolean isMoveStarted(){
        return moveStarted;
    }

    //moveBlocked
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    //wanna fight
    public boolean isWannaFight() {
        return wannaFight;
    }
    public void setWannaFight(boolean wannaFight) {
        this.wannaFight = wannaFight;
    }

    //not wanna fight
    public void setNotWannaFight(boolean notWannaFight){this.notWannaFight = notWannaFight; }
    public  boolean notWannaFight(){
        return notWannaFight;
    }

    //fight
    public boolean isFight() {
        return fight;
    }
    public void setFight(boolean fight) {
        this.fight = fight;
    }

    //increased power
    public void setIncreasedPower(int pow){
        this.increasedPower = pow;
    }
    public int getIncreasedPower(){
        return increasedPower;
    }

    //lose
    public void setLose(boolean lose){
        this.lose = lose;
    }
    public boolean isLose(){
        return lose;
    }

   //win
    public boolean isMonsterRolled() {
        return monsterRolled;
    }
    public void setMonsterRolled(boolean monsterRolled) {
        this.monsterRolled = monsterRolled;
    }

    //is parameters increased
    //power
    public boolean isPowerIncreased() {
        return isPowerIncreased;
    }
    public void setPowerIncreased(boolean pi){
        this.isPowerIncreased = pi;
    }
    //luck
    public boolean isLuckIncreased() {
        return isLuckIncreased;
    }
    public void setLuckIncreased(boolean pi){
        this.isLuckIncreased = pi;
    }
    //speed
    public boolean isSpeedIncreased() {
        return isSpeedIncreased;
    }
    public void setSpeedIncreased(boolean pi){
        this.isSpeedIncreased = pi;
    }

    //show
    public boolean readyToShowPoints(){
        return readyToShowPoints;
    }
    public void setReadyToShowPoints(boolean readyToShowPoints){
        this.readyToShowPoints = readyToShowPoints;
    }

    public void setReadyToShowResult(boolean showResult){
        this.showResult = showResult;
    }
    public boolean isReadyToShowResult(){ return showResult;}

    public  boolean isShow(){
        return show;
    }
    public void setShow(boolean show){
        this.show = show;
    }

    //exit
    public void setExit(boolean exit){
        this.exit = exit;
    }
    public boolean isExit(){
        return exit;
    }
}