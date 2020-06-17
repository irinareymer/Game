package View;

public abstract class View {

    protected View(){
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw() ;
    public abstract void dispose();
}

