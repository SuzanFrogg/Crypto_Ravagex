package algorithmes.chiffrement.generateurdecles;

import algorithmes.chiffrement.RSA.ParametresRSA;
import static algorithmes.chiffrement.RSA.ParametresRSA.getTailleDemiCle;
import algorithmes.chiffrement.RSA.RabinMiller;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import static donnees.NombreBinaire.random;
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
        //Preparation du trousseau de cles
        Cles cles = new Cles();
        
        //Création des mins et max pour les randoms suivant
        String strmax = "1",strmin ="1";
        for(int i =0;i<getTailleDemiCle();i++) {
            strmax+="1";
            strmin+="0";
        } 
       NombreBinaire max = new NombreBinaire(strmax);
       NombreBinaire min = new NombreBinaire(strmin);

        do {
            NombreBinaire preP = random(max,min);
            NombreBinaire preQ = random(max,min);
            P = RabinMiller.nombrePremier(preP);
            Q = RabinMiller.nombrePremier(preQ);
        }while(P.estEgal(Q));
        this.N = this.P.multiplication(this.Q);
        
        //NombreBinaire 1 utile pour les calculs
     	NombreBinaire One = new NombreBinaire(1);
        NombreBinaire p1 = this.P.soustraction(One);
        NombreBinaire d1 = this.Q.soustraction(One);
        //Phi = (P-1)(Q-1)
        this.phi = p1.multiplication(d1);
        //E est un nombre premier de phi
        this.e = RabinMiller.nombrePremier(this.phi);
        
        
        //Création des deux clés et ajout au trousseau
        MotBinaire motN = new MotBinaire();
        MotBinaire motE = new MotBinaire();
        CleBinaire kN = new CleBinaire(motN);
        CleBinaire ke = new CleBinaire(motE);

        cles.addCle("cleRSA_N",kN);
        cles.addCle("cleRSA_e",ke);
        return cles;
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
        CleBinaire cleN = new CleBinaire(motD);
        cles.addCle("privee", cleN);
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

    public NombreBinaire getP() {
        return P;
    }

    public NombreBinaire getQ() {
        return Q;
    }

    public NombreBinaire getN() {
        return N;
    }

    public NombreBinaire getPhi() {
        return phi;
    }

    public NombreBinaire getE() {
        return e;
    }
    
    
}
