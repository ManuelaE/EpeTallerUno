package taller_uno;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Ovni {

	protected PApplet p;
	protected int x, y, vida;
	protected PImage ovni;

	public Ovni(PApplet p) {
		this.p = p;

		// Inicializo variables
		y = -50;
		x = (int) p.random(100, 1100);
	}

	public abstract void pintar();

	public void mover() {

		y++;
	}

	public void morir(int golpe) {

		vida -= golpe;
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

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
