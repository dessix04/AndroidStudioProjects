package fr.ldnr.dessix.myapplication;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CarteActivity extends Activity {
    // pour faire notre propre log
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CarteView(this));// c'est la vue dans le controller
        Log.i("CarteActivity", "onCreate terminé");

        // comment recuperer une chaine
        String recupChaine = getString(R.string.carte_bienvenue);
        Toast.makeText(this, recupChaine, Toast.LENGTH_LONG).show();
    }


    // l'équivalent de onClick en javaScript
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // log où l'usager a appuyé
        Log.d("Aquarium", "Appui à "+event.getX()+" * "+event.getY());

        // si on lache la souris
       // if ((event.getX() >10) ){
       if((event.getActionMasked() == MotionEvent.ACTION_UP &&(event.getX() <50)
        && (event.getY() < 400) ) ) {
            // préparation de l'intention pour l'appel des activités
            Intent i = new Intent(this, AquariumActivity.class);
            startActivity(i); // l'appel de l'activite
        } /* else {
        // return à popcorn partout ou on clic partout mais different de X et Y
           Intent i = new Intent(this, PopcornActivity.class);
           startActivity(i); // l'appel de l'activite
       } */

        // pour ouvrir un lien dans le navigateur ou montrer une page web
        if (event.getActionMasked()==MotionEvent.ACTION_UP && event.getX() >= 200){
            final String page = "https://www.mangerbouger.fr/";

            Uri uri = Uri.parse(page);

            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            try{
                startActivity(i);
            } catch (ActivityNotFoundException ex ){
                Log.e("CarteActivity", "Pas de navigateur", ex);
            } finally {
                // quoi qu'il en soit, le code qu'on veut executer
                // mettre ici les instructions à faire après,
                // qu'il y aie une exception
            }
        }

        return true;
    }

    // la view
    public class CarteView extends View{
        // controller
        public CarteView(Context context) {
            super(context);
        }

        // une methode à appeler si on a envi d'afficher
        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.carte);
            canvas.drawBitmap(img, 0, 0, null);
        }
    }
}
