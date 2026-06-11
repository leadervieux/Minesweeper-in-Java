import java.awt.Point;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Choisissez le niveau 1, 2, 3:");
        System.out.println("1) Débutant");
        System.out.println("2) Intermédiaire");
        System.out.println("3) Avancé");
        System.out.println("Niveau :");

        int niv= sc.nextInt();
        String niveau = "Débutant";


        switch (niv) {
            case 1:
                niveau = "Débutant";
                break;
            case 2:
                niveau = "Intermédiaire";
                break;
            case 3:
                niveau = "Avancé";
                break;
            default:
                System.out.println("Niveau invalide, niveau Débutant par défaut.");
                break;
        }

        Partie partie = Partie.setUniqueInstance(niveau);

        while (partie.isEncours()) {

            partie.afficher();

            System.out.println("1 - Marquer une case \n 2 - Decouvrir une case \n 0 - Quitter");

            System.out.print("Choix : ");
            int choix = sc.nextInt();

            if (choix == 0) {
                System.out.println("Fin du jeu.");
                break;
            }

                System.out.print("Ligne : ");
                int x = sc.nextInt();
                System.out.print("Colonne : ");
                int y = sc.nextInt();

                Point p = new Point(x, y);

                if (choix == 1) {
                    partie.marquerCase(p);
                } else if (choix == 2) {
                    partie.decouvrirCase(p);
                }

            partie.testerSiGagne();
        }
        partie.afficher();

        sc.close();
    }
}
