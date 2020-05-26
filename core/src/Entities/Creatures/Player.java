package Entities.Creatures;

import Managers.EntitiesManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Player extends Creatures {

    protected EntitiesManager em;

    private int[] shapex;
    private int[] shapey;
    String name;
    Position pose;
    int currentField = 0;
    int countItems = 0;
    boolean blocked = false;
    boolean wannaFight = false;
    boolean fight = false;

    public Player(EntitiesManager em, Position pose){
        this.pose = pose;
        this.em = em;
        shapex = new int[4];
        shapey = new int[4];
    }

    //name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    //parameters
    public boolean isParametersSet(){
        return luck != 0 && speed != 0 && power != 0;
    }

    //items
    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }
    public int getCountItems() {
        return countItems;
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
    public boolean isWannaFight(){return wannaFight;}
    public void setWannaFight(boolean wannaFight){this.wannaFight = wannaFight;}
    public boolean isFight(){return fight;}
    public void setFight(boolean fight){this.fight = fight;}




    public void setShape(){
        shapex[0] = (int) (pose.getX() + MathUtils.cos(0) * 20);
        shapey[0] = (int) (pose.getY() + MathUtils.sin(0) * 20);

        shapex[1] = (int) (pose.getX() + MathUtils.cos(- 3.1415f / 2) * 20);
        shapey[1] = (int) (pose.getY() + MathUtils.sin(- 3.1415f / 2) * 20);

        shapex[2] = (int) (pose.getX() + MathUtils.cos(3.1415f) * 20);
        shapey[2] = (int) (pose.getY() + MathUtils.sin(3.1415f) * 20);

        shapex[3] = (int) (pose.getX() + MathUtils.cos(3.1415f / 2) * 20);
        shapey[3] = (int) (pose.getY() + MathUtils.sin(3.1415f / 2) * 20);
    }

    public void draw(ShapeRenderer shapeRenderer, float r, float g, float b, int a){
        shapeRenderer.setColor(r,g,b,a);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++){
            shapeRenderer.rectLine(shapex[i], shapey[i], shapex[j], shapey[j], 15);
        }
        shapeRenderer.end();
    }

    public void update(float dt){
        setShape();
    }
}
