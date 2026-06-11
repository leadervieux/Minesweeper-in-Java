public class CaseMinee extends Case {

    public CaseMinee(){
        setEtatCourant(new EtatCouverte());
    }

    @Override
    public void devoiler(){
    setEtatCourant(new EtatDecouverte());
    }

    public void afficher() {
        if (getEtatCourant() instanceof EtatDecouverte) {
            System.out.print("M");
        }
        if (getEtatCourant() instanceof EtatCouverte) {
            System.out.print("*");
        }
        if (getEtatCourant() instanceof EtatMarquee) {
            System.out.print("%");
        }
    }


}