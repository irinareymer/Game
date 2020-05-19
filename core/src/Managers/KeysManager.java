package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeysManager {
    EntitiesManager em;
    boolean keyPressed = false;
    boolean action = false;
    public KeysManager(EntitiesManager em) {
        this.em = em;
    }


    public void update(float dt) {
        action = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
    }

    public boolean isAction() {
        return action;
    }
}
