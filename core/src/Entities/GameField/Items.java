package Entities.GameField;

import Managers.EntitiesManager;
import com.badlogic.gdx.graphics.Texture;

public class Items {
    protected EntitiesManager em;
    Texture foundItems;

    public Items(EntitiesManager em) {
        this.em = em;
    }

    public void init() {
        itemImg(0);
    }

    //todo items update()

    public Texture getFoundItems(){
        return foundItems;
    }

    public Texture itemImg(int count) {
        //todo (need correct img)
        if (count == 0) foundItems = new Texture("img/dice/dice1.png");
        if (count == 1) foundItems = new Texture("img/dice/dice1.png");
        if (count == 2) foundItems = new Texture("img/dice/dice1.png");
        if (count == 3) foundItems = new Texture("img/dice/dice1.png");
        return foundItems;
    }



}
