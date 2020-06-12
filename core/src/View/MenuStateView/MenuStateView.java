package View.MenuStateView;

import Model.MenuState;
import View.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    private int currentItem;
    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";



    public MenuStateView(MenuState ms){
        this.ms = ms;
        //this.currentItem = currentItem;
    }

    public void init(){
        currentItem = 0;
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


    public void update(float dt) throws InterruptedException {
        draw();
       // ms.update(dt);
        input();

    }

    public void draw() throws InterruptedException {
        batch.begin();
        fontTitle.draw(batch, "РОДДЕРБИ!", 425, 600);
        int height = 525;
        for (int i = 0; i < menuItems.length; i++){
            if(currentItem == i) font.setColor(Color.RED);
            else font.setColor(Color.BLACK);
            height -= 75;
            font.draw(batch, menuItems[i], 450,height);
        }
        batch.end();
    }

    public void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(currentItem >0) currentItem--;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(currentItem < 3) currentItem++;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            ms.select(currentItem);
        }
    }




    public void dispose(){
        batch.dispose();
        fontTitle.dispose();
        font.dispose();
    }





}

