import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Plateau{

    //ATTRIBUTS

    private int largeur = 9;
    private int hauteur = 9;
    private Map<Point, Case> coordonnees;
    private Partie partie;

    //CONSTRUCTEURS

    public Plateau(Partie p){
        this.partie = p;

        // Init cases vides
        this.coordonnees = new HashMap<>();
        if (partie.getNiveau() == "Intermédiaire"){
            largeur = 16;
            hauteur = 16;
        }
        else if (partie.getNiveau() == "Avancé"){
            largeur = 16;
            hauteur = 32;
        }

        for (int i = 0; i<largeur; i++){
            for (int j= 0; j<hauteur; j++){
                coordonnees.put(new Point(i, j),new CaseVide());
            }
        }

//Placement des mines
        Random rand = new Random();
        int minesPlacees = 0;

        while (minesPlacees < partie.getNbMinesInitial()){

            int x = rand.nextInt(largeur);
            int y = rand.nextInt(hauteur);
            Point position = new Point(x, y);

            Case c = coordonnees.get(position);
            if (!(c instanceof CaseMinee)) {
                coordonnees.put(position, new CaseMinee());
                minesPlacees++;
            }
        }


        //Numérotation des cases
        for (int i=0; i<largeur; i++){
            for (int j=0; j<hauteur; j++){


                Point position = new Point(i, j);
                Case c = coordonnees.get(position);

                if (!(c instanceof CaseMinee)){

                    int compteur = 0;

                    // Vérifier les 8 cases adjacentes
                    for (int dx = -1; dx <= 1; dx++){
                        for (int dy = -1; dy <= 1; dy++){
                            // Ignorer la case elle-même
                            if (dx == 0 && dy == 0) continue;

                            int nx = i + dx;
                            int ny = j + dy;

                            // Vérifier que c'est dans les limites du plateau
                            if (nx >= 0 && nx < largeur && ny >= 0 && ny < hauteur){
                                Point voisin = new Point(nx, ny);
                                Case c1 = coordonnees.get(voisin);

                                if (c1 instanceof CaseMinee) {
                                    compteur++;
                                }
                            }
                        }
                    }

                    if (compteur > 0) {
                        coordonnees.put(position, new CaseNumerotee(compteur));
                    }
                }
            }
        }

        // Liaison des voisines
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {

                Case c = coordonnees.get(new Point(i, j));

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {

                        if (dx == 0 && dy == 0) continue;

                        int nx = i + dx;
                        int ny = j + dy;

                        if (nx >= 0 && nx < largeur && ny >= 0 && ny < hauteur) {
                            Case voisine = coordonnees.get(new Point(nx, ny));
                            c.ajouterVoisine(voisine);
                        }
                    }
                }
            }
        }

    }

    // METHODES


    public boolean testerSiGagne(){
        //Si on a une case qui n'est pas minée, qui n'est pas découverte, alors on n'a pas gagné
        for (Case c : coordonnees.values()){
            if (!(c instanceof CaseMinee)) {
                if (!(c.getEtatCourant() instanceof EtatDecouverte)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void marquerCase(Point pt){
        Case c = coordonnees.get(pt);
        if (c != null){
            c.marquer();
        }
    }

    public void decouvrirCase(Point pt) {


        Case c = coordonnees.get(pt);
        if(c != null){
            c.decouvrir();
        }

        if (c instanceof CaseMinee){
            partie.perdre();


            for (Case caseTmp : coordonnees.values()){
                if (caseTmp instanceof CaseMinee){
                    caseTmp.setEtatCourant(new EtatDecouverte());
                }
            }
        }

        else if (testerSiGagne()){
            System.out.println("C'est gagné");
        }

    }

    public void afficher(){

        System.out.print("   ");
            for (int j = 0; j < hauteur; j++) {
                System.out.printf("%3d",j);
            }
            System.out.println();
            for (int i = 0; i < largeur; i++) {
                System.out.printf("%2d| ",i);

                for (int j = 0; j < hauteur; j++) {
                    Point p = new Point(i, j);
                    Case c = coordonnees.get(p);
                    System.out.print(" ");
                    c.afficher();
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

}

