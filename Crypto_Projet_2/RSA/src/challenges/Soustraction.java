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
public class Soustraction extends Challenge {
    
    
    public Soustraction() {
    }
    @Override
        
        public String communicate() throws IOException {
        
        MotBinaire mot1 = new MotBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        MotBinaire mot2 = new MotBinaire(getMsgReceive());

        MotBinaire mot3 = mot1.soustracionMod2p32(mot2);
        
        String res =  mot3.toString();
        
        return res;
    }
    
}
