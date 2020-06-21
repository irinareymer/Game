package View.PlayStateView;

import Controller.MyGdxGame;
import Controller.States.PlayState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SetName {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    public void init(){
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        //font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/f1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 44;
        parameter1.characters = FONT_CHARS;
        font = generator.generateFont(parameter1);
        font.setColor(Color.BLACK);
    }

    public void draw(PlayState ps) {
        //set name for player1
        if (!ps.getPlayer1().isNameSet() && !ps.getPlayer1().isExit()) {
            batch.begin();
            font.draw(batch, "Игрок_1", 210, 710);
            font.draw(batch, "Игрок_2", MyGdxGame.WIDTH - 7 * 23 - 200, 710);
            font.draw(batch, "Игрок_1, давай установим твоё имя." + System.lineSeparator() +
                    "Используй стрелки, чтобы установить имя.", 320, 400);
            for (int i = 0; i < 10; i++) {
                font.draw(batch, Character.toString(ps.newName[i]), 450 + 35 * i, 250);
            }
            font.draw(batch, "Нажми SPACE, чтобы сменить игрока.", 350, 160);
            batch.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line(445 + 35 * ps.currentChar, 205, 470 + 35 * ps.currentChar, 205);
            shapeRenderer.end();
        }
        //set name for player2
        if (!ps.getPlayer2().isNameSet() && ps.getPlayer1().isNameSet() && !ps.getPlayer2().isExit()) {
            batch.begin();
            font.draw(batch, ps.getPlayer1().getName(), 210, 710);
            font.draw(batch, "Игрок_2", MyGdxGame.WIDTH - 7 * 23 - 200, 710);
            font.draw(batch, "Игрок_2, давай установим твоё имя." + System.lineSeparator() +
                    "Используй стрелки, чтобы установить имя.", 320, 400);
            for (int i = 0; i < 10; i++) {
                font.draw(batch, Character.toString(ps.newName[i]), 450 + 35 * i, 250);
            }
            font.draw(batch, "Нажми SPACE, чтобы начать игру.", 350, 160);
            batch.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line(445 + 35 * ps.currentChar, 205, 470 + 35 * ps.currentChar, 205);
            shapeRenderer.end();
        }
        //exit
        if(ps.getPlayer1().isExit() || ps.getPlayer2().isExit()){
            batch.begin();
            font.draw(batch,"Покинуть игру?",500,400);
            font.draw(batch, "Нажми Q, если хочешь покинуть игру." + System.lineSeparator()+
                    "(данные текущей игры не сохранятся)" +System.lineSeparator()+
                    "Нажми C, если хочешь продолжить.",325,320);
            if(!ps.getPlayer1().isNameSet()) {
                font.draw(batch, "Игрок_1", 210, 710);
                font.draw(batch, "Игрок_2", MyGdxGame.WIDTH - 7 * 23 - 200, 710);
            }
            else if(!ps.getPlayer2().isNameSet() && ps.getPlayer1().isNameSet()) {
                font.draw(batch, ps.getPlayer1().getName(), 210, 710);
                font.draw(batch, "Игрок_2", MyGdxGame.WIDTH - 7 * 23 - 200, 710);
            }
            batch.end();
        }
    }

    public void dispose(){
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}