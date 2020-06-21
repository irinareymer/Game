package Controller.States;

import Controller.StatesManager;

public abstract class State {
    protected StatesManager sm;

    protected State(StatesManager sm){
        this.sm = sm;
        init();
    }

    public void init() {}
    public void update(float dt) {}

}