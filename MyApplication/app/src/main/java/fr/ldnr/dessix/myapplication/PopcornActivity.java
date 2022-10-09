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

import java.util.Random;

public class PopcornActivity extends Activity {
    public final static String CLE_DUREE = "dureePopcorn";
    private long fin, debut;
    // pour faire notre propre log
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PopcornView(this));// c'est la vue dans le controller

        long duree = getIntent().getLongExtra("duree", 0)/1000;
        if(duree>2){
            String[] tab_poissons = getResources().getStringArray(R.array.tableau_poissons);
            int indexe = new Random().nextInt(tab_poissons.length);
            String avertissement = getString(R.string.popcorn_avertissement, duree, tab_poissons[indexe]);
            Toast.makeText(this, avertissement
                    , Toast.LENGTH_LONG).show();
        }

        debut=System.currentTimeMillis();
        Log.i("PopcornActivity", "onCreate terminé");
      //  Toast.makeText(this, "Bienvenue", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        fin = System.currentTimeMillis();
        long dureePopcorn = fin - debut;
        Intent i = new Intent();
        i.putExtra(PopcornActivity.CLE_DUREE, dureePopcorn);
        setResult(0, i);
        super.onBackPressed();
    }

    // l'équivalent de onClick en javaScript
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // préparation de l'intention de l'appel
            Intent i = new Intent(this, CarteActivity.class);
            //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // enlever les animations ou defilements lors du chargement
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i); // l'appel de l'activite
            return true;
        }


    // la view
    public class PopcornView extends View {
        // controller
        public PopcornView(Context context) {
            super(context);
        }

        // une methode à appeler si on a envi d'afficher
        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.popcorn1);
            canvas.drawBitmap(img, 0, 0, null);
        }
    }
}
