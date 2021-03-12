/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import coucheReseau.client.Client;
import exceptions.ExceptionCryptographie;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocoles.Protocole;

/**
 *
 * @author Manon
 */
public abstract class Challenge implements Protocole
{
    private Client client;
    private String msgReceive;
    private String msgSend;
    
    /**
     * Constructeur
     * @param client 
     */
    public Challenge() 
    {
        try {
            this.client = new Client();
            this.msgReceive = "";
            this.msgSend = "";
                    
            System.out.println("-- Debut transmission --");
            executer();
            this.client.end();
            
        } catch (Exception ex) {
            Logger.getLogger(ConnexionServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Boucle de communication
     */
    public void boucleCommunication() throws IOException {
        boolean keepGoing = true;
        
    }
    
    /**
     * Méthode abstraite de communication
     * @return true si la communication doit continuer
     */
    public abstract String communicate() throws IOException;
    
    /**
     * Fonction executer permettant de tester le challenge
     * @throws ExceptionCryptographie 
     */
        @Override
    public void executer() throws ExceptionCryptographie {
        boolean keepGoing = true; 
        try {
            do {
                //Première reception contenant le nom du défi
                //Puis Reception du message validant ou non le calcul envoyé
                this.setMsgReceive(this.client.receiveMessage());
                //Si le calcul n'est pas validé, on sort de la boucle
                if(this.getMsgReceive().startsWith("NOK")) {
                    keepGoing = false;
                    continue;
                }
                //Pour certain challenge envoyant deux paramètre de calcul
                //La condition est passé sans soucis
                //Ou Reception du message de fin de défi
                this.setMsgReceive(this.client.receiveMessage());
                //Si le défi est terminé, qu'il soit réussi ou non, il faut sortir de la boucle
                if(this.getMsgReceive().startsWith("Defi valide") || this.getMsgReceive().startsWith("Defi echoue!") ) {
                    keepGoing = false;
                    continue;
                }    
                //Pour chaque challenge, envoyer le résultat du calcul effectué
                this.setMsgSend(this.communicate());
                this.client.sendMessage(this.getMsgSend());
            } while(keepGoing);            
        } catch (IOException ex) {
            Logger.getLogger(Challenge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * Getter
     * @return 
     */
    protected Client getClient() {
        return client;
    }

    /**
     * Getter
     * @return 
     */
    protected String getMsgReceive() {
        return msgReceive;
    }

    /**
     * Setter
     * @param msgReceive 
     */
    protected void setMsgReceive(String msgReceive) {
        this.msgReceive = msgReceive;
    }

    /**
     * Getter
     * @return 
     */
    protected String getMsgSend() {
        return msgSend;
    }

    /**
     * Setter
     * @param msgSend 
     */
    protected void setMsgSend(String msgSend) {
        this.msgSend = msgSend;
    }


    
    
    
    
}
