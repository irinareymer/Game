package Model.GameField;

import Model.Creatures.Position;
import Model.EntitiesManager;
import Model.PlayState;
import Controller.MyGdxGame;

import java.util.HashMap;
import java.util.Map;

public class Field {
    protected PlayState em;
    Map<Integer, Position> field = new HashMap<>();

    public Field(PlayState em) {
        this.em = em;
    }
    //map_field init
    public void init(){
        int c = (int) ((MyGdxGame.WIDTH - 880) / 2);
        field.put(0, new Position(c + 44,484));
        field.put(1, new Position(c + 132,484));
        field.put(2, new Position(c + 220,484));
        field.put(3, new Position(c + 308,484));
        field.put(4, new Position(c + 396,484));
        field.put(5, new Position(c + 484,484));
        field.put(6, new Position(c + 572,484));
        field.put(7, new Position(c + 660,484));
        field.put(8, new Position(c + 748,484));
        field.put(9, new Position(c + 836,484));
        field.put(10, new Position(c + 836,396));
        field.put(11, new Position(c + 836,308));
        field.put(12, new Position(c + 836,220));
        field.put(13, new Position(c + 836,132));
        field.put(14, new Position(c + 836,44));
        field.put(15, new Position(c + 748,44));
        field.put(16, new Position(c + 660,44));
        field.put(17, new Position(c + 572,44));
        field.put(18, new Position(c + 484,44));
        field.put(19, new Position(c + 396,44));
        field.put(20, new Position(c + 308,44));
        field.put(21, new Position(c + 220,44));
        field.put(22, new Position(c + 132,44));
        field.put(23, new Position(c + 44,44));
        field.put(24, new Position(c + 44,132));
        field.put(25, new Position(c + 44,220));
        field.put(26, new Position(c + 44,308));
        field.put(27, new Position(c + 44,396));
    }

    public Position getFieldPosition(int x){
       return field.getOrDefault(x % 28, new Position(0,0));
    }

}
