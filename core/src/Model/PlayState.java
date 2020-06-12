package Model;


import Controller.MyGdxGame;
import Controller.StatesManager;
import Model.Creatures.Monster;
import Model.Creatures.Player;
import Model.Creatures.Position;
import Model.GameField.Dice;
import Model.GameField.Field;
import Model.GameField.Items;
import View.PlayStateView.PlayView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.Arrays;

public class PlayState extends State  {

   // EntitiesManager em;
    Player player1;
    Player player2;
    GameLogic logic;
    Field field;
    Items item;
    Monster monster;
    Dice dice;
    Player currentPlayer;
    Boolean ready;
    Boolean showResultOfFight;
    public char[] newName;
    public int currentChar;


    public PlayState(StatesManager sm){
        super(sm);
    }

     public void init(){
        // em = new EntitiesManager(this);

         ready = false;
         showResultOfFight = false;
         player1 = new Player(this, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484 + 20));
         player2 = new Player(this, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484));
         player1.setName("----------");
         player2.setName("----------");
         player2.setExit(false);
         player1.setExit(false);
         newName = new char[] {'A','A','A','A','A','A','A','A','A','A'};
         currentChar = 0;

         logic = new GameLogic(this);
         logic.init();

        dice = new Dice(this);
        field = new Field(this);
        field.init();
        item = new Items(this);
        monster = new Monster(this);

     }

    public void inputName(Player player){
        setCurrentPlayer(player);
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(newName[currentChar] == '_'){
                newName[currentChar]='Z';
            }
            else {
                newName[currentChar]--;
                if(newName[currentChar] < 'A'){
                    newName[currentChar] = '_';
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(newName[currentChar] == '_'){
                newName[currentChar]='A';
            }
            else {
                newName[currentChar]++;
                if(newName[currentChar] > 'Z'){
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
            newName = new char[] {'A','A','A','A','A','A','A','A','A','A'};
            currentChar = 0;
            player.setIsNameSet(true);
        }
    }


    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
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

    public void setReady(boolean ready){
        this.ready = ready;
    }
    public boolean isReady(){
        return ready;
    }
    public void setShowResultOfFight(boolean show){
        this.showResultOfFight = show;
    }

    public Boolean showResultOfFight() {
        return showResultOfFight;
    }

    @Override
    public State getCurrentState() {
        return this;
    }

    public void update (float dt) throws InterruptedException {
        logic.update(dt);
    }

    public StatesManager getSM(){
        return sm;
    }

    public void input(){
    }

    @Override
    public void dispose() {

    }
}
