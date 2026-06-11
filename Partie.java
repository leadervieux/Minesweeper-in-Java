import java.awt.*;

public  class Partie {

    // ATTRIBUTS

    private static Partie uniqueInstance;
    private int nbMinesInitial = 10;
    private int nbMinesRestantes;
    private static String niveau = "";
    private boolean encours;
    private boolean resultat;
    private Plateau jeu;

    // CONSTRUCTEURS

    private Partie(String m_niveau){
        this.niveau = m_niveau;
        this.jeu = new Plateau(this);
        this.encours = true;
        this.nbMinesRestantes = nbMinesInitial;

    }

    //METHODES


    public static Partie getUniqueInstance() {
        return uniqueInstance;
    }

    public static Partie setUniqueInstance(String niveau) {
        if (uniqueInstance==null)
        {
            uniqueInstance = new Partie(niveau);
        }
        return uniqueInstance;
    }

    public int getNbMinesInitial() {
        if (niveau.equals("Débutant")){
            return nbMinesInitial = 10;
        }
        else if (niveau.equals("Intermédiaire"))
        {
            return nbMinesInitial = 40;
        }
        else if (niveau.equals("Avancé")) {
            return nbMinesInitial = 99;
        }
        return nbMinesInitial;
    }

    public int getNbMinesRestantes() {
        return nbMinesRestantes;
    }


    public String getNiveau() {
        return niveau;
    }

    public boolean isEncours() {
        return encours;
    }

    public boolean isResultat() {
        return resultat;
    }

    public void marquerCase(Point pt)
    {this.jeu.marquerCase(pt);}

    public void decouvrirCase(Point pt){
        this.jeu.decouvrirCase(pt);
    }

    public void decrNbMines()
    {
        nbMinesRestantes --;
    }

    public void incrNbMines(){
        nbMinesRestantes ++;
    }

    public void perdre(){
        System.out.println("C'est perdu...");
        this.encours = false;
    }

    public boolean testerSiGagne(){

        if (this.jeu.testerSiGagne()){
            this.encours = false;
            this.resultat = true;
        }
        return resultat;
    }

    public void afficher(){
        System.out.println("Nb mines restantes :" + nbMinesRestantes);
        this.jeu.afficher();
    }

}
