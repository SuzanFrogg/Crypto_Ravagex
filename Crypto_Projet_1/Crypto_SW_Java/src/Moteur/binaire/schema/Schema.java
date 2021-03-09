package Moteur.binaire.schema;

import java.util.ArrayList;
import Moteur.binaire.MotBinaire;
import Moteur.binaire.boxes.PBox;
import Moteur.binaire.boxes.SBox;

/**
 * Classe représentant un schéma de Feistel
 */
public class Schema {
    private SBox sbox;
    private PBox pbox;
    
    public Schema() {
		//TODO
    }
    
    /**
     * Fonction non-linéaire F 
     * @param M Mot binaire dont on veut calculer l'image
	 * @return l'image de M par F
     */
    private MotBinaire F(MotBinaire M) {
        //TODO
		return null;
    }
    
	/**
     * Décryptage d'un bloc M du message (64 bits) par un bloc K de la clé (64 bits) 
     * @param M le bloc du message à décrypter
     * @param K le bloc du clé pour décrypter
	 * @return le bloc décrypté
     */
    public MotBinaire decrypter(MotBinaire M, MotBinaire K) {
        //TODO
        return null;
    }
    
}
