package com.example.solarsystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class EcranDemarrage extends View {
    private int screenW;
    private int screenH;
    private Bitmap PageLogo;
    private Bitmap btnModeDoigteUp;
    private Bitmap btnModeDoigteDown;
    private Bitmap btnModePivoUp;
    private Bitmap btnModePivoDown;
    private boolean PlayBtnState;
    private Context MyContext;
    private Paint crayon;

    //Recuperation des dimensions de l'écran et attribution de ces dimensions à nos variables, à chaque changement de taille
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;

        Log.e("dim",String.valueOf(screenW) + " width");
        Log.e("dim",String.valueOf(screenH) + " Height");

    }

    //Creation du constructeur EcranDemarrage et on lui attribut le context en paramètre
    public EcranDemarrage(Context context)
    {
        super(context);
        MyContext = context;

        //On recupère les chemins de chaque image et on les place dans des vriables
        PageLogo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        btnModeDoigteUp = BitmapFactory.decodeResource(getResources(), R.drawable.bouton_doigte_up);
        btnModeDoigteDown = BitmapFactory.decodeResource(getResources(), R.drawable.bouton_doigte_down);
        btnModePivoUp = BitmapFactory.decodeResource(getResources(), R.drawable.bouton_mode_pivo_up);
        btnModePivoDown = BitmapFactory.decodeResource(getResources(), R.drawable.bouton_mode_pivo_down);

        //Log.e("logo",String.valueOf(PageLogo.getWidth()) + " width");
        //Log.e("logo",String.valueOf(PageLogo.getHeight()) + " height");
    }

    @Override
    //Canvas fait référence à la surface graphique de l'utilisateur
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //Avec le canvas on dessine le Pagelogo et on indique la position où on veut le dessiner
        canvas.drawBitmap(PageLogo,(screenW - PageLogo.getWidth())/2,(int)(screenH*0.10),null);
        //canvas.drawText("AlienSolarSytème", (float)Paint.measureText("hello"), canvas.getHeight()/2 - crayon.descent() + crayon.ascent() / 2);

        //Changement de l'état des boutons quand on appuis
        if(PlayBtnState)
        {
            canvas.drawBitmap(btnModeDoigteDown,(screenW - btnModeDoigteDown.getWidth()) / 2,(int)(screenH*0.75),null);
        }
        else
        {
            canvas.drawBitmap(btnModeDoigteUp,(screenW - btnModeDoigteUp.getWidth()) / 2, (int)(screenH*0.75),null);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);

        int action = event.getAction(); //On récupère le type d'action effectué
        int touchX = (int)event.getX(); // on récupère les coordonnées x du touché
        int touchY = (int)event.getY(); //on récupère les coordonnées y du touché

        //On détermine l'action à effectuer
        switch(action)
        {
            case  MotionEvent.ACTION_DOWN:
                if((touchX > (screenW - btnModeDoigteUp.getWidth())/2 && touchX < ((screenW - btnModeDoigteUp.getWidth())/2) +
                        btnModeDoigteUp.getWidth()) && ((touchY > (int)(screenH*0.75)) &&
                        (touchY < ((int)(screenH*0.75) + btnModeDoigteUp.getHeight()))))
                {
                    PlayBtnState = true;
                }
                break;

            case MotionEvent.ACTION_UP:
                if(PlayBtnState)
                {
                    Intent intent = new Intent(MyContext,JeuxOuvert.class);
                    MyContext.startActivity(intent);
                }
                PlayBtnState = false;
                break;
        }
        invalidate();
        return true;
    }
}
