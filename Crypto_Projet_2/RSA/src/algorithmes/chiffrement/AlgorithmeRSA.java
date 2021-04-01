package algorithmes.chiffrement;

import static algorithmes.chiffrement.RSA.ParametresRSA.getTailleCle;
import static algorithmes.chiffrement.RSA.ParametresRSA.getTailleMorceau;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.Cle;
import donnees.cles.Cles;
import donnees.messages.Message;
import donnees.messages.MessageBinaire;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;
import java.util.ArrayList;

public class AlgorithmeRSA implements Algorithme{

    
    @Override
    public String getNom() {
        return "RSA";
    }
    
    /**
     * Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
     * @param morceau
     * @param clesPublique
     * @return
     * @throws ExceptionConversionImpossible 
     */
    public MotBinaire chiffrerMorceau(MotBinaire morceau, Cles clesPublique) throws ExceptionConversionImpossible {
       //On récupère les 2 parties de la clé RSA
       Cle N = clesPublique.getCle("cleRSA_N");
       Cle e = clesPublique.getCle("cleRSA_e");
       
       //On convertit les clés en NombreBinaire
       MotBinaire eBis = e.asMotBinaire();
       NombreBinaire eNb = new NombreBinaire(eBis.getBitSet());
       
       MotBinaire NBis = N.asMotBinaire();
       NombreBinaire NNb = new NombreBinaire(NBis.getBitSet());
       
       //On encode le morceau
       NombreBinaire n = new NombreBinaire(morceau.getBitSet());
       n = n.puissanceModulo(eNb, NNb);
       
       //On renvoie le morceau encodé
       MotBinaire res = new MotBinaire(n.asBitSet(), getTailleCle());
       return res;
    }
    
    /**
     * Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
     * @param morceau
     * @param clesPublique
     * @param clesPrivee
     * @return
     * @throws ExceptionConversionImpossible 
     */
    public MotBinaire dechiffrerMorceau(MotBinaire morceau, Cles clesPublique, Cles clesPrivee) throws ExceptionConversionImpossible {
       //On récupère les 2 clés dont on a besoin
       Cle N = clesPublique.getCle("cleRSA_N");
       Cle d = clesPrivee.getCle("cleRSA_d");
       
       //On convertit les clés en NombreBinaire
       MotBinaire dBis = d.asMotBinaire();
       NombreBinaire dNb = new NombreBinaire(dBis.getBitSet());
       
       MotBinaire NBis = N.asMotBinaire();
       NombreBinaire NNb = new NombreBinaire(NBis.getBitSet());
       
       //On déchiffre le morceau
       NombreBinaire n = new NombreBinaire(morceau.getBitSet());
       n = n.puissanceModulo(dNb, NNb);
       
       //On renvoie un MotBinaire de la taille d'un morceau
       MotBinaire res = new MotBinaire(n.asBitSet(), getTailleMorceau());
       return res;
    }

    /**
     * Chiffre un message entier
     * @param message
     * @param clesPubliques
     * @param clesPrivees
     * @return
     * @throws ExceptionCryptographie 
     */
    @Override
    public Message chiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
        //On convertit le Message en MotBinaire pour pouvoir le séparer en morceau
        MotBinaire mBase = message.asMotBinaire();
        MotBinaire m = new MotBinaire();
        
        //on créé un tableau qui récupère les différents morceaux
        ArrayList<MotBinaire> array = mBase.scinder(getTailleMorceau());
        
        //on récupère et on chiffre morceau par morceau en concaténant à chaque fois
        for (int i = array.size()-1; i>=0; i--){
            MotBinaire morceau = array.get(i);
            morceau = this.chiffrerMorceau(morceau, clesPubliques);
            m = m.concatenation(morceau);
        }
       
        //on renvoit le message chiffré
       return new MessageBinaire(m);
    }

    @Override
    public Message dechiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
       //On convertit le Message en MotBinaire pour pouvoir le séparer en morceau
        MotBinaire mBase = message.asMotBinaire();
        MotBinaire m = new MotBinaire();
        
        //on créé un tableau qui récupère les différents morceaux
        ArrayList<MotBinaire> array = mBase.scinder(getTailleCle());
        
        //on récupère et on déchiffre morceau par morceau en concaténant à chaque fois
        for (int i = array.size()-1; i>=0; i--){
            MotBinaire morceau = array.get(i);
            morceau = this.dechiffrerMorceau(morceau, clesPubliques, clesPrivees);
            m = m.concatenation(morceau);
        }
       
        //on renvoit le message déchiffré
       return new MessageBinaire(m);
    }

}
