package States;

import Managers.StatesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MenuState extends State { //todo all!

    private static GlyphLayout glyphLayout = new GlyphLayout();

    private SpriteBatch spriteBatch;
    private BitmapFont titleFont;
    private BitmapFont font;

    private final String title = "GGame!";

    private String[] menuItems;

    public MenuState(StatesManager sm){
        super(sm);
    }

    public void init(){
        spriteBatch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/f1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 56;
        titleFont = generator.generateFont(parameter1);
        titleFont.setColor(Color.NAVY);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 20;
        font = generator.generateFont(parameter2);

        menuItems = new String[]{
                "New Game",
                "Rules",
                "Stats",
                "Quit"
        };

    }
    public void update(float dt){
    }
    public void draw(){
        //spriteBatch.begin();
       // float width = titleFont.getBounds(title).width;
    }
    public void dispose(){
    }
}
