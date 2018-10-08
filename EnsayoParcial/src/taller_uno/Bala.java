package taller_uno;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Bala extends Thread {

	protected PApplet p;
	protected int x, y, daño;
	protected PImage bala;
	protected boolean eliminame;

	public Bala(PApplet p, int x) {
		this.p = p;
		this.x = x;
	}

	public abstract void pintarNormal();

	@Override
	public void run() {
		while (eliminame == false) {
			try {
				mover();
				seFue();
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void seFue() {
		if(this.y>500) {
			eliminame=true;
		}
	}
	
	public abstract void mover();

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

	public boolean isEliminame() {
		return eliminame;
	}

	public void setEliminame(boolean eliminame) {
		this.eliminame = eliminame;
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}
	

}
