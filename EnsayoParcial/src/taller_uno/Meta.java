package taller_uno;

import processing.core.PApplet;
import processing.core.PImage;

public class Meta {
	
	PApplet p;
	
	// Declaro imágenes
	private PImage meta_1, meta_2, meta_3, meta_4, meta_5, meta_6, meta_7, meta_8;
	private PImage meta_a, meta_b, meta_c;
	
	// Declaro variables
	private int meta, muertos;
	
	public Meta ( PApplet p ) {
		this.p = p;
		
		// Cargo las imágenes
		meta_1 = p.loadImage("../data/meta_1.png");
		meta_2 = p.loadImage("../data/meta_2.png");
		meta_3 = p.loadImage("../data/meta_3.png");
		meta_4 = p.loadImage("../data/meta_4.png");
		meta_5 = p.loadImage("../data/meta_5.png");
		meta_6 = p.loadImage("../data/meta_6.png");
		meta_7 = p.loadImage("../data/meta_7.png");
		meta_8 = p.loadImage("../data/meta_8.png");
		
		meta_a = p.loadImage("../data/meta_a.png");
		meta_b = p.loadImage("../data/meta_b.png");
		meta_c = p.loadImage("../data/meta_c.png");
		
		// Inicializo variables
		meta = 1;
		muertos = 0;
	}
	
	public void pintar () {

	}
	
	public void pintarMeta ( int meta ) {
		this.meta = meta;
		
		switch (meta) {
		case 1:
			p.image(meta_a, 0, 0);
			break;
			
		case 2:
			p.image(meta_a, 0, 0);
			break;
			
		case 3:
			p.image(meta_a, 0, 0);
			break;

		default:
			break;
		}
	}
	
	public void logros ( int muertos ) {
		this.muertos = muertos;
		
		if ( muertos == 1 ) {
			p.image(meta_1, 0, 0);
		}
		
		else if ( muertos == 2 ) {
			p.image(meta_2, 0, 0);
		}
		
		else if ( muertos == 3 ) {
			p.image(meta_3, 0, 0);
		}
		
		else if ( muertos == 4 ) {
			p.image(meta_4, 0, 0);
		}
		
		else if ( muertos == 5 ) {
			p.image(meta_5, 0, 0);
		}
		
		else if ( muertos == 6 ) {
			p.image(meta_6, 0, 0);
		}
		
		else if ( muertos == 7 ) {
			p.image(meta_7, 0, 0);
		}
		
		else if ( muertos == 8 ) {
			p.image(meta_8, 0, 0);
		}
	}
}
