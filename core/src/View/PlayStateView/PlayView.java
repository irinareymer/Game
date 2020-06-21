package View.PlayStateView;

import Controller.States.PlayState;
import View.View;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import Controller.MyGdxGame;

public class PlayView extends View {

    private PlayState ps;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Texture gameField;
    private DiceView diceView;
    private ItemsView itemsView;

    private Texture player1;
    private Texture player2;
    private PlayerView playerView1;
    private PlayerView playerView2;

    private TextPlayView textPlayView;
    private SetName setNameView;

    public PlayView(PlayState ps) {
        this.ps = ps;

    }

    @Override
    public void init(){
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        textPlayView = new TextPlayView();
        textPlayView.init();
        setNameView = new SetName();
        setNameView.init();

        player1 = new Texture("img/player1.png");
        playerView1 = new PlayerView();
        playerView1.init();

        player2 = new Texture("img/player2.png");
        playerView2 = new PlayerView();
        playerView2.init();

        gameField = new Texture("img/field.png");

        diceView = new DiceView();
        diceView.init();

        itemsView = new ItemsView();
        itemsView.init();
    }

    @Override
    public void draw()  {
        batch.begin();
        //field
        batch.draw(gameField,(MyGdxGame.WIDTH - gameField.getWidth()) / 2f, 0 );
        //players
        batch.draw(player1, 0,MyGdxGame.HEIGHT - player1.getHeight());
        batch.draw(player2,MyGdxGame.WIDTH - player2.getWidth(),MyGdxGame.HEIGHT - player2.getHeight());
        //dice
        batch.draw(diceView.diceImg(ps.getDice().getRolled()),(MyGdxGame.WIDTH - diceView.getDice().getWidth()) / 2f,
                MyGdxGame.HEIGHT  - 2 * diceView.getDice().getHeight());
        //items
        batch.draw(itemsView.itemImg(ps.getPlayer1().getCountItems()), player1.getWidth() + 10,
                MyGdxGame.HEIGHT - itemsView.getFoundItems().getHeight() - 10 - 50);
        batch.draw(itemsView.itemImg(ps.getPlayer2().getCountItems()),
                MyGdxGame.WIDTH - player2.getWidth() - itemsView.getFoundItems().getWidth() -10,
                MyGdxGame.HEIGHT - itemsView.getFoundItems().getHeight() - 10 - 50);
        batch.end();
        //pawn
        playerView1.draw(ps.getPlayer1().getPosition(),shapeRenderer, 10/255f, 0,41/255f,1);
        playerView2.draw(ps.getPlayer2().getPosition(),shapeRenderer,0, 126/255f, 143/255f,1);

        if(!ps.getPlayer2().isNameSet()) setNameView.draw(ps);
        if(ps.getPlayer2().isNameSet()) textPlayView.draw(ps);
    }

    @Override
    public void update(float dt)  {
        draw();
    }

    @Override
    public void dispose(){
        batch.dispose();
        shapeRenderer.dispose();
        textPlayView.dispose();
        setNameView.dispose();
    }
}