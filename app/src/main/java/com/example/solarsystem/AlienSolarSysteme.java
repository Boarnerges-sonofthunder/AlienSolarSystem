package com.example.solarsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienSolarSysteme extends View {
    private Random aleatoire;
    private Paint vaisseauPaint;
    private float vaisseauX;
    private float vaisseauY;
    private float vaisseauRadius;
    private int compteur;
    private Context myContext;
    private boolean fin;
    private AstreCeleste[] astreCeleste = new AstreCeleste[5];
    private Bitmap vaisseau;
    private int screenW;
    private int screenH;
    AstreCeleste astrCel = new AstreCeleste();

    //Recuperation des dimensions de l'écran et attribution de ces dimensions à nos variables, à chaque changement de taille
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;

        //Log.e("dim",String.valueOf(screenW) + " width");
        //Log.e("dim",String.valueOf(screenH) + " Height");

    }

    public AlienSolarSysteme(Context context)
    {
        super(context);
        myContext = context;
        AstreDBHelper astreDbHelper = new AstreDBHelper(myContext);
        fin = false;
        compteur = 0;
        vaisseau = BitmapFactory.decodeResource(getResources(), R.drawable.vaisseau_spatial);


        //On crée une boucle pour récupérer dans un tableau les astres
        for(int i=0; i<5; i++)
        {
            AstreCeleste temp = new AstreCeleste();
            astreCeleste[i] = temp;
            boolean success = astreDbHelper.addAll(astreCeleste[i]);
            //Toast.makeText(myContext,"Success" + success,Toast.LENGTH_SHORT).show();
            //Toast.makeText(myContext,astreCeleste[i].toString(),Toast.LENGTH_SHORT).show();
            
        }

        //Afficher les astres
        //Creation d'une liste qui va recevoir les données du astreDBHelper
        List<AstreCeleste> tousLesAstre = astreDbHelper.getAll();
        //Toast.makeText(myContext,tousLesAstre.toString(),Toast.LENGTH_SHORT).show();

        //Creation d'un arrayAdapter
        ArrayAdapter astreArrayAdapter = new ArrayAdapter<AstreCeleste>(myContext,AlienSolarSysteme.generateViewId(),tousLesAstre);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //super.onDraw(canvas);
        vaisseauX = (screenW - vaisseau.getWidth())/2;
        vaisseauY = (int)(screenH*0.90);
        //On dessine le vaisseau selon les dimension reçus
        canvas.drawBitmap(vaisseau,vaisseauX,vaisseauY,null);
        //canvas.drawBitmap(vaisseau,(screenW - vaisseau.getWidth())/2,(int)(screenH*0.90),null);
        //TODO: revoir le dessin du vaisseau

        //On crée une boucle pour dessiner les astres en appelant la methode onDraw de la classe AstreCeleste
        for(int i=0;i<5;i++)
        {
            astreCeleste[i].onDraw(canvas);

        }

        //On envois un message si le compteur arrive à 5
        if(compteur >= 5 && !fin)
        {
            Toast.makeText(myContext,"La partie est termin�e",Toast.LENGTH_LONG).show();
            fin = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float touchX = event.getX();
        float touchY = event.getY();

        boolean limitL,limitR,LimitU,LimitD = false;

        //TODO: optimiser le reponse lorsque le vaisseau touche un astre
        switch (action)
        {
            case MotionEvent.ACTION_MOVE:
                vaisseauX = touchX;
                vaisseauY = touchY;

                for(int i =0;i<5;i++)
                {
                    limitL = vaisseauX > (astreCeleste[i].getPosX()-30);
                    limitR =  vaisseauX < (astreCeleste[i].getPosX()+30);
                    LimitU =  vaisseauY > (astreCeleste[i].getPosY()-30);
                    LimitD =  vaisseauY < (astreCeleste[i].getPosY()+30);

                    if(limitL && limitR && LimitD && LimitU )
                    {
                        if(astreCeleste[i].isStatus())
                        {
                            astreCeleste[i].setStatus(false);
                            compteur++;
                        }
                        System.out.println(compteur);

                    }

                }
                break;
        }
        invalidate();
        return true;
    }
}
