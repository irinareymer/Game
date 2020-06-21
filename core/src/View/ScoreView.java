package View;

import Controller.States.ScoreState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreView extends View {

    private ScoreState ss;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;
    private int[] scores;
    private String[] names;

    public ScoreView(ScoreState ss){
        this.ss = ss;
        this.scores = ss.getScores();
        this.names = ss.getNames();
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
        parameter2.size = 64;
        parameter2.characters = FONT_CHARS;
        fontTitle = generator.generateFont(parameter2);
        fontTitle.setColor(Color.BLACK);
    }

    @Override
    public void draw() {
        batch.begin();
        fontTitle.draw(batch, "Результаты игр:", 475, 650);
        for (int i = 0; i < scores.length; i++){
            String s = String.format("%2d. %5s %s", i + 1, scores[i], names[i]);
            font.draw(batch, s, 470,500 - 50 * i);
        }
        batch.end();
    }

    @Override
    public void update(float dt)  {
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        fontTitle.dispose();
    }
}