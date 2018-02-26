package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import game.AssetsJuego;
import game.B2DVars;
import game.Utiles;
import modelo.Mundo;
import modelo.PersonajeJugable;

/**
 * Created by dalei on 14/02/2018.
 */

public class Renderer implements InputProcessor{

    private Mundo mundo;
    private boolean debugger=false;
    private SpriteBatch spritebatch;
    private OrthographicCamera camera;
    private ShapeRenderer shaperender;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;
    private int levelPixelWidth;
    private int levelPixelHeigth;
    private int width;
    private int heigth;
    private Box2DDebugRenderer box2ddbr;
    private OrthographicCamera box2dcam;

    public Renderer(Mundo mundo){
        Utiles.imprimirLog("Renderer","Constructor","Creado objeto renderer");
        this.mundo = mundo;
        camera = new OrthographicCamera();
        width = Gdx.graphics.getWidth();
        heigth = Gdx.graphics.getHeight();
        camera.setToOrtho(false, width,heigth); // TODO cambiar coordenadas camara
        camera.update();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();

        //box2d camera
        box2ddbr = new Box2DDebugRenderer();
        box2dcam = new OrthographicCamera();
        box2dcam.setToOrtho(false,width/ B2DVars.PIXELS_METER, heigth/ B2DVars.PIXELS_METER);

        //tiled map
        mapa = new TmxMapLoader().load("mapas/test/test.tmx"); //TODO cambiar ruta mapa
		rendererMapa = new OrthogonalTiledMapRenderer(mapa);
        MapProperties properties = mapa.getProperties();
        int levelWidth = properties.get("width",Integer.class);
        int levelHeight = properties.get("height", Integer.class);
        int tilePixelWidth = properties.get("tilewidth", Integer.class);
        int tilePixelHeight = properties.get("tileheight",Integer.class);
        levelPixelWidth = tilePixelWidth * levelWidth;
        levelPixelHeigth= tilePixelHeight * levelHeight;

        Gdx.input.setInputProcessor(this);
    }


    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        camera.position.x = Math.min(Math.max(mundo.getPj().getPosicionX(), width/2),levelPixelWidth-(width/2));
        camera.position.y = Math.min(Math.max(mundo.getPj().getPosicionY(), heigth/2),levelPixelHeigth-(heigth/2));
        box2ddbr.render(mundo.getWorld(),box2dcam.combined);
        camera.update();
		rendererMapa.setView(camera);
		rendererMapa.render();
		spritebatch.setProjectionMatrix(camera.combined);
		spritebatch.begin();
		dibujarPj();
		spritebatch.end();

        if(debugger){
            debug();
        }
    }

    public void dibujarPj() {
        PersonajeJugable pj = mundo.getPj();
		spritebatch.draw(AssetsJuego.texturePJ, pj.getPosicion().x, pj.getPosicion().y, pj.getTamano().x, pj.getTamano().y );
    }

    public void debug() {

    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, width,height);
        box2dcam.setToOrtho(false,width/ B2DVars.PIXELS_METER, heigth/ B2DVars.PIXELS_METER);
        camera.update();
//		rendererMapa.setView(camera);
		spritebatch.setProjectionMatrix(camera.combined);
        shaperender.setProjectionMatrix(camera.combined);
    }

    public void dispose() {
        Gdx.input.setInputProcessor(null);
        spritebatch.dispose();
        shaperender.dispose();
        rendererMapa.dispose();
        mapa.dispose();
    }

    public TiledMap getMapa() {
    	return mapa;
	}

	public OrthographicCamera getCamera() {
    	return camera;
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
