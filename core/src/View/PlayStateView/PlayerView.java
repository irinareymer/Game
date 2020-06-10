package View.PlayStateView;

import Controller.MyGdxGame;
import Model.Creatures.Player;
import Model.Creatures.Position;
import Model.EntitiesManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class PlayerView {

    //EntitiesManager em;
    //Player player;
    private int[] shapex;
    private int[] shapey;
    Position pose;


    public PlayerView() {
        //this.em = em;
        //this.player = player;
        shapex = new int[4];
        shapey = new int[4];
    }

    public void init(){
        pose = new Position(0,0);
        shapex[0] = (int)(((int)(MyGdxGame.WIDTH - 880) / 2) + MathUtils.cos(0) * 20);
        shapey[0] = (int) (484 + MathUtils.sin(0) * 20);

        shapex[1] = (int) (((int)(MyGdxGame.WIDTH - 880) / 2) + MathUtils.cos(- 3.1415f / 2) * 20);
        shapey[1] = (int) (484 + MathUtils.sin(- 3.1415f / 2) * 20);

        shapex[2] = (int) (((int)(MyGdxGame.WIDTH - 880) / 2) + MathUtils.cos(3.1415f) * 20);
        shapey[2] = (int) (484 + MathUtils.sin(3.1415f) * 20);

        shapex[3] = (int) (((int)(MyGdxGame.WIDTH - 880) / 2) + MathUtils.cos(3.1415f / 2) * 20);
        shapey[3] = (int) (484 + MathUtils.sin(3.1415f / 2) * 20);
    }

    public void setShape(){

    }

    public void draw(Position pose, ShapeRenderer shapeRenderer, float r, float g, float b, int a){
        shapeRenderer.setColor(r,g,b,a);
        shapex[0] = (int) (pose.getX() + MathUtils.cos(0) * 20);
        shapey[0] = (int) (pose.getY() + MathUtils.sin(0) * 20);

        shapex[1] = (int) (pose.getX() + MathUtils.cos(- 3.1415f / 2) * 20);
        shapey[1] = (int) (pose.getY() + MathUtils.sin(- 3.1415f / 2) * 20);

        shapex[2] = (int) (pose.getX() + MathUtils.cos(3.1415f) * 20);
        shapey[2] = (int) (pose.getY() + MathUtils.sin(3.1415f) * 20);

        shapex[3] = (int) (pose.getX() + MathUtils.cos(3.1415f / 2) * 20);
        shapey[3] = (int) (pose.getY() + MathUtils.sin(3.1415f / 2) * 20);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++){
            shapeRenderer.rectLine(shapex[i], shapey[i], shapex[j], shapey[j], 15);
        }
        shapeRenderer.end();
    }

    public void update(float dt){
        //draw();
    }
}



