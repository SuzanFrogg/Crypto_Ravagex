/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import coucheReseau.client.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manon
 */
public abstract class Challenge 
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
            boucleCommunication();
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
        do {
            keepGoing = this.communicate();
        } while(keepGoing);
    }
    
    /**
     * Méthode abstraite de communication
     * @return true si la communication doit continuer
     */
    public abstract boolean communicate() throws IOException;
    

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
