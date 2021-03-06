/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thibault 
 * @author Manon
 * @author mathy
 */
public abstract class Client {
    private Socket socket;
    private BufferedReader fluxEntrant;
    private PrintWriter fluxSortant;
    
    public void connexion() throws IOException {
        this.socket = new Socket("127.0.0.1", 1977);
    }
    
    public void creationFlux() throws IOException {
        this.fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.fluxSortant = new PrintWriter(this.socket.getOutputStream(), true);
    }
    
    /**
     * classe abstraire de communication
     * @throws IOException 
     */
    public void boucleDeDiscussion(int phase) throws IOException
    {
        System.out.println("−− Debut de la transmission −−") ;
        
        switch(phase)
        {
            case 2 : phase2();
                break;
            case 3: phase3();
                break;
            default : {
            try {
                throw new Exception("Mauvais numéro de phase");
            } catch (Exception ex) {
                Logger.getLogger(Conversion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        
        System.out.println("−− Fin de la transmission −−") ;
    }
    
    /**
     * envoie un message au jar
     * @param message 
     */
    protected void sendMessage(String message)
    {
        fluxSortant.println(message);
        System.out.println("> "+message);
    }
    
    protected String getMessage() throws IOException
    {
        String messageRecu = this.fluxEntrant.readLine();
        System.out.println("< "+ messageRecu);
        
        return messageRecu;
    }
    
    protected abstract void phase2();
    protected abstract void phase3();
    
    
   
}
