package taller_uno;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion extends Thread {

	// Esta clase se encarga de "decir" cuantas personas se conectaron

	ServerSocket ss;
	Socket s;
	Connect observer;

	public Conexion() {

		try {
			ss = new ServerSocket(5000);
			System.out.println("Esperando conexi�n!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {

			try {

				s = ss.accept();
				System.out.println("Conexi�n aceptada!");
				
				//El jugador que se conect�
				observer.conectados(s);

			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	public interface Connect {

		public void conectados(Socket socket);
	}

	public void setObserver(Connect mensajito) {

		this.observer = mensajito;
	}
}
