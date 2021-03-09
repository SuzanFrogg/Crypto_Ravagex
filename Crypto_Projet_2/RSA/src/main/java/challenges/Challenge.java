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
     * constructeur
     * @param client 
     */
    public Challenge() 
    {
        try {
            this.client = new Client();
            System.out.println("-- Debut com --");
            communicate();
        } catch (IOException ex) {
            Logger.getLogger(Challenge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * méthode abstraite de comunication
     */
    public abstract void communicate();
    

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
