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
    
    public void boucleDeDiscussion() throws IOException {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;
        System.out.println("−− Debut de la transmission −−") ;
        do {
            //Reception du message du serveur
            messageRecu = this.fluxEntrant.readLine();
            System.out.println("< "+ messageRecu);
            //Envoi du message de réponse
            switch (messageRecu) {
                case "Bonjour":
                    messageAEnvoyer = "Bonjour";
                    break;
                case "Ca va ?":
                    messageAEnvoyer = "Oui";
                    break;
                case "FIN":
                    messageAEnvoyer = "FIN";
                    break;
            }
            fluxSortant.println(messageAEnvoyer);
            System.out.println(">"+messageAEnvoyer);
        } while(!messageRecu.equals("FIN")) ;
        System.out.println("−− Fin de la transmission −−") ;
    }
}
