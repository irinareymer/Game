package View;

import Controller.States.MenuState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MenuStateView extends View {

    MenuState ms;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;
    private String[] menuItems;

    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    public MenuStateView(MenuState ms){
        this.ms = ms;
    }

    public void init(){
        batch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/f1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 56;
        parameter1.characters  = FONT_CHARS;
        font = generator.generateFont(parameter1);
        font.setColor(Color.BLACK);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 78;
        parameter2.characters  = FONT_CHARS;
        fontTitle = generator.generateFont(parameter2);
        fontTitle.setColor(Color.BLACK);
        menuItems = new String[]{
                "Начать игру",
                "Правила",
                "Результаты игр",
                "Выход"
        };
    }

    public void update(float dt) {
        draw();
    }

    public void draw() {
        batch.begin();
        fontTitle.draw(batch, "РОДДЕРБИ!", 425, 600);
        int height = 525;
        for (int i = 0; i < menuItems.length; i++){
            if(ms.currentItem == i) font.setColor(Color.RED);
            else font.setColor(Color.BLACK);
            height -= 75;
            font.draw(batch, menuItems[i], 450,height);
        }
        batch.end();
    }

    public void dispose(){
        batch.dispose();
        fontTitle.dispose();
        font.dispose();
    }
}