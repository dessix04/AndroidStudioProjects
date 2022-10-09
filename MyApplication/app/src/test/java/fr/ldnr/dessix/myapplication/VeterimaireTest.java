package fr.ldnr.dessix.myapplication;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class VeterimaireTest {
    @Test
    public void testVet(){
        System.out.println("**** Veterenaires");
       String nom = " hamburger ";
       //String debut1 = nom.substring(5,8);
       String sansEspace = nom.trim(); // sans espace
       String enMaj = sansEspace.substring(0,1).toUpperCase();// recupère la 1ère lettre et le met en majuscule
        String enMin= sansEspace.substring(1);// tout en minuscule en supprimant la 1ère lettre
        String rendu = enMaj + enMin;
        System.out.println(sansEspace);
        System.out.println(enMaj);
        System.out.println(enMin);
        System.out.println(rendu);

    }
    @Test
    public void TestCalendrier(){
        Calendar maintenant = Calendar.getInstance();
        Calendar lundiMatin = Calendar.getInstance();
        lundiMatin.set(2022, 7, 29, 8, 0, 0);// modifier
        lundiMatin.add(Calendar.HOUR, 1); // ajouter
        long ms = lundiMatin.getTimeInMillis() - maintenant.getTimeInMillis();
        long heure = (ms/3600000);
        long minute = (ms/60000)%60;

        System.out.println(maintenant.get(Calendar.DATE));
        System.out.println("lundi ouverture à : "+ lundiMatin.get(Calendar.DAY_OF_YEAR));
        System.out.println("Ouverture dans "+heure+" h et "+minute+" minutes");
    }
    @Test
    public void TestColection(){
        ArrayList<String> sujets = new ArrayList<>();
        sujets.add("Bob Merlan");
        sujets.add("Ann Chevre");
        sujets.add("Carl Lion");

        System.out.println("Il y a "+sujets.size()+" examens.");
        System.out.println("premier personne "+sujets.get(0));

        for (String s:
             sujets) {
            System.out.println(" - "+s);
        }

        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("Ann Chevre", 6);
        ages.put("Coin Chevre", 2);
        System.out.println(ages.get("Ann Chevre"));

    }
}
