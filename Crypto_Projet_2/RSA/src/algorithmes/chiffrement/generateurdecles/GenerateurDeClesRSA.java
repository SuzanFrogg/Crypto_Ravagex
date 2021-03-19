package algorithmes.chiffrement.generateurdecles;

import algorithmes.chiffrement.RSA.ParametresRSA;
import algorithmes.chiffrement.RSA.RabinMiller;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.Cle;
import donnees.cles.CleBinaire;
import donnees.cles.Cles;
import java.util.logging.Logger;

/**
 * Description de la classe
 * @author Matthieu
 */
public class GenerateurDeClesRSA implements GenerateurDeCles{

    
    private NombreBinaire P;
    private NombreBinaire Q;
    private NombreBinaire N;;
    private NombreBinaire phi;
    private NombreBinaire e;
        
    @Override
    public Cles genererClePublique() {
       //TODO
       return null;
    }

    /**
     * Création de la clé privée à partir de P Q, phi et e
     * @return une liste de clé composé de la clé privée
     */
    @Override
    public Cles genererClePrivee() {
        Cles cles = new Cles();
        //Calcul de phi en trois étapes séparées
        NombreBinaire tempP = this.P.soustraction(new NombreBinaire(1));
        NombreBinaire tempQ = this.Q.soustraction(new NombreBinaire(1));
        //Pour obtenir phi = (P -1)(Q-1)
        this.phi = tempP.multiplication(tempQ);
        //Calcul de d en deux étapes, en NombreBinaire puis convertion en mot
        NombreBinaire pred = this.e.inverseModulaire(this.phi);
        MotBinaire d = new MotBinaire(pred.toString());
        //Puis ajout de la clé à la liste et renvoi de la liste
        CleBinaire k = new CleBinaire(d);
        cles.addCle("privee", k);
        return cles;
    }

    /**
     * Setter de P
     * @param P un NombreBinaire
     */
    public void setP(NombreBinaire P) {
        this.P = P;
    }

    /**
     * Setter de Q
     * @param Q un NombreBinaire
     */
    public void setQ(NombreBinaire Q) {
        this.Q = Q;
    }

    /**
     * Setter de phi
     * @param phi un NombreBinaire
     */
    public void setPhi(NombreBinaire phi) {
        this.phi = phi;
    }

    /**
     * Setter de e
     * @param e un NombreBinaire
     */
    public void setE(NombreBinaire e) {
        this.e = e;
    }
}
