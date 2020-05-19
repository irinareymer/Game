package States;

import Entities.Creatures.Player;
import Entities.Creatures.Position;
import Entities.GameField.Dice;
import Entities.GameField.Items;
import Logic.GameLogic;
import Managers.EntitiesManager;
import Managers.KeysManager;
import Managers.StatesManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;

public class PlayState extends State {

    EntitiesManager em;
    GameLogic logic;
    private ShapeRenderer shapeRenderer;
    private Player player1;
    private Player player2;
    private SpriteBatch batch;
    Texture gameField;
    Texture p1;
    Texture p2;
    Dice dice;
    Items itemsPl1;
    Items itemsPl2;
    KeysManager km;

    public PlayState(StatesManager sm){
        super(sm);
    }

    public void init(){

        shapeRenderer = new ShapeRenderer();
        em = new EntitiesManager(this);
        player1 = new Player(em,new Position(((MyGdxGame.WIDTH * 3 / 4) + 30), ((MyGdxGame.HEIGHT / 2) - 30)));
        player2 = new Player(em,new Position(((MyGdxGame.WIDTH / 4) - 25), ((MyGdxGame.HEIGHT / 2) + 55)));
        player1.init();
        player2.init();
        batch = new SpriteBatch();
        gameField = new Texture("img/field1.png");
        p1 = new Texture("img/player1.png");
        p2 = new Texture("img/player2.png");
        dice = new Dice(em);
        dice.init();
        logic = new GameLogic(em);
        itemsPl1 = new Items(em);
        itemsPl2 = new Items(em);
        itemsPl1.init();
        itemsPl2.init();

        km = new KeysManager(em);
        logic.init();
    }

    public void update(float dt){

        player1.update(dt);
        player2.update(dt);
        em.update(dt);
        dice.update(dt);
        logic.update(dt);
        km.update(dt);

    }

    public void draw(){
        batch.begin();
        batch.draw(gameField,(MyGdxGame.WIDTH - gameField.getWidth()) / 2f, MyGdxGame.HEIGHT / 18 );
        batch.draw(p1, 0,MyGdxGame.HEIGHT - p1.getHeight());
        batch.draw(p2,MyGdxGame.WIDTH - p2.getWidth(),MyGdxGame.HEIGHT - p2.getHeight());
        batch.draw(dice.getDice(),(MyGdxGame.WIDTH - dice.getDice().getWidth()) / 2f,(MyGdxGame.HEIGHT  - 2 * dice.getDice().getHeight()));
        batch.draw(itemsPl1.getFoundItems(),0,MyGdxGame.HEIGHT - 2 * p1.getHeight() + 20);
        batch.draw(itemsPl2.getFoundItems(),MyGdxGame.WIDTH - p2.getWidth(),MyGdxGame.HEIGHT - 2 * p2.getHeight() + 20);
        batch.end();
        player1.draw(shapeRenderer, 10/255f, 0,41/255f,1);
        player2.draw(shapeRenderer,0, 126/255f, 143/255f,1);

    }

    public void dispose(){
        batch.dispose();
        gameField.dispose();
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

    public Items getItemsPl1() {
        return itemsPl1;
    }

    public Items getItemsPl2() {
        return itemsPl2;
    }

    public KeysManager getKm() {
        return km;
    }

    public GameLogic getLogic() {
        return logic;
    }
}
