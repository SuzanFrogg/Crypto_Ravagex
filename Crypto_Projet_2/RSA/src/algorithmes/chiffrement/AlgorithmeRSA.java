package algorithmes.chiffrement;

import static algorithmes.chiffrement.RSA.ParametresRSA.getTailleCle;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.Cle;
import donnees.cles.Cles;
import donnees.messages.Message;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;

public class AlgorithmeRSA implements Algorithme{

    
    @Override
    public String getNom() {
        return "RSA";
    }
    
    //ATTENTION NE MARCHE PAS
    //Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
    public MotBinaire chiffrerMorceau(MotBinaire morceau, Cles clesPublique) throws ExceptionConversionImpossible {
       //On initialise les 2 morceaux de la clé RSA grâce à clesPublique
       Cle N = clesPublique.getCle("cleRSA_N");
       Cle e = clesPublique.getCle("cleRSA_e");
       
       MotBinaire eBis = e.asMotBinaire();
       NombreBinaire eNb = new NombreBinaire(eBis.toString());
       
       MotBinaire NBis = N.asMotBinaire();
       NombreBinaire NNb = new NombreBinaire(NBis.toString());
       
       NombreBinaire n = new NombreBinaire(morceau.toString());
       n = n.puissanceModulo(eNb, NNb);
       
       MotBinaire res = new MotBinaire(n.asBitSet(), getTailleCle());
       return res;
    }
    
    //Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
    public MotBinaire dechiffrerMorceau(MotBinaire morceau, Cles clesPublique, Cles clesPrivee) throws ExceptionConversionImpossible {
       //TODO
       return null;
    }

    @Override
    public Message chiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
       //TODO
       return null;
    }

    @Override
    public Message dechiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
       //TODO
       return null;
    }

}
