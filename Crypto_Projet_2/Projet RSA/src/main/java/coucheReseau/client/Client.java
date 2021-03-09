package coucheReseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket; 
    private PrintWriter pw;
    private BufferedReader bufr;
    
    public Client() throws IOException {
        //TODO
    }
    
    public void sendMessage(String message) {
        //TODO
    }
        
    //Renvoie true si l'IA doit continuer
    public String receiveMessage() throws IOException {
        //TODO
        return null;
    }
    
    public void end() throws IOException {
        //TODO
    }
    
}
