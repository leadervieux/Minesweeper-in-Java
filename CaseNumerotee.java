public class CaseNumerotee extends Case{

    private int nbVoisinesMinees;

    public CaseNumerotee(int nb) {
        nbVoisinesMinees = nb;
        setEtatCourant(new EtatCouverte());

    }

    @Override
    public void devoiler(){
        super.devoiler();
    }

    public void afficher(){
        if (getEtatCourant() instanceof EtatDecouverte) {
            System.out.print(nbVoisinesMinees);
        }
        else if (getEtatCourant() instanceof EtatMarquee) {
            System.out.print("%");
        }
        else {
            System.out.print("*");
        }
    }

    public void incNbVoisinesMinees(){
        nbVoisinesMinees++;
    }
}
