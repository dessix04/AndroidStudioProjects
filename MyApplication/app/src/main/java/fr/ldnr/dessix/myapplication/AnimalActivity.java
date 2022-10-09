package fr.ldnr.dessix.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AnimalActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_animal);

        Button btAnimalEnreg = findViewById(R.id.btAnimal);
        btAnimalEnreg.setOnClickListener(this);

        ConnectBDD connectBDD = new ConnectBDD(this);
        List<String> enclos = connectBDD.listerOuLireEnclos();

        Spinner etIdEnclos = findViewById(R.id.etIdEnclos);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, enclos);
        etIdEnclos.setAdapter(aa);

        List<String> espece = connectBDD.listerOuLireEspece();

        Spinner etIdEspece = findViewById(R.id.etIdEspece);

        ArrayAdapter<String> ee = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, espece);
        etIdEspece.setAdapter(ee);
    }


    @Override
    public void onClick(View view) {
        // Inserer l'animal
        // attention : convertir la date // à retrouver dans la partie où on a utilisé calendar
        // attention : trouver l'id de l'espece et de l'enclos
        Toast.makeText(this, android.R.string.ok, Toast.LENGTH_SHORT).show();
    }
}
