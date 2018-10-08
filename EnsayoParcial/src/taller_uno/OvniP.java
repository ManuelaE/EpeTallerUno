package taller_uno;

import processing.core.PApplet;

public class OvniP extends Ovni{

	public OvniP(PApplet p) {
		super(p);
		
		// Cargo la imagen
		ovni = p.loadImage("../data/ovni_1.png");
		
		// Inicializo variables
		vida = 150;
	}

	@Override
	public void pintar() {
		p.imageMode(p.CENTER);
		p.image(ovni, x, y);
		p.imageMode(p.CORNER);
	}

}
