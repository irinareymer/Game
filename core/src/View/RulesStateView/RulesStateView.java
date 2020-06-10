package View.RulesStateView;

import Model.RulesState;
import View.View;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.w3c.dom.Text;

public class RulesStateView extends View {

    RulesState rs;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;
    FileHandle rules = Gdx.files.internal("rules.txt");
    String text = rules.readString("UTF-8");

    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";


    public RulesStateView(RulesState rs){
        this.rs = rs;
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
        parameter2.size = 58;
        parameter2.characters  = FONT_CHARS;
        fontTitle = generator.generateFont(parameter2);
        fontTitle.setColor(Color.BLACK);
    }


    public void update(float dt) throws InterruptedException {
        draw();
    }


    public void draw() throws InterruptedException {
        batch.begin();
        fontTitle.draw(batch, "Правила игры", 475, 690);
        font.draw(batch,text, 30, 600);
        batch.end();
    }


    public void dispose() {
        batch.dispose();
    }
}
