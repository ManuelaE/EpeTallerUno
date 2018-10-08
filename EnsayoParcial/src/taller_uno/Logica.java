package taller_uno;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica implements Servidor.Mensaje {

	private PApplet p;
	Servidor server;

	private ArrayList<Bala> balasPer;

	// Declaro objetos
	private Personaje shooter1, shooter2;
	private ArrayList<Ovni> ovnis;
	private ArrayList<Bala> balasEne;
	private ArrayList<Aleatorios> objetos;

	// Declaro imágenes
	private PImage fondo, pantalla_1, pantalla_3, ins_1, ins_2, perder_1, perder_2, ganar;
	private PImage boton_comenzar, nivelC, perder_3;
	private PImage meta_1, meta_2, meta_3, meta_4, meta_5, meta_6, meta_7, meta_8;

	// Declaro variables
	private int msj, pantalla, tiempo, restar, puntos, logro, nivel, notDead;
	private boolean enJuego;

	public Logica(PApplet p) {

		this.p = p;

		// Conexión con Android
		server = new Servidor(this);
		server.setObserver(this);
		server.start();

		// Inicializo los objetos
		shooter1 = new Personaje(p);
		shooter2 = new Personaje(p);
		ovnis = new ArrayList<>();
		balasPer = new ArrayList<Bala>();
		balasEne = new ArrayList<Bala>();
		objetos = new ArrayList<Aleatorios>();

		// Inicializo variables
		msj = 0;
		pantalla = 0;
		tiempo = 0;
		restar = 0;
		puntos = 0;
		logro = 0;
		nivel = 0;
		enJuego = false;
		notDead = 0;

		// Cargo las imágenes
		fondo = p.loadImage("../data/fondo.png");
		ins_1 = p.loadImage("../data/ins_1.png");
		ins_2 = p.loadImage("../data/ins_2.png");
		pantalla_1 = p.loadImage("../data/pantalla_1.png");
		pantalla_3 = p.loadImage("../data/pantalla_3.png");
		boton_comenzar = p.loadImage("../data/boton_comenzar.png");
		perder_1 = p.loadImage("../data/perder_1.png");
		perder_2 = p.loadImage("../data/perder_2.png");
		perder_3 = p.loadImage("../data/perder_3.png");
		ganar = p.loadImage("../data/ganar.png");
		nivelC = p.loadImage("../data/meta_c.png");
		
		meta_1 = p.loadImage("../data/meta_1.png");
		meta_2 = p.loadImage("../data/meta_2.png");
		meta_3 = p.loadImage("../data/meta_3.png");
		meta_4 = p.loadImage("../data/meta_4.png");
		meta_5 = p.loadImage("../data/meta_5.png");
		meta_6 = p.loadImage("../data/meta_6.png");
		meta_7 = p.loadImage("../data/meta_7.png");
		meta_8 = p.loadImage("../data/meta_8.png");

		// Iniciar
		iniciando();
	}

	public void pintar() {

		// Métodos internos
		pantallas();

		// Para cambiar pantallas pero solo de prueba
		p.fill(255);
		p.rect(0, 0, 50, 50);
		p.fill(255);
		p.rect(0, 600, 50, 50);
		p.fill(255);
		p.rect(1100, 600, 50, 50);
		p.fill(255);
		p.rect(1000, 600, 50, 50);

		if (enJuego == true) {
			pantallaTres();
		}
	}

	// Con este método coloco todas las variables en su valor inicial
	public void terminar() {

		tiempo = 0;
		puntos = 0;
		logro = 0;
		enJuego = false;
	}

	// El juego comienza con ovnis pequeños
	public void iniciando() {

		enJuego = true;

		for (int i = 0; i < 2; i++) {
			OvniP ovniPequeño = new OvniP(p);
			ovnis.add(ovniPequeño);
		}
	}

	// Esto sucede solo cuando está en modo juego, o sea,en la pantalla 3
	public void pantallaTres() {

		if (pantalla == 3) {

			// A esta variable le resto el tiempo que lleva el juego desde el
			// comienzo hasta que empieza a jugar
			tiempo = (p.millis() - restar) / 1000;
			
			// Esta variable permite generar vidas aleatorias
			notDead++;
			System.out.println(notDead);

			// Métodos internos
			matarOvnis();
			perder();
			matarPersonajes();
			vidasAleatorias();
			pintarBalas();
			generarOvnis();
			meta();
			
			// Pinto la meta
			p.image(nivelC, 0, 0);

			// Pinto el tiempo
			p.fill(0, 3, 59);
			p.textSize(30);
			p.text(tiempo + "", 162, 663);

			// Pinto y cuento los puntos
			p.fill(0, 3, 59);
			p.textSize(30);
			p.text(puntos, 1080, 663);

			// Dispara cada 50 frames
			if (p.frameCount % 70 == 0) {
				balasOvnis();
			}

			// Pinto los corazones aleatorios
			for (Aleatorios obj : objetos) {
				obj.pintarCorazon();
				obj.mover();
			}
		}
	}

	public void matarPersonajes() {

		for (Bala bala : balasEne) {

			if (p.dist(bala.getX(), bala.getY(), shooter1.getX(), shooter1.getY()) < 50) {

				shooter1.morir(bala.getDaño());

				if (shooter1.getVida() < 0) {
					pantalla = 4;
					System.out.println("Se murió jugador uno");
				}

				balasEne.remove(bala);
				return;

			} else if (p.dist(bala.getX(), bala.getY(), shooter2.getX(), shooter2.getY()) < 50) {

				shooter2.morir(bala.getDaño());

				if (shooter2.getVida() < 0) {
					pantalla = 5;
					System.out.println("Se murió jugador dos");
				}

				balasEne.remove(bala);
				return;
			}
		}
	}

	public void balasOvnis() {
		for (Ovni ovni : ovnis) {
			Bala b = null;
			if (ovni instanceof OvniG)
				b = new BalaTres(p, ovni.getX());
			if (ovni instanceof OvniM)
				b = new BalaDos(p, ovni.getX());
			if (ovni instanceof OvniP)
				b = new BalaUno(p, ovni.getX());

			if (b != null) {
				b.setY(ovni.getY());
				b.start();
				balasEne.add(b);
			}
		}
	}

	public void matarOvnis() {

		for (int i = 0; i < balasPer.size(); i++) {
			Bala b = balasPer.get(i);

			for (int j = 0; j < ovnis.size(); j++) {
				Ovni o = ovnis.get(j);

				if (p.dist(b.getX(), b.getY(), o.getX(), o.getY()) < 50) {

					o.morir(50);
					balasPer.remove(i);

					if (o.getVida() == 0) {

						ovnis.remove(j);
						
						System.out.println("Lo mató!");
						
						if (o instanceof OvniG) {

							logro += 1;
							
						}
					}

					if (o instanceof OvniP) {

						puntos += 30;

					} else if (o instanceof OvniM) {

						puntos += 60;

					} else if (o instanceof OvniG) {

						puntos += 100;
						
					}
				}
			}
		}
	}

	public void perder() {

		for (int i = 0; i < ovnis.size(); i++) {

			if (ovnis.get(i).getY() >= 500) {

				ovnis.remove(i);
				terminar();
				pantalla = 11;
				
				limpiar();
			}
		}
	}

	public void vidasAleatorias() {

		if (notDead == 250) {
			objetos.add(new Aleatorios(p));
			notDead = 0;
		}

		for (Aleatorios obj : objetos) {

			if (p.dist(obj.getX(), obj.getY(), shooter1.getX(), shooter1.getY()) < 50) {
				objetos.remove(obj);
				shooter1.sumaVida();
				return;
			}

			if (p.dist(obj.getX(), obj.getY(), shooter2.getX(), shooter2.getY()) < 50) {
				shooter2.sumaVida();
				objetos.remove(obj);
				return;
			}
		}
	}

	public void ganar() {

		if (logro >= 8) {

			pantalla = 6;
		}
	}

	public void pintarBalas() {

		// Pinto las balas del personaje y las remuevo cuando pasan el límite de arriba
		for (Bala bala : balasPer) {
			bala.pintarNormal();

			if (bala.isEliminame() == true) {
				bala.interrupted();
				balasPer.remove(bala);
				return;
			}
		}

		// Pinto las balas de los ovnis y las remuevo cuando pasan el límite de abajo
		for (Bala bala : balasEne) {
			bala.pintarNormal();

			if (bala.isEliminame() == true) {
				bala.interrupted();
				balasEne.remove(bala);
				return;
			}
		}
	}

	public void generarOvnis() {

		if (enJuego = true) {

			// Genero ovnis aleatorios
			if (p.frameCount % 180 == 0) {

				int random = (int) p.random(100);
				if (random <= 40) {

					ovnis.add(new OvniP(p));

				} else if (random >= 60) {

					ovnis.add(new OvniM(p));

				} else {

					if (p.frameCount >= 600) {
						ovnis.add(new OvniG(p));
					}
				}
			}
		}

	}
	
	// Ests método es para volver a jugar
	public void limpiar () {
		
		balasEne.removeAll(balasEne);	
		ovnis.removeAll(ovnis);
		objetos.removeAll(objetos);
	} 
	
	public void meta () {
		
		if ( logro == 1 ) {
			p.image(meta_1, 0, 0);
			
		} else if ( logro == 2 ) {
			p.image(meta_1, 0, 0);
			p.image(meta_2, 0, 0);
			
		} else if ( logro == 3 ) {
			p.image(meta_1, 0, 0);
			p.image(meta_2, 0, 0);
			p.image(meta_3, 0, 0);
			
		} else if ( logro == 4 ) {
			p.image(meta_1, 0, 0);
			p.image(meta_2, 0, 0);
			p.image(meta_3, 0, 0);
			p.image(meta_4, 0, 0);
			
		} else if ( logro == 5 ) {
			p.image(meta_1, 0, 0);
			p.image(meta_2, 0, 0);
			p.image(meta_3, 0, 0);
			p.image(meta_4, 0, 0);
			p.image(meta_5, 0, 0);
			
		} else if ( logro == 6 ) {
			p.image(meta_1, 0, 0);
			p.image(meta_2, 0, 0);
			p.image(meta_3, 0, 0);
			p.image(meta_4, 0, 0);
			p.image(meta_5, 0, 0);
			p.image(meta_6, 0, 0);
			
		} else if ( logro == 7 ) {
			p.image(meta_1, 0, 0);
			p.image(meta_2, 0, 0);
			p.image(meta_3, 0, 0);
			p.image(meta_4, 0, 0);
			p.image(meta_5, 0, 0);
			p.image(meta_6, 0, 0);
			p.image(meta_7, 0, 0);
			
		} else if ( logro == 8 ) {
			p.image(meta_1, 0, 0);
			p.image(meta_2, 0, 0);
			p.image(meta_3, 0, 0);
			p.image(meta_4, 0, 0);
			p.image(meta_5, 0, 0);
			p.image(meta_6, 0, 0);
			p.image(meta_7, 0, 0);
			p.image(meta_8, 0, 0);
		}
	}

	// Este método cambia las pantallas
	public void pantallas() {

		switch (pantalla) {

		// Imagen principal
		case 0:
			p.image(pantalla_1, 0, 0);
			break;

		// Instrucciones 1
		case 1:
			p.image(ins_1, 0, 0);
			break;

		// Instrucciones 2
		case 2:
			p.image(ins_2, 0, 0);
			break;

		// Empezar a jugar
		case 3:
			p.image(pantalla_3, 0, 0);

			// Método de la clase Personaje
			shooter1.pintarUno();
			shooter2.pintarDos();

			// Pinto los ovnis
			for (int i = 0; i < ovnis.size(); i++) {
				ovnis.get(i).pintar();
				ovnis.get(i).mover();
			}

			break;

		// Pierde jugador uno
		case 4:
			p.image(perder_1, 0, 0);
			break;

		// Pierde jugador dos
		case 5:
			p.image(perder_2, 0, 0);
			break;

		// Ganaron el juego
		case 6:
			p.image(ganar, 0, 0);
			break;

		// Los ovnis alcanzaron a los usuarios
		case 11:
			p.image(perder_3, 0, 0);
			break;
		}
	}

	// Este método pinta la interaación del botón principal
	public void comenzar() {
		// System.out.println(p.mouseX + "," + p.mouseY);

		if (pantalla == 0) {

			if (p.mouseX > 491 && p.mouseX < 707 && p.mouseY > 520 && p.mouseY < 590) {

				p.image(boton_comenzar, 0, 0);
				pantalla = 1;
			}
		}

		if (pantalla == 1) {

			if (p.mouseX > 0 && p.mouseX < 100 && p.mouseY > 0 && p.mouseY < 100) {

				pantalla = 2;
			}
		}

		if (pantalla == 2) {

			if (p.mouseX > 0 && p.mouseX < 100 && p.mouseY > 600 && p.mouseY < 700) {
				restar = p.millis();
				pantalla = 3;
				enJuego = true;
			}
		}

		if (pantalla == 3) {

			if (p.mouseX > 1100 && p.mouseX < 1200 && p.mouseY > 600 && p.mouseY < 700) {
				accionJugador("2", shooter1);
				// System.out.println("si funciona kdajsdkaj");
			}

			if (p.mouseX > 1000 && p.mouseX < 1100 && p.mouseY > 600 && p.mouseY < 700) {
				accionJugador("2", shooter2);
				// System.out.println("si funciona kdajsdkaj");
			}
		}

		if (pantalla == 11) {

			if (p.mouseX > 687 && p.mouseX < 977 && p.mouseY > 73 && p.mouseY < 644) {
				pantalla = 13;
			}
		}

		if (pantalla == 13) {

			if (p.mouseX > 365 && p.mouseX < 832 && p.mouseY > 266 && p.mouseY < 437) {
				pantalla = 3;
				iniciando();
			}
		}
	}

	// En este método llega el mensaje de Android
	@Override
	public void enviarInfo(String mensaje, String yo) {

		if (yo.equals("uno")) {
			accionJugador(mensaje, shooter1);
		} else if (yo.equals("dos")) {
			accionJugador(mensaje, shooter2);
		}

	}

	public void accionJugador(String mensaje, Personaje per) {

		msj = Integer.parseInt(mensaje);

		new Thread(new Runnable() {

			@Override
			public void run() {

				// Izquierda
				if (msj == 1) {

					per.moverIzq();
					msj = 0;
				}

				// Disparar
				else if (msj == 2) {

					Bala b = new BalaPer(p, per.getX());
					b.start();
					balasPer.add(b);

				}

				// Derecha
				else if (msj == 3) {

					per.moverDer();
					msj = 0;
				}

				// Super disparo
				else if (msj == 4) {

				}

				// Stop
				else if (msj == 5) {

				}

				// Siguiente
				else if (msj == 6) {
					pantalla = 2;
				}

				// Jugar
				else if (msj == 7) {
					pantalla = 3;
				}
			}
		}).start();
	}
}
