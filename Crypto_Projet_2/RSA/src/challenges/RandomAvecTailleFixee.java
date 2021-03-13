/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°11 : Random avec taille fixée
 * @author mathy
 */
public class RandomAvecTailleFixee extends Challenge{

    /**
     * Pour valider le challenge Random avec taille fixe : il faut renvoyer un nombre binaire
     * d'une taille inférieur ou égale à celle donnée
     * @return un nombre binaire d'une taille aléatoire inférieure au nombre donné
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Réception de la taille 
        int taille = Integer.parseInt(getMsgReceive());
        
        
        return NombreBinaire.randomAvecTailleMax(taille).toString();
    }
    
}
