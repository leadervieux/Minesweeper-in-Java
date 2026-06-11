
import java.util.ArrayList;

//Pas d'extend Plateau car une case NEST PAS un plateau
public abstract class Case{

    //ATTRIBUTS

    private EtatCase EtatCourant;
    protected ArrayList<Case> Voisines;
    protected int nbMinesVoisines;


    //CONSTRUCTEURS

    public Case(){

        this.setEtatCourant(null);
        this.Voisines = new ArrayList<>();
        this.nbMinesVoisines = 0;
    }


    //GETTEURS ET SETTEURS

    public EtatCase getEtatCourant() {
        return EtatCourant;
    }

    public void setEtatCourant(EtatCase etatCourant) {
        EtatCourant = etatCourant;
    }

    public ArrayList<Case> getVoisines() {
        return Voisines;
    }


    //METHODES

    public void marquer(){
        EtatCourant.marquer(this );
    }

    public void decouvrir(){
        EtatCourant.decouvrir(this);
    } //Lorsque l'on met les coordonnées

    public void devoiler(){
        EtatCourant.decouvrir(this);
    }

    public abstract void afficher();

    public void ajouterVoisine(Case c){
        Voisines.add(c);
        if (c instanceof CaseMinee){
            nbMinesVoisines++;
        }
    }
}
