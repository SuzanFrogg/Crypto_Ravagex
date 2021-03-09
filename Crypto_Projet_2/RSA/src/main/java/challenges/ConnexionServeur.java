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
 * Communication de la connexion
 * @author Manon, Thibault
 */
public class ConnexionServeur extends Challenge{
    @Override
    public boolean communicate() throws IOException {
        //Premier message qui correspond à l'état de la réponse
        setMsgReceive(getClient().receiveMessage());
        if (getMsgReceive().equals("NOK")) return false;
        
        //Deuxième message avec l'information
        setMsgReceive(getClient().receiveMessage());

        //On vérifie si le défi est terminé
        boolean keepGoing = !getMsgReceive().equals("Defi valide") && !getMsgReceive().equals("Defi echoue!");
        if (keepGoing) {
            int chiffre = Integer.parseInt(getMsgReceive()) + 1;
            String msg = "" + chiffre;
            setMsgSend(msg);
            getClient().sendMessage(getMsgSend());
        }
        return keepGoing;
    }
}
