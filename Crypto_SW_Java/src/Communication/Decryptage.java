/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Moteur.binaire.MotBinaire;
import Moteur.binaire.boxes.SBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Decryptage, dernière partie du projet
 * @author Manon
 * @author mathy
 */
public class Decryptage extends Client{
      
    
     /**
     * Decryptage phase2
     *MDP = AHSKGUENSKRUJEN
     */
    @Override
    protected void phase2()
    {
        String receive = "", send = "" ;
        
        send = "AHSKGUENSKRUJEN";

        do 
        {
            try {
                //Envoi du MDP
                sendMessage(send);

                //M : désigne le paquet de 64 bits du message à encodé.
                receive = getMessage();
                MotBinaire m = new MotBinaire(receive);
                System.out.println("m :"+m);
                //M1 : désigne les 32 premiers bits de ce paquet.
                //M2 : désigne les 32 derniers bits de ce paquet.
                ArrayList<MotBinaire> listM = m.scinder(32);
                MotBinaire m1 = listM.get(1);
                System.out.println("m1 :"+m1);

                MotBinaire m2 = listM.get(0);
                System.out.println("m2 :"+m2);
                //K : désigne le paquet de 64 bits de la clé utilisé pour encodé.
                receive = getMessage();
                MotBinaire k = new MotBinaire(receive);
                System.out.println("k :"+k);

                //K1 : désigne les 32 premiers bits de ce paquet.
                //K2 : désigne les 32 derniers bits de ce paquet.
                ArrayList<MotBinaire> listK = k.scinder(32);
                MotBinaire k1 = listK.get(1);
                System.out.println("k1 :"+k1);
                MotBinaire k2 = listK.get(0);     
                System.out.println("k2 :"+k2);
                
                //I1 : désigne les 32 premiers bits du message intermédiaire.
                //I2 : désigne les 32 derniers bits du message intermédiaire.
                MotBinaire i2 = m1.xor(k1);
                System.out.println("i2 :"+i2);
                MotBinaire i1 = m2.xor(this.algoDecryptage(i2));
                System.out.println("i1 :"+i1);

                //C1 : désigne les 32 premiers bits du message chiffré.
                //C2 : désigne les 32 derniers bits du message chiffré.
                MotBinaire c2 = i1.xor(k2);
                System.out.println("c2 :"+c2);
                MotBinaire c1 = i2.xor(this.algoDecryptage(c2));
                System.out.println("c1 :"+c1);

                //send recoit la clé finale à renvoyer
                send = c1.concatenation(c2).toString();


        } catch (IOException ex) {
            Logger.getLogger(Decryptage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } while(!receive.equals("END")) ;
    }
    
    /**
     * Decryptage phase 3 n'existe pas
     */
    @Override
    protected void phase3(){/*Cette partie n'a pas de phase n°3*/}

    private MotBinaire algoDecryptage(MotBinaire mot) throws IOException {
        //1. Soit M un message de 32 bits dont on veut calculer F(M).
        MotBinaire M = new MotBinaire(mot.getBitSet(),mot.getTaille());
        MotBinaire F = new MotBinaire();

        //2. Soit D les 8 premiers bits de M.
        MotBinaire D = M.scinder(8).get(3);
        System.out.println("d : "+D);
        //3. Soient Sbox et P une SBox et une permutation données.
        Permutation P = new Permutation();
        String currentFolder = System.getProperty("user.dir");
        SBox sBox = new SBox(currentFolder + "/src/Data/sbox.txt");

        //4. F(M) = SBox(P(D)) ⊗ M.
        System.out.println("D2 : "+D);
        MotBinaire imageD = P.permuterMot8(D);
        System.out.println("image D : "+imageD);
        F = sBox.appliquer(imageD).additionMod2p32(M);
        System.out.println("F : "+F);
        return F;
    }
    
}
