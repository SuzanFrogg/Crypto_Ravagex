package Moteur.binaire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Lecteur de fichier binaire
 */
public class LecteurFichierBinaire {

    private InputStream inputStream;            //Le flux d'entrée
    
    /**
     * Constructeur 
     * @param adresse adresse du fichier à lire
     * @throws FileNotFoundException 
     */
    public LecteurFichierBinaire(String adresse) throws FileNotFoundException {
        this.inputStream = new FileInputStream(adresse);
    }
    
	//
    /**
     * Renvoie le tableau de 8 bytes (soit 64 bits) suivant de le fichier. 
     * Il faut donc créer un mot binaire avec chacun des 8 bytes puis les concaténer. Attention au sens de concaténation
     * @return un tableau de 8 byte
     * @throws IOException 
     */
    public byte[] nextBytes() throws IOException {
        byte[] buffer = new byte[8];
        if(inputStream.read(buffer) == -1) buffer = null;
        return buffer;
    }
    
    /**
     * Fermeture du flux de lecture
     * @throws IOException 
     */
    public void close() throws IOException {
        this.inputStream.close();
    }
    
}
