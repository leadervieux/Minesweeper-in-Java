public class EtatCouverte extends EtatCase{

    @Override
    public void decouvrir(Case c){
        c.setEtatCourant(new EtatDecouverte());
        c.devoiler();
    }

    @Override
    public void marquer(Case c){
        c.setEtatCourant(new EtatMarquee());
        Partie.getUniqueInstance().decrNbMines();

    }

}
