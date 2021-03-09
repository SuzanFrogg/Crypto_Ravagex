package coucheReseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe client permettant d'effectuer une connexion 
 * @author simonetma
 */
public class Client {
    private Socket socket; 
    private PrintWriter pw;
    private BufferedReader bufr;
    
    /**
     * Constructeur du Client
     * @throws IOException 
     */
    public Client() throws IOException {
        this.socket = new Socket("127.0.0.1", 1234);
        this.bufr = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.pw = new PrintWriter(this.socket.getOutputStream(), true);
    }
    
    /**
     * Envoie un message à l'exe
     * @param message 
     */
    public void sendMessage(String message) {
        this.pw.println(message);
        System.out.println("> "+message);
    }
      
    /**
     * Renvoie true si l'IA doit continuer
     * @return un message venant de l'exe
     * @throws IOException 
     */
    public String receiveMessage() throws IOException {
        String messageRecu = this.bufr.readLine();
        System.out.println("< "+messageRecu);
        
        return messageRecu;
    }
    
    /**
     * Fermer tous les flux ouverts
     * @throws IOException 
     */
    public void end() throws IOException {
        this.socket.close();
        this.bufr.close();
        this.pw.close();
    }
    
}
