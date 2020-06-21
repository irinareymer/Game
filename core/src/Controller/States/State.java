package Controller.States;

import Controller.StatesManager;

public abstract class State {
    protected StatesManager sm;

    protected State(StatesManager sm){
        this.sm = sm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);

}