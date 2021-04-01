/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import algorithmes.chiffrement.generateurdecles.GenerateurDeClesRSA;
import donnees.NombreBinaire;
import donnees.cles.Cle;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe permettant la validation du challenge n°24 : Générer la clé publique
 * @author mathy
 */
public class GenererClePublique extends Challenge {

    /**
     * Pour valider le challenge Générer la clé publique , il faut renvoyer
     * 
     * @return un nombrebinaire premier
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        return null;
    }
    
    @Override
    public final void executer() throws ExceptionCryptographie {
        boolean keepGoing = true; 
        try {

            do {
                //Première reception contenant le nom du défi
                //Puis Reception du message validant ou non le calcul envoyé
                this.setMsgReceive(this.getClient().receiveMessage());
                //Si le calcul n'est pas validé, on sort de la boucle
                if(this.getMsgReceive().startsWith("NOK")) {
                    keepGoing = false;
                    continue;
                }
              /*//Pour certain challenge envoyant deux paramètre de calcul
                //La condition est passé sans soucis
                //Ou Reception du message de fin de défi
                this.setMsgReceive(this.getClient().receiveMessage());
              */
                //Pour ce challenge, envoyer les 5 résultats différents
                GenerateurDeClesRSA generator = new GenerateurDeClesRSA();
                //Execution et renvoi du Nombre décalé
                Cle d = generator.genererClePublique().getCle("publique");
                this.setMsgSend(generator.getP().toString());
                this.getClient().sendMessage(this.getMsgSend());
                this.setMsgSend(generator.getQ().toString());
                this.getClient().sendMessage(this.getMsgSend());
                this.setMsgSend(generator.getN().toString());
                this.getClient().sendMessage(this.getMsgSend());
                this.setMsgSend(generator.getPhi().toString());
                this.getClient().sendMessage(this.getMsgSend());
                this.setMsgSend(generator.getE().toString());
                this.getClient().sendMessage(this.getMsgSend());
                //Si le défi est terminé, qu'il soit réussi ou non, il faut sortir de la boucle
                if(this.getMsgReceive().startsWith("Defi valide") || this.getMsgReceive().startsWith("Defi echoue!") ) {
                    keepGoing = false;
                    continue;
                }    

            } while(keepGoing);            
        } catch (IOException ex) {
            Logger.getLogger(Challenge.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
