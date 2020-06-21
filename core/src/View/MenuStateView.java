package View;

import Controller.States.MenuState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuStateView extends View {

    private MenuState ms;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;
    private String[] menuItems;

    public MenuStateView(MenuState ms){
        this.ms = ms;
    }

    @Override
    public void init(){
        batch = new SpriteBatch();
        //font
        parameter1.size = 56;
        parameter1.characters = FONT_CHARS;
        font = generator.generateFont(parameter1);
        font.setColor(Color.BLACK);
        //title
        parameter2.size = 78;
        parameter2.characters = FONT_CHARS;
        fontTitle = generator.generateFont(parameter2);
        fontTitle.setColor(Color.BLACK);
        //menu
        menuItems = new String[]{
                "Начать игру",
                "Правила",
                "Результаты игр",
                "Выход"
        };
    }

    @Override
    public void draw() {
        batch.begin();
        fontTitle.draw(batch, "РОДДЕРБИ!", 425, 600);
        int height = 525;
        for (int i = 0; i < menuItems.length; i++){
            if(ms.getCurrentItem() == i) font.setColor(Color.RED);
            else font.setColor(Color.BLACK);
            height -= 75;
            font.draw(batch, menuItems[i], 450,height);
        }
        batch.end();
    }

    @Override
    public void update(float dt) {
        draw();
    }

    @Override
    public void dispose(){
        batch.dispose();
        fontTitle.dispose();
        font.dispose();
    }
}