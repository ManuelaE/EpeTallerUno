package epe_taller_uno.epetalleruno;

import android.util.Log;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class Cliente extends Thread{

    Socket s;

    @Override
    public void run() {

        try{

            s = new Socket("192.168.1.22", 5000);

        } catch (Exception e){

            e.printStackTrace();
        }
    }

    public void enviar (final String msj) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    OutputStream os = s.getOutputStream();
                    Log.e("ENVIAR", "ME PREPARO PARA ENVIAR UN MENSAJE");

                    PrintWriter mensaje = new PrintWriter( new OutputStreamWriter(os));
                    mensaje.println(msj);

                    mensaje.flush();

                } catch ( Exception e ) {

                    e.printStackTrace();
                }
            }
        }).start();
    }
}
