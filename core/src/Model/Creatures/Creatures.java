package Model.Creatures;

public class Creatures {

    protected int power ;
    protected int luck ;
    protected int speed ;

    public void setPower(int p){
        power = p;
    }
    public int getPower(){
        return power;
    }

    public void setSpeed(int s){
        speed = s;
    }
    public int getSpeed(){
        return speed;
    }

    public void setLuck(int l){
        luck = l;
    }
    public int getLuck(){
        return luck;
    }
}
