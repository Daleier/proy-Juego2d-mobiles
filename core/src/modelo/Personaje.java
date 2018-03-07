package modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.B2DVars;
import game.Utiles;

public abstract class Personaje {

        private Rectangle rectangulo;
        public float velocidade_max;
        protected float velocidade = 0;
        protected Vector2 posicion;
        protected Vector2 tamano;

        public Personaje(){
			rectangulo = new Rectangle();
        }
        /**
         * Instancia unha personaxe
         *
         * @param posicion
         * @param tamano
         * @param velocidade_max
         */
        public Personaje(Vector2 posicion, Vector2 tamano, float velocidade_max) {
            Utiles.imprimirLog("Personaje","Constructor","Creado objeto constructor");
            this.posicion = posicion;
            this.tamano = tamano;
            this.velocidade_max = velocidade_max;
            rectangulo = new Rectangle(posicion.x,posicion.y,tamano.x,tamano.y);
        }


        public void setTamanoRectangulo(float width,float height){
                rectangulo.setWidth(width);
                rectangulo.setHeight(height);
        }

        /**
         * Actualiza rectangleUpArrow posición do rectángulo asociado á forma do gráfico
         *
         */
        public void actualizarRectangulo(){
                rectangulo.x=posicion.x;
                rectangulo.y=posicion.y;
        }

        /**
         * Devolve o rectángulo asociado
         * @return rectangulo
         */
        public Rectangle getRectangulo(){
                return rectangulo;
        }


 
        /**
         * Devolve rectangleUpArrow posicion
         * @return posicion
         */
        public Vector2 getPosicion() {
                return posicion;
        }

        public int getPosicionX() {
                return Math.round(this.posicion.x);
        }

        public int getPosicionY() {
                return Math.round(this.posicion.y);
        }
        /**
         * Modifica rectangleUpArrow posición
         * @param posicion: rectangleUpArrow nova posicion
         */
        public void setPosicion(Vector2 posicion) {
                this.posicion = posicion;
                actualizarRectangulo();
        }
 
        /**
         * Modifica rectangleUpArrow posición
         *
         * @param x: nova posición x
         * @param y: nova posición y
         */
        public void setPosicion(float x, float y) {
                posicion.x = x;
                posicion.y = y;

                actualizarRectangulo();
        }
 
        /**
         * Modifica rectangleUpArrow velocidade
         * @param velocidade: rectangleUpArrow nova velocidade
         */
        public void setVelocidade(float velocidade) {
                this.velocidade = velocidade;
        }
 
        /**
         * Devolve rectangleUpArrow velocidade
         * @return velocidade
         */
        public float getVelocidade() {
                return velocidade;
        }
 
        /**
         * Devolve o tamaño
         * @return tamano
         */
        public Vector2 getTamano() {
                return tamano;
        }
 
        /**
         * Modifica o tamano
         *
         * @param width: novo tamano de ancho
         * @param height: novo tamano de alto
         */
        public void setTamano(float width, float height) {
                this.tamano.set(width,height);
                setTamanoRectangulo(width, height);
        }

        public void setTamano(Vector2 tamano) {
                this.tamano=tamano;
                setTamanoRectangulo(tamano.x,tamano.y);
        }
}