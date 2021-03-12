/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import coucheReseau.client.Client;
import donnees.MotBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°7 : Est Pair
 * @author mathy
 */
public class EstEgal extends Challenge {
    
    private Client client;
    
    public EstEgal() {
        this.client = this.getClient();
    }
    @Override
        
        public String communicate() throws IOException {
        String res = "false";
        
        MotBinaire mot1 = new MotBinaire(getMsgReceive());
        setMsgReceive(client.receiveMessage());
        MotBinaire mot2 = new MotBinaire(getMsgReceive());
        
        int nb1 = mot1.asInteger();
        int nb2 = mot2.asInteger();
        
        if (nb1 == nb2)
        {
            res = "true";
        }
        return res;
    }
    
}
