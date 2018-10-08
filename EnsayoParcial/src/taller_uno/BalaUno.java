package taller_uno;

import processing.core.PApplet;

public class BalaUno extends Bala {

	BalaUno(PApplet p, int x) {
		super(p, x);

		// Cargo la imagen
		bala = p.loadImage("../data/bala_1.png");

		// Inicializo las variables
		y = 509;
		daño = 30;
	}

	@Override
	public void pintarNormal() {

		p.image(bala, x, y);
	}

	@Override
	public void mover() {
		
		y += 30;
	}

	public void moverSuper() {
		// TODO Auto-generated method stub

	}

}
