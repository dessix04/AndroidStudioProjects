package fr.ldnr.dessix.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class AnnuaireActivity extends Activity {

    private long debut, fin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annauaire);
        ecrireLog("Ouverture\n");
        debut = System.currentTimeMillis();

        // pour demander ou verifier si on a droit à faire telle chose ou permission
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) incrementer();
        else {
            requestPermissions(new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
           // Log.i("AnnuaireActivity", "incrementation");
        }
    }

    // cette fonction est appelele lorsqu'on repond aux requestPermissions
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED) incrementer();
    }

    private void incrementer(){
        Log.i("AnnuaireActivity", "incrementation dans le fichier externe");
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File repertoire = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
            );
            File fichierCompteur = new File(repertoire, "MyApp_compteur.txt");
            try {
                int n=0;
                if (fichierCompteur.exists()) {
                    FileReader fr = new FileReader(fichierCompteur); // lire le fichier
                    BufferedReader br= new BufferedReader(fr);
                    String ligne = br.readLine();
                    n = Integer.parseInt(ligne);
                    fr.close();
                }
                n++;
                    FileWriter fw = new FileWriter(fichierCompteur, false);// créer ou ecrire dans le fichier
                    fw.write(n+"\n");
                    fw.close();

            } catch (Exception ex){
                Log.e("AnnuaireActivity", "Erreur compteur", ex);
            }
        };
    }

    @Override
    public void onBackPressed() {
        fin = System.currentTimeMillis();
        long duree = (fin - debut)/1000;
        ecrireLog("Fermeture après"+"  "+duree+"\n");
        super.onBackPressed(); // ordonne la fermeture de l'activité
    }

    private void ecrireLog( String message){
        try{
            // fichier interne ou fichier privé n'a besoin aucune permission
         OutputStream os = openFileOutput("annuaire.log.txt", MODE_APPEND);
            Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            w.write(message);
            w.close();
            // autres fonctions sur fichiers internes
            // deleteFile() pour supprimer
            // fileList() renvoi une liste de tous les fichiers internes
            // if(new File(getFilesDir(), "...log.txt").exists()); pour savoir si un fichier existe
            // new File(getFilesDir(), "...log.txt").length(); pour savoir le nombre de caracteres
        } catch (Exception ex){
            Log.e("Annuaire activity", "Erreur ecriture log", ex);
        }
    }

}
