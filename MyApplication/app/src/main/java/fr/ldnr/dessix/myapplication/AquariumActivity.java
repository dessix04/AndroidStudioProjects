package fr.ldnr.dessix.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AquariumActivity extends Activity {


    private long debut, fin,dureePopcorn=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AquariumView(this));// c'est la vue dans le controller

        // equivalent de time() du php
        debut = System.currentTimeMillis();


        Log.i("AquariumActivity", "onCreate terminé");
       // Toast.makeText(this, "Bienvenue", Toast.LENGTH_LONG).show();
    }

    // l'équivalent de onClick en javaScript
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // préparation de l'intention de l'appel
        // pour maitriser le realche de la souris
        if(event.getActionMasked()==MotionEvent.ACTION_UP) {
            Intent i = new Intent(this, PopcornActivity.class);

            // calcul de la durée
            fin = System.currentTimeMillis();
            long duree = fin - debut - dureePopcorn;
            // ajout additionnel à l'intention
            i.putExtra("duree", duree);
            // i.putExtra("information", 98347);

            startActivityForResult(i, 0); // l'appel de l'activite
        } return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         dureePopcorn = data.getLongExtra(PopcornActivity.CLE_DUREE, 0);
    }

    // la view
    public class AquariumView extends View {
        // controller
        public AquariumView(Context context) {
            super(context);
        }

        // une methode à appeler si on a envi d'afficher
        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.aquarium);
            canvas.drawBitmap(img, 0, 0, null);
        }
    }
}
