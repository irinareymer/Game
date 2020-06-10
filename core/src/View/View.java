package View;

//import Controller.ViewManager;

public abstract class View {
   // protected ViewManager vm;

    protected View(){
        //this.vm = vm;
        init();
    }
    public abstract void init();
    public abstract void update(float dt) throws InterruptedException;
    public abstract void draw() throws InterruptedException;
    public abstract void dispose();

}

