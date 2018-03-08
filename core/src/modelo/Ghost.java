package modelo;

import com.badlogic.gdx.math.Vector2;

public class Ghost extends Personaje {

    public Vector2 direccion;
    public Vector2 temporal;
    public Vector2 posicionInicial;
    public Vector2 coordenadaDestino;
    public boolean mirandoFrente;


    public Ghost(Vector2 posicion, Vector2 tamano, float velocidade_max) {
        super(posicion, tamano, velocidade_max);
        posicionInicial = posicion;
        this.velocidade_max=velocidade_max;
        getRectangulo().setSize(tamano.x/2, tamano.y/2);
        temporal = new Vector2();
        direccion = new Vector2(0,0);
        coordenadaDestino = new Vector2();
    }

    public void inicializarGhost(){
        setPosicion(105,1400);
        setTamano(tamano.x,tamano.y);
    }

    public void update(float delta) {
        temporal.set(direccion);
        setPosicion(posicion.add(temporal.scl(velocidade_max*delta)));
    }

}