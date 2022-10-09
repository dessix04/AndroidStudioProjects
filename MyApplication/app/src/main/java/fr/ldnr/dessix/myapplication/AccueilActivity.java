package fr.ldnr.dessix.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class AccueilActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        // equivalent de getElementById du javascript
        lireNews();
        Button btCarte = findViewById(R.id.btCarte);
        btCarte.setOnClickListener(this);

        Button bAnnuaire = findViewById(R.id.bAnnuaire);
        bAnnuaire.setOnClickListener(this);


        Button btAlerte = findViewById((R.id.btAlerte));
        btAlerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(AccueilActivity.this, AlerteActivity.class);
                    startActivity(i);
            }
        });
        // boutton annuaire
       /* Button bAnnuaire = findViewById(R.id.bAnnuaire);
        bAnnuaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(AccueilActivity.this, AnnuaireActivity.class);
                    startActivity(i);
                }

        }); */
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btCarte) {
            Intent i = new Intent(this, CarteActivity.class);
            startActivity(i);
        }

        if(view.getId() == R.id.bAnnuaire) {
            Intent i = new Intent(this, AnnuaireActivity.class);
            startActivity(i);
        }

    }

    // afficher un menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acceuil, menu);
        // lecture

        SharedPreferences sp = getSharedPreferences("My Application", MODE_PRIVATE);
        boolean envoi = sp.getBoolean("envoyer", false);
        MenuItem item = menu.findItem((R.id.menu_envoyer));
        item.setChecked(envoi);
        return true;
    }

    // equivalent de onclick pour le menu
    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
     /*   if(item.getItemId()==R.id.menu_carte){
                Intent i = new Intent(this, CarteActivity.class);
                startActivity(i);
            }
        if(item.getItemId()==R.id.menu_alerte){
            Intent i = new Intent(this, AlerteActivity.class);
            startActivity(i);
        } */
        switch (item.getItemId()){
            case R.id.menu_carte:
                startActivity(new Intent(this, CarteActivity.class));
                break;
            case R.id.menu_alerte:
                startActivity(new Intent(this,AlerteActivity.class));
                break;
            case R.id.menu_envoyer:
               /* if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true); */
                item.setChecked( ! item.isChecked() );
                // ecrire dans les shared preferences
                // equivalent de session
                // création de block
                SharedPreferences sp = getSharedPreferences("My Application", MODE_PRIVATE);

                // création d'un éditor
                SharedPreferences.Editor e = sp.edit();
                // ecrire dans un editeur
                e.putBoolean("envoyer", item.isChecked());
                e.commit(); // ça écrit // c'est pour faciliter les gestions de conflit
                break;
            case R.id.menu_espece:
                startActivity(new Intent(this,EspeceActivity.class));
                break;
            case R.id.menu_animal:
                startActivity(new Intent(this,AnimalActivity.class));
                break;
        }
        return true;
    }

    private void lireNews(){
        TextView tvNews = findViewById(R.id.tvNews);

        try{
            InputStream is = getAssets().open("news.txt");
            Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(r);
            String tout = "", ligne;
            while((ligne = br.readLine()) != null){
                tout += ligne + "\n";
            }
            is.close();
            tvNews.setText(tout);
        } catch (Exception ex){
            Log.e("AccueilActivity", "Erreur lecture asset", ex);
        }

    }
}
