package pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


import game.AssetsJuego;

/**
 * Created by dalei on 14/02/2018.
 */

public class PantallaInicio implements Screen, InputProcessor {

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private Game juego;
    private boolean musicaOn;
    private float height;
    private float width;
    private float aniCrono = 0;
    private BitmapFont bitMapFont;

    public PantallaInicio(Game game){
        juego = game;
        camera = new OrthographicCamera();
        spriteBatch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        Gdx.input.setInputProcessor(this);
        Preferences prefs = Gdx.app.getPreferences("preferencias.dat");
        if(!prefs.contains("musicaOn")){
            musicaOn = true;
        }else{
            musicaOn = prefs.getBoolean("musicaOn");
        }
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/mad_college.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(width * 0.04f) ;
        bitMapFont = generator.generateFont(parameter);
        bitMapFont.setColor(Color.RED);
        generator.dispose();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.8f, 0.9f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        aniCrono += delta;
        spriteBatch.begin();
        if(musicaOn){
            spriteBatch.draw(AssetsJuego.sound, width * 0.85f, height * 0.80f,
                    width * 0.08f, height * 0.1f);
        }else{
            spriteBatch.draw(AssetsJuego.sound_inactive, width * 0.87f, height * 0.80f,
                    width * 0.06f, height * 0.1f);
        }
        spriteBatch.draw(AssetsJuego.play, width * 0.2f, height * 0.45f,
                width * 0.1f, height * 0.2f);
        spriteBatch.draw(AssetsJuego.scores, width * 0.45f, height * 0.45f,
                width * 0.1f, height * 0.2f);
        spriteBatch.draw(AssetsJuego.exit, width * 0.72f, height * 0.45f,
                width * 0.1f, height * 0.2f);
        bitMapFont.draw(spriteBatch, "MARTA ADVENTURE", width * 0.27f, height * 0.88f);
        spriteBatch.draw((Sprite) AssetsJuego.pjIdle.getKeyFrame(aniCrono, true), width * 0.05f, height * 0.01f,
                width * 0.1f, height * 0.3f);
        spriteBatch.draw((Sprite)AssetsJuego.zombieMale.getKeyFrame(aniCrono, true), width * 0.88f, height * 0.01f,
                -width * 0.1f, height * 0.3f);
        spriteBatch.end();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        spriteBatch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 temp = new Vector3(screenX,screenY,0);
        camera.unproject(temp);
        Circle dedo = new Circle(temp.x,temp.y,2);
        if(Intersector.overlaps(dedo, new Rectangle(width * 0.2f, height * 0.45f,
                width * 0.1f, height * 0.2f))){ // play
            dispose();
            juego.setScreen(new PantallaJuego(juego));
        }else if(Intersector.overlaps(dedo, new Rectangle(width * 0.45f, height * 0.45f,
                width * 0.1f, height * 0.2f))){ // scores
            dispose();
            juego.setScreen(new PantallaPuntuacion(juego));
        }else if(Intersector.overlaps(dedo, new Rectangle(width * 0.72f, height * 0.45f,
                width * 0.1f, height * 0.2f))){ // salir
            Gdx.app.exit();
        }else if(Intersector.overlaps(dedo, new Rectangle(width * 0.85f, height * 0.80f,
                width * 0.08f, height * 0.1f))){ // sonido
            musicaOn = !musicaOn;
            Preferences prefs = Gdx.app.getPreferences("preferencias.dat");
            prefs.putBoolean("musicaOn", musicaOn);
            prefs.flush();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
