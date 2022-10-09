package fr.ldnr.dessix.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlerteActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerte);

        String[] lieux = getResources().getStringArray(R.array.lieux);
        //liste d'element à adapter à des
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line,
                lieux);

        AutoCompleteTextView etLieu = findViewById(R.id.etLieu);
        etLieu.setAdapter(aa);
    }

    public void envoyer(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alerte_djibril);
        builder.setMessage(R.string.confirmer_alerte);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmer();
                    }
                });
        builder.setNegativeButton(android.R.string.no, null);
        builder.show();
    }

    private void confirmer(){
        EditText etIntitule = findViewById(R.id.etIntitule);
        String intitule = etIntitule.getText().toString();

        // pour lire les shared preferences d'une autre activité
        SharedPreferences sp = getSharedPreferences("My Application", MODE_PRIVATE);
        boolean envoi = sp.getBoolean("envoyer", false);

        if(!envoi){
            Toast.makeText(this, "Envoi désactivé", Toast.LENGTH_LONG).show();
            return;
        }

        if (intitule.trim().length() > 1) {

            String message = "Envoi ("+intitule+") ok";

            CheckBox cbUrgent = findViewById(R.id.cbUrgent);
            if(cbUrgent.isChecked()){
                message += "!";
            }

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Intitulé Incorrect", Toast.LENGTH_LONG).show();
        }
;
    }
}
