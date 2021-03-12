/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import coucheReseau.client.Client;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°7 : Est Pair
 * @author mathy
 */
public class EstPair extends Challenge {
    
    private Client client;
    
    public EstPair() {
        this.client = this.getClient();
    }
    @Override
    public String communicate() throws IOException {
        
        return "";
    }
    
}
