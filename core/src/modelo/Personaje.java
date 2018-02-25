package modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Personaje {

        private Rectangle rectangulo;

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
         * Actualiza a posición do rectángulo asociado á forma do gráfico
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
         * Velocidade que toma cando se move.
         */
        public float velocidade_max;
        /**
         * Velocidade actual
         */
        protected float velocidade = 0;
        /**
         * Posición
         */
        protected Vector2 posicion;
        /**
         * Tamaño
         */
        protected Vector2 tamano;
 
        /**
         * Devolve a posicion
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
         * Modifica a posición
         * @param posicion: a nova posicion
         */
        public void setPosicion(Vector2 posicion) {
                this.posicion = posicion;
                actualizarRectangulo();
        }
 
        /**
         * Modifica a posición
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
         * Modifica a velocidade
         * @param velocidade: a nova velocidade
         */
        public void setVelocidade(float velocidade) {
                this.velocidade = velocidade;
        }
 
        /**
         * Devolve a velocidade
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

        /**
         * Actualiza a posición en función da velocidade
         * @param delta: tempo entre unha chamada e a seguinte
         */
        public abstract void update(float delta);
}