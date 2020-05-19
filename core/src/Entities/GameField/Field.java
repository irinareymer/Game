package Entities.GameField;

import Entities.Creatures.Position;
import Managers.EntitiesManager;
import com.mygdx.game.MyGdxGame;

import java.util.HashMap;
import java.util.Map;

public class Field {
    protected EntitiesManager em;

    public Field(EntitiesManager em) {
        this.em = em;
        Map<Integer, Position> field = new HashMap<>();

        //map_field init
        field.put(0, new Position(((MyGdxGame.WIDTH / 4) - 25), ((MyGdxGame.HEIGHT / 2) + 100)));
        for (int i = 1; i <= 7; i++) {
            field.put(i, new Position(field.getOrDefault(i - 1, new Position(0,
                    0)).getX() + 50, ((MyGdxGame.HEIGHT / 2) + 100)));
        }
        for (int i = 8; i <= 12; i++) {
            field.put(i, new Position(field.getOrDefault(7, new Position(0, 0)).getX(),
                    field.getOrDefault(i - 1, new Position(0, 0)).getY() - 100));
        }
        for (int i = 13; i <= 19; i++) {
            field.put(i, new Position(field.getOrDefault(i - 1, new Position(0, 0)).getX() - 50,
                    field.getOrDefault(12, new Position(0, 0)).getY()));
        }
        for (int i = 20; i <= 23; i++) {
            field.put(i, new Position(field.getOrDefault(19, new Position(0, 0)).getX(),
                    field.getOrDefault(i - 1, new Position(0, 0)).getY() + 100));
        }

    }
}
