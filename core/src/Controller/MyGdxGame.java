package Controller;

import Controller.StatesManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {

	public static float HEIGHT;
	public static float WIDTH;
	private StatesManager sm;
	//private ViewManager vm;
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		//Gdx.input.setInputProcessor(new GameInput());

		sm = new StatesManager();


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(218/255f, 196/255f, 154/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		try {
			sm.update(Gdx.graphics.getDeltaTime());
			//vm.update(Gdx.graphics.getDeltaTime());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//GameKeys.update();

	}

}