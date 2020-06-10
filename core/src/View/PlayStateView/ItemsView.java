package View.PlayStateView;

import com.badlogic.gdx.graphics.Texture;

public class ItemsView {
    Texture foundItems;

    public void init() {
        itemImg(0);
    }

    public Texture itemImg(int count) {
        if (count == 0) foundItems = new Texture("img/items/items0.png");
        if (count == 1) foundItems = new Texture("img/items/items1.png");
        if (count == 2) foundItems = new Texture("img/items/items2.png");
        if (count == 3) foundItems = new Texture("img/items/items3.png");
        return foundItems;
    }
    public Texture getFoundItems(){
        return foundItems;
    }

    public void update(float dt){
        itemImg(0);
    }

}
