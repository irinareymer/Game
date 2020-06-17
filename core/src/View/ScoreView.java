package View;

import Controller.States.ScoreState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ScoreView extends View {

    ScoreState ss;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;

    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    public ScoreView(ScoreState ss){
        this.ss = ss;
    }

    public void init() {
        batch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/f1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 46;
        parameter1.characters  = FONT_CHARS;
        font = generator.generateFont(parameter1);
        font.setColor(Color.BLACK);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 64;
        parameter2.characters  = FONT_CHARS;
        fontTitle = generator.generateFont(parameter2);
        fontTitle.setColor(Color.BLACK);
    }

    public void update(float dt)  {
        draw();
    }

    public void draw() {
        batch.begin();
        fontTitle.draw(batch, "Результаты игр:", 475, 650);
        for (int i = 0; i < ss.scores.length; i++){
            String s = String.format("%2d. %5s %s", i + 1, ss.scores[i], ss.names[i]);
            font.draw(batch, s, 470,500 - 50*i);
        }
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        fontTitle.dispose();
    }
}