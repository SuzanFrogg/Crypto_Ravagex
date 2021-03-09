package Moteur.binaire;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

/**
 * Représentation d'un mot binaire
 * @author Manon
 * @author mathy
 * @author Thibault
 * @author Iris
 * @author Albane
 * @author Antonia
 */
public class MotBinaire {

    private BitSet listeBits;                   //Liste des bits
    private int taille;                         //Nombre de bits
    
    /**
     * Constructeur standard
     */
    public MotBinaire() {
        this.listeBits = new BitSet();
        this.taille = 0;
    }
    /**
     * Constructeur par paramètres avec clonage du bitset
     */
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
        this.taille = 32;   
        long[] longs = new long[1];
        longs[0] = valeur;
        this.listeBits = BitSet.valueOf(longs);
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
    
    /**
     * Constructeur à partir d'un caractère (UTF-8)
     * @param c un caractère
     */
    public MotBinaire(char c) {
        this.listeBits = new BitSet();
        this.taille = 8; //UTF8
        int code = (int)c;
        
        //On regarde pour la taille
        for(int i=0;i<this.taille;i++) {
            //On regarde si le code est divisible par 2 : donc si on peut
                    //mettre 1 pour le bit actuel
            int binaireInt = (int)(code % 2);
            
            boolean bin = binaireInt == 1;
            this.listeBits.set(i, bin);
            
            code /= 2;
        }
    }
    
    /**
     * Constructeur à partir d'une succession de 1 et de 0
     * @param S une suite de 1 et de 0
     */ 
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
        
        //on parcours le byte
        for(int i = 0; i < this.taille ; i++) {
            boolean bit = this.listeBits.get(i);
            //si le bit est true alors le int est == 1
            int bitInteger = bit ? 1 : 0; 
            
            res += (int) bitInteger * Math.pow(2,i);
        }
        return res;
    }
    /**
     * Interprète le MotBinaire comme une succession de 
     * caractère encodé chacun sur 8bits (UTF-8)
     * @return une chaine de caractères
     */
    public String asString() {
        String res = "";
        //Le mot est utilisé d'octet en octet
        for(int ite = this.taille-8;ite>=0;ite-=8) {
            res += (char)Integer.parseInt( new MotBinaire(this.listeBits.get(ite,ite+8),8).toString(),2);
        }   
        return res;
    }
    
    /**
     * Affichage en binaire (i.e : 6 -> "110")
     * @return Un string renvoyant sous forme de suite de 1 et de 0 le mot
     */
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.taille;i++) {
            if(this.listeBits.get(this.taille-i-1)) {
                res = res+"1";
            } else {
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
        MotBinaire mot1 = new MotBinaire(this.listeBits,this.taille);
        mot1.getBitSet().xor(mot2.getBitSet());
        return mot1;
    }
    
    /**
     * Renvoie le résultat de this + mot2 [2^32]
     * @param mot2 2nd mot binaire
     * @return le résultat de l'addition
     */
     public MotBinaire additionMod2p32(MotBinaire mot2) {
        int retenue = 0;
        
        
        BitSet bR = new BitSet();
        BitSet bM1 = this.getBitSet();
        BitSet bM2 = mot2.getBitSet();
         
        for(int i =0; i < 32; i++) {
            int bM1INT = bM1.get(i) ? 1 : 0; // 1 si true, 0 si false
            int bM2INT = bM2.get(i) ? 1 : 0;
            
            int calc = (retenue + bM1INT + bM2INT);
             
            retenue = calc > 1 ? 1 : 0; //si calc est supérieur à 1 la retenue est égale a 1
               
            boolean res = calc % 2 == 1; //si calc == 1  le bit est true
            
            
            bR.set(i, res);
        }
        
        MotBinaire mbRes = new MotBinaire(bR,32);// sortie des addition de taille 32
        
        return mbRes;
     }
    
     /**
      * Scinde le mot binaire en une liste de mot binaire de taille donnée. 
      * @param tailleMorceau taille des morceaux
      * @return la liste des morceaux
      */
     public ArrayList<MotBinaire> scinder(int tailleMorceau) {
        ArrayList<MotBinaire> res = new ArrayList<>();
            
            //Pour chaque partage possible de la tailleMorceau choisie, on créer un nouveau mot
            for(int i = 0; i<this.taille;i+=tailleMorceau) {
                MotBinaire Mot  = new MotBinaire(this.listeBits.get(i,i+tailleMorceau),tailleMorceau);
                res.add(Mot);
            }
            
        return res;
    }
     
     /**
      * Concaténation de deux mots binaires
      * @param mot le deuxième mot
      * @return le résultat de la concaténation
      */
     public MotBinaire concatenation(MotBinaire mot) {
	return new MotBinaire(this.toString()+mot.toString());
     }
     
}