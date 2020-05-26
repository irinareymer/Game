package States;

import Entities.Creatures.Monster;
import Entities.Creatures.Player;
import Entities.Creatures.Position;
import Entities.GameField.Dice;
import Entities.GameField.Field;
import Entities.GameField.Items;
import Logic.GameLogic;
import Managers.EntitiesManager;
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
    Field field;
    Items item;
    Monster monster;

    public PlayState(StatesManager sm){
        super(sm);
    }

    public void init(){
        em = new EntitiesManager(this);

        shapeRenderer = new ShapeRenderer();
        player1 = new Player(em, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484 + 20));
        player2 = new Player(em, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484));

        batch = new SpriteBatch();
        gameField = new Texture("img/field.png");
        p1 = new Texture("img/player1.png");
        p2 = new Texture("img/player2.png");

        dice = new Dice(em);
        dice.init();

        item = new Items(em);
        item.init();

        logic = new GameLogic(em);
        logic.init();

        field = new Field(em);
        field.init();

        monster = new Monster(em);
    }

    public void update(float dt) throws InterruptedException {
        em.update(dt);

        player1.update(dt);
        player2.update(dt);

        dice.update(dt);
        item.update(dt);

        logic.update(dt);

    }

    public void draw(){
        batch.begin();
        //field
        batch.draw(gameField,(MyGdxGame.WIDTH - gameField.getWidth()) / 2f, 0 );
        //players
        batch.draw(p1, 0,MyGdxGame.HEIGHT - p1.getHeight());
        batch.draw(p2,MyGdxGame.WIDTH - p2.getWidth(),MyGdxGame.HEIGHT - p2.getHeight());
        //dice
        batch.draw(dice.getDice(),(MyGdxGame.WIDTH - dice.getDice().getWidth()) / 2f,
                (MyGdxGame.HEIGHT  - 2 * dice.getDice().getHeight()));
        //items
        batch.draw(item.itemImg(player1.getCountItems()),p1.getWidth() + 10,MyGdxGame.HEIGHT - item.getFoundItems().getHeight() - 10);
        batch.draw(item.itemImg(player2.getCountItems()),MyGdxGame.WIDTH - p2.getWidth() - item.getFoundItems().getWidth() -10,
                MyGdxGame.HEIGHT - item.getFoundItems().getHeight() - 10);
        batch.end();
        //player on field
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
    public Field getField() {
        return field;
    }
    public Items getItems(){
        return item;
    }
    public Monster getMonster(){return monster;}
}
