/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Moteur.binaire.EnregistreurFichierBinaire;
import Moteur.binaire.LecteurFichierBinaire;
import Moteur.binaire.MotBinaire;
import Moteur.binaire.boxes.SBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Décryptage permettant la communication avec la partie Décryptage du jar
 * Et permet d'obtenir le Plan final
 * @author Manon
 * @author mathy
 */
public class Decryptage extends Client{
      
    
     /**
     * Decryptage phase 2
     *MDP = AHSKGUENSKRUJEN
     */
    @Override
    protected void phase2() {
        
        String receive = "", send = "" ;
        
        send = "AHSKGUENSKRUJEN";

        do {
            
            try {
                //this.connexion();
                //this.creationFlux();
                //Envoi du MDP puis des clés finales
                sendMessage(send);

                //M : désigne le paquet de 64 bits du message à encodé.
                receive = getMessage();
                MotBinaire m = new MotBinaire(receive);
                
                //K : désigne le paquet de 64 bits de la clé utilisé pour encodé.
                receive = getMessage();
                MotBinaire k = new MotBinaire(receive);
                
                //send recoit la clé finale à renvoyer
                send = algoGeneral(m, k).toString();


        } catch (IOException ex) {
            Logger.getLogger(Decryptage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } while(!receive.equals("END")) ;
    }
    
    /**
     * Fonction utilisée afin de faire le décryptage du plan final
     */
    @Override
    protected void phase3(){
        try {
            //Création des lecteurs et de l'enregistreur
            String currentFolder = System.getProperty("user.dir");
            LecteurFichierBinaire lPlan = new LecteurFichierBinaire(currentFolder + "/src/Data/PlanCrypte");
            LecteurFichierBinaire lCle = new LecteurFichierBinaire(currentFolder + "/src/Data/cle");
            EnregistreurFichierBinaire save = new EnregistreurFichierBinaire(currentFolder+"/src/Data/Plan.jpg");
            
            //Boucle sur le nombre de ligne des deux fichiers
            for(int ligne = 0;ligne<68718;ligne++){
                byte[] plan = lPlan.nextBytes();
                byte[] cle = lCle.nextBytes();
                MotBinaire M = new MotBinaire(),K = new MotBinaire();
                //Concatenations de tous les bytes afin de former le message et la clé de 64 bits chacun
                for(int i = 7;i >= 0;i--){
                    M= M.concatenation(new MotBinaire(plan[i]));
                    K= K.concatenation(new MotBinaire(cle[i]));
                }
                //On effectue l'algo avec ce message et cette clé 
                //Puis on l'enregistre dans le fichier Plan.jpg
                save.enregistrer(this.algoGeneral(M, K));
            }
            
            //Fermeture des fluxs
            lPlan.close();
            lCle.close();
            save.close();
        } catch (IOException ex) {
            Logger.getLogger(Decryptage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Fonction F de l'algorithme de décryptage
     * @param mot un message de 32 bits
     * @return l'image du mot par la fonction F
     * @throws IOException 
     */
    private MotBinaire fonctionF(MotBinaire mot) throws IOException {
        //1. Soit M un message de 32 bits dont on veut calculer F(M).
        MotBinaire M = new MotBinaire(mot.getBitSet(),mot.getTaille());
        MotBinaire F = new MotBinaire();

        //2. Soit D les 8 premiers bits de M.
        MotBinaire D = M.scinder(8).get(3);
        //3. Soient Sbox et P une SBox et une permutation données.
        Permutation P = new Permutation();
        String currentFolder = System.getProperty("user.dir");
        SBox sBox = new SBox(currentFolder + "/src/Data/sbox.txt");

        //4. F(M) = SBox(P(D)) ⊗ M.
        MotBinaire imageD = P.permuterMot8(D);
        F = sBox.appliquer(imageD).additionMod2p32(M);
        return F;
    }
    
    /**
     * Algorithme de type schéma de Feistel
     * @param m mot encodé
     * @param k clé utilisée 
     * @return le mot déchiffré
     * @throws IOException 
     */
    private MotBinaire algoGeneral(MotBinaire m,MotBinaire k) throws IOException {
        //M1 : désigne les 32 premiers bits du paquet M.
        //M2 : désigne les 32 derniers bits du paquet M.
        ArrayList<MotBinaire> listM = m.scinder(32);
        MotBinaire m1 = listM.get(1);
        MotBinaire m2 = listM.get(0);
        
        //K1 : désigne les 32 premiers bits du paquet K.
        //K2 : désigne les 32 derniers bits du paquet K.
        ArrayList<MotBinaire> listK = k.scinder(32);
        MotBinaire k1 = listK.get(1);
        MotBinaire k2 = listK.get(0);     

        //I1 : désigne les 32 premiers bits du message intermédiaire.
        //I2 : désigne les 32 derniers bits du message intermédiaire.
        MotBinaire i2 = m1.xor(k1);
        MotBinaire i1 = m2.xor(this.fonctionF(i2));

        //C1 : désigne les 32 premiers bits du message chiffré.
        //C2 : désigne les 32 derniers bits du message chiffré.
        MotBinaire c2 = i1.xor(k2);
        MotBinaire c1 = i2.xor(this.fonctionF(c2));
        
        //Renvoie la clé en un MotBinaire
        return c1.concatenation(c2);
    }
    
}
