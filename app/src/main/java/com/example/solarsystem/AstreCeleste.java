package com.example.solarsystem;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class AstreCeleste {
    private int id;
    private String nom;
    private int taille;
    private String couleur;
    private boolean status;
    private int image;
    private int posX;
    private int posY;
    private Paint crayon;
    private Color pinceau;
    private Random aleatoire;
    private int radius;
    private static final int[] palette = {Color.CYAN,Color.LTGRAY,Color.BLUE,Color.GREEN,Color.RED};

    public AstreCeleste(int id,String nom, int taille, String couleur, boolean status, int image) {
        this.id = id;
        this.nom = nom;
        this.taille = taille;
        this.couleur = couleur;
        this.status = status;
        this.image = image;
    }

    @Override
    public String toString() {
        return "AstreCeleste{" +
                "nom='" + nom + '\'' +
                ", taille=" + taille +
                ", couleur='" + couleur + '\'' +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public AstreCeleste() {
        aleatoire = new Random();
        posX = aleatoire.nextInt(500);
        posY = aleatoire.nextInt(500);

        crayon = new Paint();
        crayon.setAntiAlias(true);
        crayon.setColor(palette[aleatoire.nextInt(5)]);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;

        if(!this.status)
        {
            crayon.setColor(Color.TRANSPARENT);
        }
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    protected void onDraw(Canvas canvas)
    {
        //canvas.drawCircle(posX,posY,radius,crayon);

    }
}
