package taller_uno;

import processing.core.PApplet;

public class OvniG extends Ovni {
	
	public OvniG(PApplet p) {
		super(p);
		
		//Cargo la imagen
		ovni = p.loadImage("../data/ovni_3.png");
		
		// Inicializo variables
		vida = 500;
	}

	@Override
	public void pintar() {
		
		p.imageMode(p.CENTER);
		p.image(ovni, x, y);
		p.imageMode(p.CORNER);
	}

}
