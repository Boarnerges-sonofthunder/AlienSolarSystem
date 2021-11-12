package com.example.solarsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AstreDBHelper extends SQLiteOpenHelper {

    public static final String ASTRE_CELESTE_TABLE = "astreCeleste";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_TAILLE = "Taille";
    public static final String COLUMN_COULEUR = "Couleur";
    public static final String COLUMN_STATUS = "Status";
    public static final String COLUMN_IMAGE = "Image";
    public static final String COLUMN_POS_X = "posX";
    public static final String COLUMN_POS_Y = "posY";

    public AstreDBHelper(@Nullable Context context) {
        super(context,"AstreSolarSytemeDB.bd",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + ASTRE_CELESTE_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOM + " TEXT," + COLUMN_TAILLE + " INTEGER," + COLUMN_COULEUR + " TEXT," + COLUMN_STATUS + " BIT," + COLUMN_IMAGE + " INTEGER," + COLUMN_POS_X + " INTEGER," + COLUMN_POS_Y + " INTEGER)";

        db.execSQL(sql);
    }

    //TODO: AJouter les autre astres
    public boolean addAll(AstreCeleste astreCeleste)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        astreCeleste.setNom("Arcas");
        cv1.put(COLUMN_NOM,astreCeleste.getNom());
        astreCeleste.setTaille(40);
        cv1.put(COLUMN_TAILLE, astreCeleste.getTaille());
        astreCeleste.setCouleur(String.valueOf(Color.GRAY));
        cv1.put(COLUMN_COULEUR, astreCeleste.getCouleur());
        astreCeleste.setStatus(true);
        cv1.put(COLUMN_STATUS,astreCeleste.isStatus());
        //astreCeleste.setImage(R.drawable.arcas);
        cv1.put(COLUMN_IMAGE,astreCeleste.getImage());
        cv1.put(COLUMN_POS_X,astreCeleste.getPosX());
        cv1.put(COLUMN_POS_Y,astreCeleste.getPosY());

        long insert = db.insert(ASTRE_CELESTE_TABLE, null, cv1);
        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

        /*ContentValues cv2 = new ContentValues();
        astreCeleste.setNom("Brahe");
        cv2.put(COLUMN_NOM,astreCeleste.getNom());
        astreCeleste.setTaille(40);
        cv2.put(COLUMN_TAILLE, astreCeleste.getTaille());
        astreCeleste.setCouleur("Transparent");
        cv2.put(COLUMN_COULEUR, astreCeleste.getCouleur());
        astreCeleste.setStatus(true);
        cv2.put(COLUMN_STATUS,astreCeleste.isStatus());
        astreCeleste.setImage(R.drawable.brahe);
        cv2.put(COLUMN_IMAGE,astreCeleste.getImage());

        db.insert(ASTRE_CELESTE_TABLE,null,cv2);*/
    }

    public List<AstreCeleste> getAll()
    {
        //Creation d'une liste
        List<AstreCeleste> returnList = new ArrayList<>();

        //remplir la liste avec une requete sur le db
        String sql = "SELECT * FROM " + ASTRE_CELESTE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        //creation du curseur
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst())
        {
            //faire un loop dans le resultat si il y en a un
            do{
                int astreId = cursor.getInt(0);
                String astreNom = cursor.getString(1);
                int astreTaille = cursor.getInt(2);
                String astreCouleur = cursor.getString(3);
                boolean astreStatus = cursor.getInt(4) == 1 ? true:false;
                int astreImage = cursor.getInt(5);
                int astrePosX = cursor.getInt(6);
                int astrePosY = cursor.getInt(7);


                //creation d'un objet AstreCeleste et lui attribuer les curseurs
                AstreCeleste newAstre = new AstreCeleste(astreId,astreNom,astreTaille,astreCouleur,astreStatus,astreImage,astrePosX,astrePosY);
                //ajout de l'astre dans la liste
                returnList.add(newAstre);

            }while(cursor.moveToNext());
        }

        //Fermer le curseur et la db
        cursor.close();
        db.close();
        return returnList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
