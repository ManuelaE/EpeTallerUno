package taller_uno;

import processing.core.PApplet;

public class BalaDos extends Bala{
	
	BalaDos(PApplet p, int x) {
		super( p, x);
		
		//Cargo la imagen
		bala = p.loadImage("../data/bala_2.png");
		
		//Inicializo las variables
		y = 509;
		daño = 60;
	}

	@Override
	public void pintarNormal() {
		
		p.image(bala, x, y);
	}

	@Override
	public void mover() {
		
		y += 30;
	}

}
