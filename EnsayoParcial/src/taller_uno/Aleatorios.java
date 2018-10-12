package taller_uno;

import processing.core.PApplet;
import processing.core.PImage;

public class Aleatorios {
	
	PApplet p;
	
	// Declaro las imágenes
	private PImage corazon;
	
	// Declaro variables
	private int x, y;
	
	public Aleatorios ( PApplet p ) {
		
		this.p = p;
		
		// Cargo las imágenes
		corazon = p.loadImage("../data/corazon.png");
		
		// Inicializo variables
		x = (int) p.random(100, 1100);
		//x = 575;
		y = -20;
	}
	
	public void pintarCorazon () {
		
		p.image(corazon, x, y);
	}	
	
	public void mover() {

		y++;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
