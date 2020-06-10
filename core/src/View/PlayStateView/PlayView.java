package View.PlayStateView;

//import Controller.ViewManager;

import Model.EntitiesManager;
import Model.PlayState;
import View.View;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import Controller.MyGdxGame;

public class PlayView extends View {

   // EntitiesManager em;
    PlayState ps;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    Texture gameField;
    Texture p1;
    Texture p2;
    DiceView dw;
    PlayerView pw1;
    PlayerView pw2;
    ItemsView iw;
    TextPlayView textw;
    

    public PlayView(PlayState ps) {
        //super(vm);
        //System.out.println("here");
        this.ps = ps;
        //this.playState = playState;
    }



    public void init(){

        //em = new EntitiesManager(playState);

        shapeRenderer = new ShapeRenderer();
        textw = new TextPlayView();
        textw.init();
        //textw = new View.PlayStateView.TextView();

        //player1 = new Player(em, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484 + 20));
        //player2 = new Player(em, new Position((int)((MyGdxGame.WIDTH - 880) / 2) + 44, 484));

        pw1 = new PlayerView();
        pw1.init();
        pw2 = new PlayerView();
        pw2.init();


        batch = new SpriteBatch();
        gameField = new Texture("img/field.png");
        p1 = new Texture("img/player1.png");
        p2 = new Texture("img/player2.png");

        //dice = new Dice(em);
        dw = new DiceView();
        dw.init();

        iw = new ItemsView();
        iw.init();

    }


    public void update(float dt) throws InterruptedException {
        //ps.update(dt);

        //textw.update(dt);
        //pw1.update(dt);
        //pw2.update(dt);


        //dw.update(dt);
        //iw.update(dt);
        draw();


    }

    public void draw() throws InterruptedException {
        batch.begin();
        //field
        batch.draw(gameField,(MyGdxGame.WIDTH - gameField.getWidth()) / 2f, 0 );
        //players
        batch.draw(p1, 0,MyGdxGame.HEIGHT - p1.getHeight());
        batch.draw(p2,MyGdxGame.WIDTH - p2.getWidth(),MyGdxGame.HEIGHT - p2.getHeight());
        //dice
        batch.draw(dw.diceImg(ps.getDice().getRolled()),(MyGdxGame.WIDTH - dw.getDice().getWidth()) / 2f,
                (MyGdxGame.HEIGHT  - 2 * dw.getDice().getHeight()));
        //items
        batch.draw(iw.itemImg(ps.getPlayer1().getCountItems()),p1.getWidth() + 10,MyGdxGame.HEIGHT - iw.getFoundItems().getHeight() - 10 - 50);
        batch.draw(iw.itemImg(ps.getPlayer2().getCountItems()),MyGdxGame.WIDTH - p2.getWidth() - iw.getFoundItems().getWidth() -10,
                MyGdxGame.HEIGHT - iw.getFoundItems().getHeight() - 10 - 50);
        batch.end();
        //player on field
        pw1.draw(ps.getPlayer1().getPosition(),shapeRenderer, 10/255f, 0,41/255f,1);
        pw2.draw(ps.getPlayer2().getPosition(),shapeRenderer,0, 126/255f, 143/255f,1);
        //if(playState.getPlayer1().isParametersSet()).
        if(ps.getCurrentPayer() != null) textw.draw(ps);
    }

    public void dispose(){
        batch.dispose();
        gameField.dispose();
    }





}