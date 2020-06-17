package Controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {

	public static float HEIGHT;
	public static float WIDTH;
	private StatesManager sm;

	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		sm = new StatesManager();
	}

	public void render () {
		Gdx.gl.glClearColor(218/255f, 196/255f, 154/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
	}
}
