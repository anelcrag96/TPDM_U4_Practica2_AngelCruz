package mx.edu.ittepic.anelcruzag.tpdm_u4_practica2_angelcruz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Figura {
    Bitmap imagen;
    int x,y;

    public Figura(Lienzo pantalla, int imagen, int posicionX, int posicionY){
        this.imagen= BitmapFactory.decodeResource(pantalla.getResources(),imagen);
        x=posicionX;
        y=posicionY;
    }//constructor

    public void pintar(Canvas c, Paint p){
        x=((x+imagen.getWidth())>=c.getWidth())?x-imagen.getWidth():x;
        y=((y+imagen.getHeight())>=c.getHeight())?y-imagen.getHeight():y;
        c.drawBitmap(imagen,x,y,p);
    }//pintar

    public boolean enArea(int xd,int yd){
        int x2= x+(imagen.getWidth());
        int y2= y+(imagen.getHeight());

        if ( xd >= x && xd<=x2){
            if (yd >=y && yd <= y2){
                return true;
            }//if
        }//if
        return false;
    }//enArea

    public void setX(int x){
        this.x=x;
    }//setX

    public void setY(int y){
        this.y=y;
    }//setY
}//class
