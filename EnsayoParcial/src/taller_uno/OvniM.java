package taller_uno;

import processing.core.PApplet;

public class OvniM extends Ovni {
	
	public OvniM(PApplet p) {
		super(p);
		
		//Cargo la imagen
		ovni = p.loadImage("../data/ovni_2.png");
		
		// Inicializo variables
		vida = 300;
	}

	@Override
	public void pintar() {
		
		p.imageMode(p.CENTER);
		p.image(ovni, x, y);
		p.imageMode(p.CORNER);
	}

}
