package fr.ldnr.dessix.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EspeceActivity extends Activity implements View.OnClickListener  {

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // affiche le layout creer_espece
        // on doit ecrire cette partie avant tout code qui manipule ce layout
        setContentView(R.layout.creer_espece);

        // toujours ecrire les bouttons sous le setContent
        Button btREspece = findViewById(R.id.btRechercher);
        btREspece.setOnClickListener(this);

        // ecoute du boutton enregistrer
        Button btEnregistrer = findViewById(R.id.btEspece);
        // avec une classe anonyme
        btEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNomEspece = findViewById(R.id.etNomEspece);
                String nom = etNomEspece.getText().toString();

                EditText etAgeMax = findViewById(R.id.etAgeMax);
                // reconversion de texte en integer quand on veut recuperer la valeur entrée à ce niveau
                //int ageMax = Integer.parseInt(etAgeMax.getText().toString());

                Integer ageMax = null;
                if(!etAgeMax.getText().toString().isEmpty()){
                    ageMax = Integer.parseInt(etAgeMax.getText().toString());
                }
                ConnectBDD connectBDD = new ConnectBDD(EspeceActivity.this);
                connectBDD.ajouterEspece(nom, ageMax);
                Toast.makeText(EspeceActivity.this, android.R.string.ok, Toast.LENGTH_SHORT).show();
            }
        });

        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onClick(View view) {
        TextView tvResultat = findViewById(R.id.tvREspece);
        tvResultat.setText("........");
        // pour executer plusieurs processus
        // executeur de service chargé d'effectuer les taches
        // execution
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                trouver();
            }
        });

    }

    // trouver
    private void trouver(){
        Log.i("Espece Activity", "Debut de recherche");

        try {
            EditText etNomEspece = findViewById(R.id.etNomEspece);
            String espece = etNomEspece.getText().toString();

            String adressWp = "https://fr.wikipedia.org/w/api.php?"+
                    "action=query&prop=extracts&exsentences=2&format=json&titles="+
                    URLEncoder.encode(espece, "UTF-8");
            URLConnection connection = new URL(adressWp).openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    inputStream, StandardCharsets.UTF_8
            ));
            String tout = "", ligne;

            while ((ligne = bufferedReader.readLine())!= null){
                tout += ligne + "\n";
            }

            Log.i("EspeceActivity", "Lecture WP : "+ tout);
            JSONObject racine = new JSONObject(tout);
            JSONObject query = racine.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");

            String numeroPage = pages.keys().next();
            JSONObject page = pages.getJSONObject(numeroPage);
            String extract = (page.getString("extract"));

            Log.i("EspeceActivity", "Lecture WP : "+ extract);


            handler.post(new Runnable() {
                @Override
                public void run() {
                    TextView tvREspece = findViewById(R.id.tvREspece);
                    tvREspece.setText(Html.fromHtml(extract));
                }
            });

            inputStream.close();


        } catch (Exception ex){
            Log.e("Espece Activity", "Erreur recherche", ex);
        }

        Log.i("Espece Activity", "Fin de recherche");
    }
}
