package fr.ldnr.dessix.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConnectBDD extends SQLiteOpenHelper {

    public ConnectBDD(@Nullable Context context) {
        super(context, "myApp3.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creationEnclos = "CREATE TABLE Enclos( id_enclos INTEGER, nom_enclos TEXT NOT NULL UNIQUE, "+
                "surface INTEGER NOT NULL, PRIMARY KEY(id_enclos))";
        String creationEspece = "CREATE TABLE Espece( id_espece INTEGER, nom_espece TEXT NOT NULL UNIQUE, "+
                "ageMax INTEGER, PRIMARY KEY(id_espece))";
        String creationAnimal = "CREATE TABLE Animal( id_animal INTEGER, nom_animal TEXT NOT NULL, "+
                "age INTEGER NOT NULL, date_darrivee DATE NOT NULL, id_enclos INTEGER NOT NULL, "+
                "id_espece INTEGER NOT NULL, PRIMARY KEY(id_animal), "+
                "FOREIGN KEY(id_espece) REFERENCES Espece(id_espece),"+
                "FOREIGN KEY(id_enclos) REFERENCES Enclos(id_enclos))";
        sqLiteDatabase.execSQL(creationEnclos);
        sqLiteDatabase.execSQL(creationEspece);
        sqLiteDatabase.execSQL(creationAnimal);

        String insert_enclos = "INSERT INTO Enclos (nom_enclos, surface) VALUES (?, ?)";
        sqLiteDatabase.execSQL(insert_enclos, new Object[]{"Enclos des singes", 87200 });
        sqLiteDatabase.execSQL(insert_enclos, new Object[]{"Enclos des lions", 23200 });
        sqLiteDatabase.execSQL(insert_enclos, new Object[]{"Enclos des pélicans", 3200 });
        sqLiteDatabase.execSQL(insert_enclos, new Object[]{"Enclos des dauphins", 23200 });
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void ajouterEspece (String nom_espece, Integer ageMax) {
        String insert_espece = "INSERT INTO Espece (nom_espece, ageMax) VALUES ( ?, ? )";
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(insert_espece, new Object[]{nom_espece, ageMax});
        db.close();
    }

    public List<String> listerOuLireEnclos(){
        String lecture = "SELECT nom_enclos FROM Enclos ORDER BY nom_enclos";
        SQLiteDatabase db= getReadableDatabase();
        Cursor c = db.rawQuery(lecture, null);

        ArrayList<String> resultat = new ArrayList<>();
        while (c.moveToNext()){
            resultat.add(c.getString(0));
        }

        db.close();
        return resultat;
    }

    public List<String> listerOuLireEspece(){
        String lecture = "SELECT nom_espece FROM Espece ORDER BY nom_espece";
        SQLiteDatabase db= getReadableDatabase();
        Cursor c = db.rawQuery(lecture, null);

        // recuperation des valeurs
        ArrayList<String> resultat = new ArrayList<>();
        while (c.moveToNext()){
            resultat.add(c.getString(0));
        }

        db.close();
        return resultat;
    }
}
