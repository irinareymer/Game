package Model;

import Controller.StatesManager;

public abstract class State {
    protected StatesManager sm;

    protected State(StatesManager sm){
        this.sm = sm;
        init();
    }
    public abstract void init();
    public abstract void update(float dt) throws InterruptedException;
    public abstract void dispose();
    //public abstract void input();

    public abstract State getCurrentState();

}
