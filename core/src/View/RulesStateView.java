package View;

import Controller.States.RulesState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RulesStateView extends View {

    private RulesState rs;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;

    public RulesStateView(RulesState rs){
        this.rs = rs;
    }

    @Override
    public void init() {
        batch = new SpriteBatch();
        //font
        parameter1.size = 46;
        parameter1.characters = FONT_CHARS;
        font = generator.generateFont(parameter1);
        font.setColor(Color.BLACK);
        //title
        parameter2.size = 58;
        parameter2.characters = FONT_CHARS;
        fontTitle = generator.generateFont(parameter2);
        fontTitle.setColor(Color.BLACK);
    }

    @Override
    public void draw() {
        batch.begin();
        fontTitle.draw(batch, "Правила игры", 475, 690);
        font.draw(batch, rs.text, 30, 600);
        batch.end();
    }

    @Override
    public void update(float dt) {
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        fontTitle.dispose();
    }
}