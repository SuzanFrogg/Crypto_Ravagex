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
/**
 *
 * @author thibault 
 */
public class Client {
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
    
    public void boucleDeDiscussion(String mdp) throws IOException {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;
        
        System.out.println("−− Debut de la transmission −−") ;
       
        
        //envoie mdp
        messageAEnvoyer = mdp;
        sendMessage(messageAEnvoyer);

        do {

            //Reception du message du serveur
            messageRecu = getMessage();
            
            //Envoi du message de réponse
            messageAEnvoyer = messageRecu;
            sendMessage(messageAEnvoyer);

            
        } while(!messageRecu.equals("END")) ;
        

        System.out.println("−− Fin de la transmission −−") ;
    }
    
    /**
     * envoie un message au jar
     * @param message 
     */
    private void sendMessage(String message)
    {
        fluxSortant.println(message);
        System.out.println("> "+message);
    }
    
    private String getMessage() throws IOException
    {
        String messageRecu = this.fluxEntrant.readLine();
        System.out.println("< "+ messageRecu);
        
        return messageRecu;
    }
}
