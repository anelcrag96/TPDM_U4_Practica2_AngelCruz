package mx.edu.ittepic.anelcruzag.tpdm_u4_practica2_angelcruz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Lienzo extends View {
    String perder;
    String ganar;
    List<Figura> mosca;
    Figura moscon;
    int puntaje;
    int segundos;
    boolean moscas,mostrarMoscon;

    public Lienzo(Context context) {
        super(context);
        mosca=new ArrayList<>();
        segundos=60;
        puntaje=0;
        moscas=true;
        perder="";
        ganar="";
    }//constructor

    protected void onDraw(Canvas c){
        Paint p=new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(34f);
        c.drawText("Puntaje:"+puntaje,50,50,p);
        c.drawText("Tiempo restante:"+segundos,400,50,p);
        if(moscas){
            mosquitapequeña(c,p);
        }//if
        else if(mostrarMoscon){
            if(moscon!=null){
                moscon.pintar(c,p);
            }//if
        }//else if
        p.setFakeBoldText(true);
        p.setColor(Color.RED);
        p.setTextSize(105f);
        c.drawText(perder,(getWidth()/2)-170,getHeight()/2,p);
        p.setColor(Color.BLACK);
        p.setTextSize(105f);
        c.drawText(ganar,(getWidth()/2)-300,getHeight()/2,p);
    }//onDraw

    public boolean onTouchEvent(MotionEvent evento){
        int accion= evento.getAction();
        int posicionX=(int)evento.getX();
        int posicionY=(int)evento.getY();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (moscas){
                    for (int i = 0; i < mosca.size(); i++) {
                        if (mosca.get(i).enArea(posicionX, posicionY)) {
                            mosca.remove(i);
                            incrementarPuntaje();
                        }//if
                    }//for
                }//if
                else if (mostrarMoscon){
                    if (moscon.enArea(posicionX,posicionY)){
                        moscon.setX((int)(Math.random()*getWidth()));
                        moscon.setY((int)(Math.random()*getHeight())+50);
                        incrementarPuntaje();
                    }//if
                }//else if
                break;
        }//switch
        invalidate();
        return true;
    }//onTouchEvent

    public void add(Figura figura){
        mosca.add(figura);
        invalidate();
    }//add

    public void decrementarSegundos(){
        segundos--;
        invalidate();
    }//decrementarSegundos

    public void incrementarPuntaje(){
        puntaje++;
        invalidate();
    }//incrementarPuntaje

    public int getPuntaje(){
        return puntaje;
    }//getPuntaje

    public int getSegundos(){
        return segundos;
    }//getSegundos

    public void remove(){
        mosca.removeAll(mosca);
        invalidate();
    }//remove

    public void setPerder(){
        perder="Perdiste";
        invalidate();
    }//setPerder

    public void setMoscas(boolean mosquitas){
        moscas=mosquitas;
    }//setMoscas

    public void mosquitapequeña(Canvas c, Paint p){
        for (int i=0;i<mosca.size();i++){
            mosca.get(i).pintar(c,p);
        }//for
    }//mosquitapequeña

    public void reiniciar(){
        puntaje=0;
        segundos=10;
        invalidate();
    }//reiniciar

    public void setMoscon(Figura mosca){
        moscon = mosca;
        setMostrarMoscon(true);
        invalidate();
    }//setMoscon

    public void removeMoscon() {
        moscon=null;
    }//removeMoscon

    public void setGanar(){
        ganar="Felicidades!!";
    }//setGanar

    public void setMostrarMoscon(boolean mostrarMoscon) {
        this.mostrarMoscon = mostrarMoscon;
    }//setMostrarMoscon
}//class
