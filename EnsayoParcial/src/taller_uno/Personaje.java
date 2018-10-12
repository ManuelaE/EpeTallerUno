package taller_uno;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Personaje {

	private PApplet p;

	// Declaro variables
	private int y, x, vida, vidas, anchoUno, anchoDos;

	// Declaro imágenes
	private PImage per1, per2;
	private PImage vida_barra;

	public Personaje(PApplet p) {
		this.p = p;

		// Inicializo variables
		y = 539;
		x = 575;
		vida = 0;
		vidas = 500;

		// Cargo las imágenes
		per1 = p.loadImage("../data/killer.png");
		per2 = p.loadImage("../data/killer_2.png");

		vida_barra = p.loadImage("../data/vidas.png");

	}

	public void pintarUno() {
		
		p.imageMode(p.CENTER);
		p.image(per1, x, y);
		p.imageMode(p.CORNER);
		vidaUno();
		p.image(vida_barra, 0, 0);
	}

	public void pintarDos() {
		
		p.imageMode(p.CENTER);
		p.image(per2, x, y);
		p.imageMode(p.CORNER);
		vidaDos();
		p.image(vida_barra, 0, 0);
	}

	public void moverIzq() {

		if (x - 50 > 100) {
			x -= 50;
		}
	}

	public void moverDer() {

		if (x + 50 < 1125) {
			x += 50;
		}
	}

	public void morir(int golpe) {

		vidas -= golpe;
	}
	
	public void sumaVida() {
		
		if ( vidas <= 420 ) {
			vidas += 80;
		}	
	}

	public void vidaUno() {
		
		anchoUno = (int) PApplet.map(vidas, 500, 0, 144, 0);
		
		p.noStroke();
		p.fill(0, 0, 255);
		p.rect(369, 634, anchoUno, 13);

	}

	public void vidaDos() {
		
		anchoDos = (int) PApplet.map(vidas, 500, 0, 144, 0);
		
		p.noStroke();
		p.fill( 255, 0, 0);
		p.rect(369, 659, anchoDos, 13);

	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getVida() {
		return vidas;
	}

	public void setVida(int vida) {
		this.vidas = vida;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
}
