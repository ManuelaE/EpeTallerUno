package taller_uno;

import java.net.Socket;
import java.util.ArrayList;

import taller_uno.Receptor.OnMessage;

public class Servidor extends Thread implements Conexion.Connect, Receptor.OnMessage {

	Logica log;
	private ArrayList<Receptor> jugadores;
	private Conexion conexion;
	private int mensajito;
	private Mensaje observer;

	public Servidor(Logica log) {
		this.log = log;
		mensajito = 0;

		jugadores = new ArrayList<Receptor>();

		conexion = new Conexion();
		conexion.setObserver(this);
		conexion.start();
	}

	@Override
	public void run() {
		while (true) {
			// deberia leer los mensajes, interpretar y resolver (si es necesario,mandar a
			// la logica)
		}
	}

	// Usuario que llegan de Conexión
	@Override
	public void conectados(Socket socket) {

		Servidor ser = this;

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Se conectó!");
				
				Receptor rec;
				if (jugadores.size() == 0) {
					rec = new Receptor(socket, "uno");
				} else {
					rec = new Receptor(socket, "dos");
				}
				rec.setObserver(ser);
				rec.start();

				// Añado al jugador "que todavía no es visible"
				jugadores.add(rec);
			}

		}).start();
	}

	// En este método llega el mensaje de Android
	@Override
	public void recibido(String mensaje, String yo) {
		observer.enviarInfo(mensaje, yo);
	}

	public interface Mensaje {

		public void enviarInfo(String mensaje, String yo);
	}

	public void setObserver(Mensaje mensajito) {

		this.observer = mensajito;
	}

}
