package Model.Creatures;

import Model.PlayState;

public class Player extends Creatures {

    protected PlayState em;

    String name;
    Position pose;
    int currentField = 0;
    int countItems = 0;
    boolean itemFound = false;
    boolean blocked = false;
    boolean wannaFight = false;
    boolean fight = false;
    boolean parametersSet = false;
    boolean readyToShowPoints = false;
    int increasedPower = 0;
    boolean isPowerIncreased = false;
    boolean isLuckIncreased = false;
    boolean isSpeedIncreased = false;
    boolean showResult = false;
    boolean show = false;
    boolean notWannaFight = false;
    boolean nf = false;
    boolean checkSpecialField = false;
    boolean win = false;
    boolean end = false;
    boolean moveStarted = false;
    boolean lose = false;
    //private int[] shapex;
    //private int[] shapey;

    boolean yes;
    boolean exit = false;
    boolean enter;
    boolean space;

    public void setYes(boolean y){yes = y;}
    public void setNo(boolean n){yes = n;}
    public void setEnter(boolean e){yes = e;}
    public void setSpace(boolean s){yes = s;}


    public Player(PlayState em, Position pose) {
        this.pose = pose;
        this.em = em;
        //shapex = new int[4];
        //shapey = new int[4];
    }

    public void setLose(boolean lose){
        this.lose = lose;
    }
    public boolean isLose(){
        return lose;
    }

    public void setExit(boolean exit){
        this.exit = exit;
    }
    public boolean isExit(){
        return exit;
    }

    public void setMoveStarted(boolean ms){
        this.moveStarted = ms;
    }
    public boolean isMoveStarted(){
        return moveStarted;
    }

    //name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //parameters
    public void setIsParametersSet(Boolean isSet) {
        this.parametersSet = isSet;
    }
    public boolean isParametersSet(){
        return parametersSet;
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

    //moveBlocked
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    //fight
    public boolean isWannaFight() {
        return wannaFight;
    }

    public void setWannaFight(boolean wannaFight) {
        this.wannaFight = wannaFight;
    }

    public boolean isFight() {
        return fight;
    }

    public void setFight(boolean fight) {
        this.fight = fight;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }


    /*public void setShape(){
        shapex[0] = (int) (pose.getX() + MathUtils.cos(0) * 20);
        shapey[0] = (int) (pose.getY() + MathUtils.sin(0) * 20);

        shapex[1] = (int) (pose.getX() + MathUtils.cos(- 3.1415f / 2) * 20);
        shapey[1] = (int) (pose.getY() + MathUtils.sin(- 3.1415f / 2) * 20);

        shapex[2] = (int) (pose.getX() + MathUtils.cos(3.1415f) * 20);
        shapey[2] = (int) (pose.getY() + MathUtils.sin(3.1415f) * 20);

        shapex[3] = (int) (pose.getX() + MathUtils.cos(3.1415f / 2) * 20);
        shapey[3] = (int) (pose.getY() + MathUtils.sin(3.1415f / 2) * 20);
    }
    public void update(float dt){
        setShape();
    }*/

    public boolean readyToShowPoints(){
        return readyToShowPoints;
    }
    public void setReadyToShowPoints(boolean readyToShowPoints){
        this.readyToShowPoints = readyToShowPoints;
    }

    public void setIncreasedPower(int pow){
        this.increasedPower = pow;
    }
    public int getIncreasedPower(){
        return increasedPower;
    }

    public boolean isPowerIncreased() {
        return isPowerIncreased;
    }
    public void setPowerIncreased(boolean pi){
        this.isPowerIncreased = pi;
    }

    public boolean isLuckIncreased() {
        return isLuckIncreased;
    }
    public void setLuckIncreased(boolean pi){
        this.isLuckIncreased = pi;
    }

    public boolean isSpeedIncreased() {
        return isSpeedIncreased;
    }
    public void setSpeedIncreased(boolean pi){
        this.isSpeedIncreased = pi;
    }


    public void setCheckSpecialField(boolean check){
        this.checkSpecialField = check;

    }
    public  boolean checkSpecialField(){
        return checkSpecialField;
    }
    public void setReadyToShowResult(boolean showResult){
        this.showResult = showResult;
    }
    public boolean isReadyToShowResult(){
        return showResult;

    }
    public  boolean isShow(){
        return show;
    }
    public void setShow(boolean show){
        this.show = show;
    }

    public void setNotWannaFight(boolean notWannaFight){this.notWannaFight = notWannaFight; }
    public  boolean notWannaFight(){
        return notWannaFight;
    }

}





