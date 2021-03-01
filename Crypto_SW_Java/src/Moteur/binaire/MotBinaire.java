package Moteur.binaire;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Représentation d'un mot binaire
 */
public class MotBinaire {

    private BitSet listeBits;                   //Liste des bits
    private int taille;                         //Nombre de bits
    
    //Constructeurs standard
    public MotBinaire() {
        this.listeBits = new BitSet();
        this.taille = 0;
    }
	
    //Constructeur par paramètres avec clonage du bitset
    public MotBinaire(BitSet listeBits,int taille) {
        this.listeBits = new BitSet();
        this.taille = taille;
        for(int i=0;i<this.taille;i++) {
            this.listeBits.set(i,listeBits.get(i));
        } 
    }
	
    /**
     * Constructeur à partir d'un long
     * @param valeur long
     */
    public MotBinaire(long valeur) {
        this.listeBits = new BitSet();
        
        int pos = 0;
        while(valeur != 0) //tant que le long n'a pas été parcouru
        {
            int binaireInt = (int)(valeur % 2);
            
            boolean bin = binaireInt == 1; //true si 1 et false si 0
            this.listeBits.set(pos, bin);
            
            valeur /= 2;
            pos += 1; //incrémentation de la position
        }
        
        this.taille = pos;
    }
    
    /**
     *  Constructeur à partir d'un byte
     * @param b un byte
     */
    public MotBinaire(byte b) {
        this.taille = 8;
        this.listeBits = new BitSet();
        //Création d'un array byte avec le byte à l'intérieur
        byte bits[] = new byte[1];
        bits[0] = b;
        //Changement de la listeBits avec la value de bits
        this.listeBits = BitSet.valueOf(bits);
    }
    
    //Constructeur à partir d'un caractère (UTF-8)
    public MotBinaire(char c) {
        this.listeBits = new BitSet();
        this.taille = 8; //UTF8
        int code = (int)c;
        
        //On regarde pour la taille
        for(int i=0;i<this.taille;i++) {
            //On regarde si le code est divisible par 2 : donc si on peut mettre 1 pour le bit actuel
            int binaireInt = (int)(code % 2);
            
            boolean bin = binaireInt == 1;
            this.listeBits.set(i, bin);
            
            code /= 2;
        }
    }
    
    //Constructeur à partir d'une succession de 1 et de 0 
    public MotBinaire(String S) {
        this();
        this.taille = S.length();
        for(int i=0;i<this.taille;i++) {
            this.listeBits.set(this.taille-i-1,S.charAt(i)=='1');
        }
    }
    
    /**
     * Getter de la liste des bits
     * @return le BitSet
     */
    public BitSet getBitSet() {
        return this.listeBits;
    }
	
	
    /**
     * Getter de la taille
     * @return la taille
     */
    public int getTaille() {
        return this.taille;
    }
     
    
    /**
     * Convertion en entier non signé
     * @return un entier
     */
    public int asInteger() {
        int res = 0;
        
        for(int i = 0; i < this.taille ; i++) //on parcours le byte
        {
            boolean bit = this.listeBits.get(i);
            int bitInteger = bit ? 1 : 0; //si le bit est true alors le int est == 1
            
            res += (int) bitInteger * Math.pow(2,i);
        }
        return res;
    }
    /**
     * Interprète le MotBinaire comme une succession de caractère encodé chacun sur 8bits (UTF-8)
     * @return une chaine de caractères
     */
    public String asString() {
        String res = "";
       
        
        int tailleChar = 8;
        BitSet shruckBit = new BitSet();
        int charAsInt = 0;
        
        while(this.taille >= 8)
        {
            for (int i=0; i < tailleChar; i++)
            {
               shruckBit.set(i, this.listeBits.get(i)); //on recréer un bit rétréci
               MotBinaire shruckMotBinaire = new MotBinaire(shruckBit, 8); //qu'on met dans un mot binaire pour utiliser as Integer
               charAsInt = shruckMotBinaire.asInteger();

               res += (char) charAsInt;

            }
        }
        
        
        return res;
    }
    
    //Affichage en binaire (i.e : 6 -> "110")
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.taille;i++) {
            if(this.listeBits.get(this.taille-i-1)) {
                res = res+"1";
            }
            else {
                res = res + "0";
            }
        }
        return res;
    }
    
    /**
     * Renvoie le résultat de this XOR mot2
     * @param mot2 2nd mot binaire
     * @return le résultat du xor
     */
    public MotBinaire xor(MotBinaire mot2) {
        BitSet bits = mot2.getBitSet();
        bits.xor(bits);
        MotBinaire mbRes = new MotBinaire(bits,mot2.taille);
        return mbRes;
    }
    
    /**
     * Renvoie le résultat de this + mot2 [2^32]
     * @param mot2 2nd mot binaire
     * @return le résultat de l'addition
     */
     public MotBinaire additionMod2p32(MotBinaire mot2) {
        int retenue = 0;
        boolean bool = false;
        BitSet bR = new BitSet();
        BitSet bM1 = this.getBitSet();
        BitSet bM2 = mot2.getBitSet();
         
        for(int i =0; i < 31; i++){
            int bM1INT = bM1.get(i) ? 1 : 0;
            int bM2INT = bM2.get(i) ? 1 : 0;
            int calc = (retenue + bM1INT + bM2INT) % 2;
             
            if(calc == 1){
                bool = true;
            }
            
            bR.set(i, bool);
        }
        
        MotBinaire mbRes = new MotBinaire(bR,mot2.taille);     
        return mbRes;
     }
    
     /**
      * Scinde le mot binaire en une liste de mot binaire de taille donnée. 
      * @param tailleMorceau taille des morceaux
      * @return la liste des morceaux
      */
     public ArrayList<MotBinaire> scinder(int tailleMorceau) {
        //TODO
        return null;
    }
     
     /**
      * Concaténation de deux mots binaires
      * @param mot le deuxième mot
      * @return le résultat de la concaténation
      */
     public MotBinaire concatenation(MotBinaire mot) {
         //TODO
	return null;
     }
     
}