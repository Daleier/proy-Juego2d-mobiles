package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


import game.AssetsJuego;
import game.B2DVars;
import game.Utiles;
import modelo.Mundo;
import modelo.PersonajeJugable;


import static game.B2DVars.PPM;

/**
 * Created by dalei on 14/02/2018.
 */

public class Renderer implements InputProcessor{

    private Mundo mundo;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera2d;
    private OrthographicCamera cameraHud;
    private final float WIDTH;
    private final float HEIGHT;
    private boolean debug = false;
    // box2d
    private World world;
    private Box2DDebugRenderer box2dRender;
    // tiled map
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRender;
    private final int LEVELPIXELWIDTH;
    private final int LEVELPIXELHEIHT;

    public Renderer(Mundo mundo) {
        Utiles.imprimirLog("Renderer", "Constructor", "Creado objeto renderer");
        this.mundo = mundo;
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera2d = new OrthographicCamera();
        cameraHud = new OrthographicCamera();
        WIDTH = mundo.ANCHO_MUNDO;
        HEIGHT = mundo.ALTO_MUNDO;
        // box2d
        this.world = this.mundo.getWorld();
        box2dRender = new Box2DDebugRenderer();
        // tiled map
        map = new TmxMapLoader().load("mapas/test/test.tmx");
        mapRender = new OrthogonalTiledMapRenderer(map);
        // calcula tamaño del mapa
        MapProperties properties = map.getProperties();
        int levelWidth = properties.get("width",Integer.class);
        int levelHeight = properties.get("height", Integer.class);
        int tilePixelWidth = properties.get("tilewidth", Integer.class);
        int tilePixelHeight = properties.get("tileheight",Integer.class);
        LEVELPIXELWIDTH = tilePixelWidth * levelWidth;
        LEVELPIXELHEIHT= tilePixelHeight * levelHeight;
        // asignar fisicas a cuadros mapa
        BodyDef bdef = new BodyDef();
        Body body;
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        for(MapObject object : map.getLayers().get("objects").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            fdef.filter.categoryBits = B2DVars.BIT_SUELO;
            fdef.filter.maskBits = B2DVars.BIT_JUGADOR;
            fdef.isSensor = false;
            body.createFixture(fdef).setUserData("platform");
        }
        Gdx.input.setInputProcessor(this);
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera2d.position.x = Math.min(Math.max(mundo.getPj().getBody().getPosition().x, WIDTH/2),LEVELPIXELWIDTH-(WIDTH/2));
        camera2d.position.y = Math.min(Math.max(mundo.getPj().getBody().getPosition().y, HEIGHT/2),LEVELPIXELHEIHT-(HEIGHT/2));
        camera2d.update();
        mapRender.setView(camera2d);
        mapRender.render();
        spriteBatch.setProjectionMatrix(camera2d.combined);
        spriteBatch.begin();
        dibujarPj();

        spriteBatch.end();
        box2dRender.render(world, camera2d.combined);
        if(debug){
            debug();
        }
    }

    public void dibujarPj(){
        PersonajeJugable pj = mundo.getPj();
        spriteBatch.draw(AssetsJuego.texturePJ,
                pj.getBody().getPosition().x - pj.getTamano().x/2, pj.getBody().getPosition().y - pj.getTamano().y/2,
                pj.getTamano().x, pj.getTamano().y);
    }

    private void debug(){

    }

    public void resize(int width, int height) {
        camera2d.setToOrtho(false,WIDTH,HEIGHT);
        camera2d.update();
        spriteBatch.setProjectionMatrix(camera2d.combined);
        shapeRenderer.setProjectionMatrix(camera2d.combined);
    }

    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        map.dispose();
        world.dispose();
        box2dRender.dispose();
        Gdx.input.setInputProcessor(null);
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
