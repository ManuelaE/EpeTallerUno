package epe_taller_uno.epetalleruno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class PantallaUno extends AppCompatActivity {

    Cliente c;
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_uno);

        c = Cliente.getInstance();

        btnSiguiente = findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.enviar("6");

                Intent intento = new Intent(getApplicationContext(), PantallaDos.class);
                //Bundle bundle = new Bundle();
               // bundle.putSerializable("cliente", (Serializable) c);
               // intento.putExtras(bundle);
                startActivity(intento);
            }
        });
    }
}
