package Model.GameField;

import Model.Creatures.Position;
import Model.EntitiesManager;
import Model.PlayState;

import java.util.Random;

public class Items {
    protected PlayState em;

    public Items(PlayState em) {
        this.em = em;
    }

    Random rndm = new Random();
    int itemsPositionByDice = rndm.nextInt(5) + 1;

    public boolean isItemFound(Position pose){
        int first = 4 + itemsPositionByDice;
        int second = 12 + itemsPositionByDice;
        int third = 23 + itemsPositionByDice;
        if (first == 9 || first ==14 || first == 23) first++;
        if (second == 9 || second ==14 || second == 23) second++;
        if (third == 9 || third ==14 || third == 23) third++;

        Position item1 = em.getField().getFieldPosition(first);
        Position item2 = em.getField().getFieldPosition(second);
        Position item3 = em.getField().getFieldPosition(third);
        return (pose == item1 || pose == item2 || pose == item3);
    }

}
