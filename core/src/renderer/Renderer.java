package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

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
        camera.setToOrtho(false, width,heigth);
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
        TiledMapTileLayer layer = (TiledMapTileLayer) mapa.getLayers().get("cuadros");
        crearBodyMapa(layer);
        Gdx.input.setInputProcessor(this);
    }

    private void crearBodyMapa(TiledMapTileLayer layer){
        BodyDef bDef = new BodyDef();
        float tamañoTile = layer.getTileWidth();
        for (int fila= 0 ; fila <layer.getHeight(); fila++){ // filas
            for (int columna=0; columna < layer.getWidth(); columna++){ // columnas
                Cell cell = layer.getCell(columna,fila);
                if (cell == null)
                    continue;
                if (cell.getTile() == null)
                    continue;
                bDef.type = BodyType.StaticBody;
                bDef.position.set((columna +0.5f) * tamañoTile/B2DVars.PIXELS_METER,
                        (fila +0.5f) * tamañoTile/B2DVars.PIXELS_METER);
                ChainShape cs = new ChainShape();
                Vector2 v[] = new Vector2[3];
                v[0] = new Vector2(- tamañoTile/2/B2DVars.PIXELS_METER, -tamañoTile/2/B2DVars.PIXELS_METER);
                v[1]= new Vector2(- tamañoTile/2/B2DVars.PIXELS_METER, tamañoTile/2/B2DVars.PIXELS_METER);
                v[2] = new Vector2(tamañoTile/2/B2DVars.PIXELS_METER, tamañoTile/2/B2DVars.PIXELS_METER);
                cs.createChain(v);
                FixtureDef fDef = new FixtureDef();
                fDef.friction = 0;
                fDef.shape= cs;
                fDef.filter.categoryBits = B2DVars.BIT_SUELO;
                fDef.filter.maskBits = B2DVars.BIT_JUGADOR;
                fDef.isSensor = true;
                mundo.getWorld().createBody(bDef).createFixture(fDef);
                cs.dispose();
            }
        }
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        camera.position.x = Math.min(Math.max(mundo.getPj().getBody().getPosition().x, width/2),levelPixelWidth-(width/2));
        camera.position.y = Math.min(Math.max(mundo.getPj().getBody().getPosition().y, heigth/2),levelPixelHeigth-(heigth/2));
        box2ddbr.render(mundo.getWorld(),box2dcam.combined);
        rendererMapa.setView(camera);
        rendererMapa.render();
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
        box2dcam.setToOrtho(false,width/ B2DVars.PIXELS_METER, heigth/ B2DVars.PIXELS_METER);
        camera.setToOrtho(false, width,heigth);
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
