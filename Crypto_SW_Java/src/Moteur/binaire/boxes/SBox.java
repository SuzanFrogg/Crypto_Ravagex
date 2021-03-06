package Moteur.binaire.boxes;

import java.util.ArrayList;
import Moteur.binaire.MotBinaire;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Box de substitution
 */
public class SBox implements IBox {

    private ArrayList<Long> tableau;
    
    public SBox(String adresseFichier) 
    {
        FileReader fr = null;
        try {
            //on récupère le fichier
            File sbox = new File("../../../Data/sbox.txt");
            
            //File reader
            fr = new FileReader(sbox);
            
            //Buffered Reader
            BufferedReader br = new BufferedReader(fr);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SBox.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(SBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }
    
    @Override
    public MotBinaire appliquer(MotBinaire entree) {
        return null;
    }
    
}
