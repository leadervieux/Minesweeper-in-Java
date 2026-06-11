public class EtatMarquee extends EtatCase{

    @Override
    public void decouvrir(Case c){}

    @Override
    public void marquer(Case c){
        c.setEtatCourant(new EtatCouverte());
        Partie.getUniqueInstance().incrNbMines();
    }
}
