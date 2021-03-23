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
        //Preparation du trousseau de cles
        Cles cles = new Cles();
        //NombreBinaire 1 utile pour les calculs
     	NombreBinaire One = new NombreBinaire(1);
     	/*NombreBinaire PQ = this.P.multiplication(Q);
     	NombreBinaire PminusQ = this.P.soustraction(Q);
     	this.phi = (PQ.soustraction(PminusQ).addition(One));*/
        NombreBinaire p1 = this.P.soustraction(One);
        NombreBinaire d1 = this.Q.soustraction(One);
        this.phi = p1.multiplication(d1);
     	NombreBinaire nbD = this.e.inverseModulaire(this.phi);

     	MotBinaire motD = new MotBinaire(nbD.asBitSet(),ParametresRSA.getTailleCle());
        CleBinaire k = new CleBinaire(motD);
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
