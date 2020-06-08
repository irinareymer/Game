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


    public PlayState(StatesManager sm){
        super(sm);
    }

     public void init(){
        // em = new EntitiesManager(this);

         ready = false;
         showResultOfFight = false;
         player1 = new Player(this, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484 + 20));
         player2 = new Player(this, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484));
         player1.setName("NIKESH");
         player2.setName("RINIREY");
         player2.setExit(false);
         player1.setExit(false);

         logic = new GameLogic(this);
         logic.init();

        dice = new Dice(this);
        field = new Field(this);
        field.init();
        item = new Items(this);
        monster = new Monster(this);

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
      /*  player1.setYes(GameKeys.isPressed(GameKeys.Y));
        player1.setNo(GameKeys.isPressed(GameKeys.N));
        player1.setEnter(GameKeys.isPressed(GameKeys.ENTER));
        player1.setSpace(GameKeys.isPressed(GameKeys.SPACE));

        player2.setYes(GameKeys.isPressed(GameKeys.Y));
        player2.setNo(GameKeys.isPressed(GameKeys.N));
        player2.setEnter(GameKeys.isPressed(GameKeys.ENTER));
        player2.setSpace(GameKeys.isPressed(GameKeys.SPACE)); */
    }

    @Override
    public void dispose() {

    }
}
