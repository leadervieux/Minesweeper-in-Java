public class CaseVide extends Case{


    public CaseVide(){
        setEtatCourant(new EtatCouverte());
    }

    @Override
    public void devoiler(){
        super.devoiler();
        if (nbMinesVoisines == 0){
            for (Case v : Voisines){
                if (v.getEtatCourant() instanceof EtatCouverte){
                    v.devoiler();
                }
            }
        }
    }

    @Override
    public void afficher(){
        if (getEtatCourant() instanceof EtatDecouverte) {
            System.out.print(" ");
        }
        if (getEtatCourant() instanceof EtatCouverte) {
            System.out.print("*");
        }
        if (getEtatCourant() instanceof EtatMarquee) {
            System.out.print("%");
        }
    }
}
