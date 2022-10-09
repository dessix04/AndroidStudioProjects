package fr.ldnr.dessix.myapplication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLOutput;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    // indique un test
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 +2 );
        String d = "Hello";
        int a = 8;
        System.out.println(d+", il y a djibril dans la salle");
        if (a==8){
            System.out.println("*****C'est beaucoup");
        }
        while (a>0){
            System.out.println("Je suis Djibril");
            a -= 1.5;
        }
        for(int i =0; i<3; i++){
            System.out.println("Aquarium "+(i+1));
        }
        for(int i =0; i<3; i++){
            System.out.println("Aquarium "+(i+1));
        }
        String [] nomAquariums = new String[3];
        nomAquariums[0] = "L'océanie";
        nomAquariums[1] = "L'océanie1";
        nomAquariums[2] = "L'océanie2";
        String [] stagiaire = {"Mehdi", "Djibril", "Jean"};
        for (String n: nomAquariums) {
            System.out.println(n);
        }
        second_class_deTest();

    }
    public void second_class_deTest(){
        System.out.println("utilisation d'une fonction après l'avoir appeler dans la fonction ci_dessous");
        affichetaille(12.3, 24.6, 4.3);
        affichetaille(12.3, 24.6);

       // Poisson p1 = new Poisson();
       // p1.nom = "Bob"; p1.espece = "Merlan";
        Poisson p1 = new Poisson("Bob", "Merlan");
        p1.afficher();
    }

    public class Poisson{
        public String nom, espece;
        public Poisson(String nom, String espece){
            this.nom = nom;
            this.espece = espece;
        }
        public void afficher(){
            System.out.println(nom+"( "+espece+")");
        }
    }
    public void affichetaille(double longueur, double largeur, double hauteur){
        System.out.println(longueur+"*"+largeur+"*"+hauteur+"m: "+
                calculeTaille(longueur, largeur, hauteur)+"m3");
    }
    public double calculeTaille(double longueur, double largeur, double hauteur){
        return longueur * largeur * hauteur;
    }
    public void affichetaille(double longueur, double largeur){
        System.out.println(longueur+"*"+largeur+"m: "+
                calculeTaille(longueur, largeur)+"m²");
    }
    public double calculeTaille(double longueur, double largeur){
        return longueur * largeur;
    }

}