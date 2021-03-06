package Moteur.binaire.boxes;

import Moteur.binaire.LecteurFichierBinaire;
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
 * @author Manon
 * Box de substitution
 */
public class SBox implements IBox {

    private ArrayList<Long> tableau = new ArrayList<>();
    
    public SBox(String adresseFichier) throws IOException 
    {
        
        //Recuperer le contenu du fichier
        FileReader fr = null;
        try {
            //on récupère le fichier
            File sbox = new File(adresseFichier);
            
            //File reader
            fr = new FileReader(sbox);
            
            //Buffered Reader
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            
            String ligne = br.readLine();
            while(ligne  != null)
            {
                // ajoute la ligne au buffer
                sb.append(ligne); 
                ligne = br.readLine();
            }
            
            //mettre les string dans le tableau
            String sboxString = sb.toString();

            for(String str : sboxString.split(","))
            {
                String s = "";
                for(int i = 2; i < str.length(); i++) //on enlève "0x"
                {
                    s += str.charAt(i);
                } 
                this.tableau.add(Long.parseLong(s,16));
            }

            
            
            fr.close(); //fermer le fichier

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
    
    /**
     * Récupère l'image du MotBinaire en entrée dans la Sbox
     * @param entree
     * @return 
     */
    @Override
    public MotBinaire appliquer(MotBinaire entree) {
        int index = entree.asInteger();
        
        long resLong = this.tableau.get(index);
        MotBinaire res = new MotBinaire(resLong);
        
        return res;
    }
    
}
