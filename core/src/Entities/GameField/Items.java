package Entities.GameField;

import Entities.Creatures.Position;
import Managers.EntitiesManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Items {
    protected EntitiesManager em;
    Texture foundItems;

    public Items(EntitiesManager em) {
        this.em = em;
    }

    public void init() {
        itemImg(0);
    }

    Random rndm = new Random();
    int itemsPositionByDice = rndm.nextInt(5) + 1;

    public boolean isItemFound(Position pose){
        Position item1 = em.getPlayState().getField().getFieldPosition(4 + itemsPositionByDice);
        Position item2 = em.getPlayState().getField().getFieldPosition(12 + itemsPositionByDice);
        Position item3 = em.getPlayState().getField().getFieldPosition(23 +itemsPositionByDice);
        return (pose == item1 || pose == item2 || pose == item3);
    }

    public Texture getFoundItems(){
        return foundItems;
    }

    public Texture itemImg(int count) {
        if (count == 0) foundItems = new Texture("img/items/items0.png");
        if (count == 1) foundItems = new Texture("img/items/items1.png");
        if (count == 2) foundItems = new Texture("img/items/items2.png");
        if (count == 3) foundItems = new Texture("img/items/items3.png");
        return foundItems;
    }

    public void update(float dt){
        itemImg(0);
    }

}
