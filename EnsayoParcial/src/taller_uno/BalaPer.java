package taller_uno;

import processing.core.PApplet;
import processing.core.PImage;

public class BalaPer extends Bala {
	
	private PImage bala2;
	
	BalaPer(PApplet p, int x) {
		super( p, x);
		
		//Cargo la imagen
		bala = p.loadImage("../data/bala_4.png");
		bala2 = p.loadImage("../data/bala_4.1.png");
		
		//Inicializo las variables
		y = 509;
	}

	@Override
	public void pintarNormal () {
		
		p.image(bala, x, y);
		
	}
	
	public void pintarSuper () {
		
		p.image(bala2, x, y);
	}

	@Override
	public void mover () {
		
		y -= 30;
	}
	
	public void moverSuper () {
		
		y -= 45;
	}
	
	@Override
	public void seFue() {
		if(this.y<-20) {
			eliminame=true;
		}
	}
	
	
}
