package epe_taller_uno.epetalleruno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaDos extends AppCompatActivity {

    Cliente c;
    Button btnJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_dos);

        c = new Cliente();
        c.start();

        btnJugar = findViewById(R.id.btn_jugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.enviar("7");

                Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intento);
            }
        });
    }
}
