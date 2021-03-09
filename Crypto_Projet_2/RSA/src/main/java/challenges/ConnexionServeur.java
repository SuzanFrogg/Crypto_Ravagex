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
public class ConnexionServeur extends Challenge{


    @Override
    public void communicate() {
        
        do {
            try {
                
                setMsgReceive(getClient().receiveMessage());
                setMsgReceive(getClient().receiveMessage());
                
                
                
                if(!"Defi valide".equals(getMsgReceive()))
                {
                    
                        
                    int chiffre = Integer.parseInt(getMsgReceive()) + 1;
                    String msg = "" + chiffre;
                    setMsgSend(msg);
                    getClient().sendMessage(getMsgSend());
                }
               
                

                
                
            } catch (IOException ex) {
                Logger.getLogger(ConnexionServeur.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while(!getMsgReceive().equals("FIN"));
        
        try {
            getClient().end();
        } catch (IOException ex) {
            Logger.getLogger(ConnexionServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
