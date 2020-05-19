package Entities.Creatures;

import Managers.EntitiesManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Player extends Creatures {

    protected EntitiesManager em;
    private float[] shapex;
    private float[] shapey;
    String name;
    int countItems = 0;
    Position pose;

    public Player(EntitiesManager em, Position pose){
        this.pose = pose;
        this.em = em;
        shapex = new float[4];
        shapey = new float[4];
    }

    public void init() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPower(int p){
        power = p;
    }
    public void setSpeed(int s){
        speed = s;
    }
    public void setLuck(int l){
        luck = l;
    }

    public int getPower(){
        return power;
    }

    public int getSpeed(){
        return speed;
    }

    public int getLuck(){
        return luck;
    }

    public int getCountItems() {
        return countItems;
    }

    public void setPose(Position pose) {
        this.pose = pose;
    }

    public Position getPosition(Player player) {
        return player.pose;
    }

    public void setShape(){
        shapex[0] = pose.getX() + MathUtils.cos(0) * 20;
        shapey[0] = pose.getY() + MathUtils.sin(0) * 20;

        shapex[1] = pose.getX() + MathUtils.cos(- 3.1415f / 2) * 20;
        shapey[1] = pose.getY() + MathUtils.sin(- 3.1415f / 2) * 20;

        shapex[2] = pose.getX() + MathUtils.cos(3.1415f) * 20;
        shapey[2] = pose.getY() + MathUtils.sin(3.1415f) * 20;

        shapex[3] = pose.getX() + MathUtils.cos(3.1415f / 2) * 20;
        shapey[3] = pose.getY() + MathUtils.sin(3.1415f / 2) * 20;
    }

    public void update(float dt){
        setShape();
    }

    public void draw(ShapeRenderer shapeRenderer, float r, float g, float b, int a){
        shapeRenderer.setColor(r,g,b,a);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++){
            shapeRenderer.rectLine(shapex[i], shapey[i], shapex[j], shapey[j], 15);
        }
        shapeRenderer.end();
    }
}
