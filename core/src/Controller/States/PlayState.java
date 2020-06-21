package Controller.States;

import Controller.GameLogic;
import Controller.MyGdxGame;
import Controller.StatesManager;
import Model.Creatures.Monster;
import Model.Creatures.Player;
import Model.Creatures.Position;
import Model.GameField.Dice;
import Model.GameField.Field;
import Model.GameField.Items;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayState extends State {

    private Player player1;
    private Player player2;
    private GameLogic logic;
    private Field field;
    private Items item;
    private Monster monster;
    private Dice dice;
    private Player currentPlayer;
    private Boolean ready;

    public char[] newName;
    public int currentChar;

    public PlayState(StatesManager sm){
        super(sm);
    }

    @Override
    public void init(){

        player1 = new Player(this, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484 + 20));
        currentPlayer = player1;
        player2 = new Player(this, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484));
        player1.setName("----------");
        player2.setName("----------");
        player1.setExit(false);
        player2.setExit(false);
        newName = new char[] {'А','А','А','А','А','А','А','А','А','А'};
        currentChar = 0;

        monster = new Monster(this);
        logic = new GameLogic(this);
        field = new Field(this);
        field.init();
        dice = new Dice(this);
        item = new Items(this);

        ready = false;
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void changeCurrentPlayer(Player currentPlayer){
        if (currentPlayer == getPlayer1()) this.currentPlayer = getPlayer2();
        else this.currentPlayer = getPlayer1();
    }

    public Dice getDice() {
        return dice;
    }
    public Field getField() {
        return field;
    }
    public Items getItems(){
        return item;
    }
    public Monster getMonster(){return monster;}

    public void setReady(boolean ready){
        this.ready = ready;
    }
    public boolean isReady(){
        return ready;
    }

    public StatesManager getSM(){
        return sm;
    }

    //exit
    public void isWannaExit(Player player){
        boolean yes = Gdx.input.isKeyJustPressed(Input.Keys.Q);
        boolean no = Gdx.input.isKeyJustPressed(Input.Keys.C);
        if(yes){
            sm.setState(StatesManager.TypeState.MENU);
        }
        else if(no) {
            player.setExit(false);
        }
    }

    //set name
    public void inputName(Player player){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            player.setExit(true);
            isWannaExit(player);
        }
        setCurrentPlayer(player);
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(newName[currentChar] == '_'){
                newName[currentChar]='Я';
            }
            else {
                newName[currentChar]--;
                if(newName[currentChar] < 'А'){
                    newName[currentChar] = '_';
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(newName[currentChar] == '_'){
                newName[currentChar]='А';
            }
            else {
                newName[currentChar]++;
                if(newName[currentChar] > 'Я'){
                    newName[currentChar] = '_';
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if(currentChar < 9){
                currentChar++;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(currentChar > 0){
                currentChar--;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                name.append(newName[i]);
            }
            player.setName(name.toString());
            newName = new char[] {'А','А','А','А','А','А','А','А','А','А'};
            currentChar = 0;
            player.setIsNameSet(true);
        }
    }

    @Override
    public void update (float dt) {
        //game logic
        logic.update();

        //exit
        boolean exit = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        if(exit) {
            getCurrentPayer().setExit(true);
        }
        if(getCurrentPayer().isExit()){
            isWannaExit(getCurrentPayer());
        }
    }
}
