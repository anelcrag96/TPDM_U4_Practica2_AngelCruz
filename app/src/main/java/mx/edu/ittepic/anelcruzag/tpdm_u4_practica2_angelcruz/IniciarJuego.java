package mx.edu.ittepic.anelcruzag.tpdm_u4_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IniciarJuego extends AppCompatActivity {
    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_juego);

        iniciar=findViewById(R.id.btnIniciar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IniciarJuego.this,PrincipalJuego.class));
            }//onClick
        });
    }//onCreate
}//class
