package Moteur.binaire;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;

/**
 * Enregistreur de fichier binaire
 */
public class EnregistreurFichierBinaire {

    private FileOutputStream outputStream ;             //flux de sortie
    
    /**
     * Constructeur
     * @param adresse adresse du fichier de destination
     * @throws FileNotFoundException 
     */
    public EnregistreurFichierBinaire(String adresse) throws FileNotFoundException {
        this.outputStream = new FileOutputStream(adresse);
    }
    
    /**
     * Ajoute un mot binaire au fichier de destination
     * @param mot le mot à ajouter
     * @throws IOException 
     */
    public void enregistrer(MotBinaire mot) throws IOException {
        byte[] byteArray = mot.getBitSet().toByteArray();
        byte[] byteArrayNormalise = new byte[(mot.getTaille()+7)/8];
        for(int i=0;i<(mot.getTaille()+7)/8;i++) {
            byteArrayNormalise[i]=0;
            if(i<byteArray.length) {
                byteArrayNormalise[i] = byteArray[i];
            }
        }
        outputStream.write(byteArrayNormalise);
        
    }
    
    /**
     * Ajoute un byte[] au fichier de destination
     * @param b le byte[] à ajouter
     * @throws IOException 
     */
    public void enregistrer(byte[] b) throws IOException {
        outputStream.write(b);
        
    }
    
    /**
     * Fermeture du flux
     * @throws IOException 
     */
    public void close() throws IOException {
        this.outputStream.close();
    }
    
    
}
