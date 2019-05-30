package mx.edu.ittepic.anelcruzag.tpdm_u4_practica2_angelcruz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PrincipalJuego extends AppCompatActivity {
    Lienzo lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_juego);

        lienzo=new Lienzo(this);
        setContentView(lienzo);
        Thread hilo=new Thread(new Runnable() {
            @Override
            public void run() {
                boolean bandera=true;
                while (bandera){
                    try {
                        Thread.sleep(1000);
                        Figura mosca=new Figura(lienzo,R.drawable.mosca,(int)(Math.random()*lienzo.getWidth()),(int)(Math.random()*lienzo.getHeight())+70);
                        lienzo.add(mosca);
                        lienzo.decrementarSegundos();
                        if (lienzo.getPuntaje()==30){
                            lienzo.remove();
                            bandera=false;
                            lienzo.setMoscas(false);
                            lienzo.reiniciar();
                            moscon();
                        }//if
                        if (lienzo.getSegundos()==0){
                            lienzo.setPerder();
                            bandera=false;
                            lienzo.setMoscas(false);
                        }//if
                    }//try
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }//catch
                }//while
            }
        });
        hilo.start();
    }//onCreate

    public void moscon(){
        Figura mosca=new Figura(lienzo,R.drawable.moscon,(int)(Math.random()*lienzo.getWidth()),(int)(Math.random()*lienzo.getHeight())+50);
        lienzo.setMoscon(mosca);
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean mosconJugando=true;
                while (mosconJugando){
                    try {
                        Thread.sleep(1000);
                        lienzo.decrementarSegundos();
                        if (lienzo.getPuntaje()>=5){
                            lienzo.removeMoscon();
                            lienzo.setGanar();
                            lienzo.setMostrarMoscon(false);
                            lienzo.reiniciar();
                            mosconJugando=false;
                        }//if
                        if (lienzo.getSegundos()==0){
                            lienzo.setPerder();
                            lienzo.setMostrarMoscon(false);
                            mosconJugando=false;
                        }//if
                    }//try
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }//catch
                }//while
            }
        }).start();
    }//moscon
}//class
