package donnees;

import exceptions.ExceptionConversionImpossible;
import java.util.BitSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Description de la classe
 * @author Matthieu
 */
public class NombreBinaire {
        
    private BitSet listeBits;
    
    //Génère un nombre binaire aléatoire de "taille" bits au maximum.
    public static NombreBinaire randomAvecTailleMax(int taille) {
        //Il y a une probabilité d'obtenir comme taille 0, il faut le prendre en compte
        String res = (taille==0)? "":"1";
        Random rand = new Random();

        for(int i =0;i<taille-(int)(Math.random()*taille)-1;i++) {
            res += String.valueOf(rand.nextInt(2));
        }
        
        return new NombreBinaire(res);
    }
    
    
    //renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max) {
       //TODO
       return null;
    }
   
    
    //Set un bit
    public void set(int i, boolean valeur) {
        this.listeBits.set(i,valeur);
    }
    
    //Get un bit
    public boolean get(int i) {
        return this.listeBits.get(i);
    }
    
    
    //Constructeurs standard
    public NombreBinaire() {
        this.listeBits = new BitSet();
    }
    
    //Constructeur clone
    public NombreBinaire(NombreBinaire nombre) {
        this.listeBits = new BitSet();
        for(int i=0;i<nombre.listeBits.length();i++) {
            this.listeBits.set(i,nombre.listeBits.get(i));
        } 
    }
    
    //Constructeur clone
    public NombreBinaire(BitSet bitset) {
        this.listeBits = new BitSet();
        for(int i=0;i<bitset.length();i++) {
            this.listeBits.set(i,bitset.get(i));
        } 
    }
    
    //Constructeur à partir d'un long
    public NombreBinaire(Long valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    //Constructeur à partir d'un int
    public NombreBinaire(int valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    //Constructeur à partir d'un byte 
    public NombreBinaire(byte b) {
        byte[] bt = new byte[1];
        bt[0] = b;
        this.listeBits = BitSet.valueOf(bt);
    }
    
    //Constructeur à partir d'une chaine de caractère binaire
    public NombreBinaire(String s) {
        this();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(s.length()-i-1) == '1') {
                this.listeBits.set(i,true);
            }
        }
    }
    
    public BitSet asBitSet() {
        return this.listeBits;
    }
    
    public int getTaille() {
        return this.listeBits.length();
    }
    
    //Convertion en entier non signé 
    public int asInteger() throws ExceptionConversionImpossible{
        if(this.listeBits.length() > 31) throw new ExceptionConversionImpossible("Nombre binaire en entier (trop grand)");
        int res = 0;
        for(int i=0;i<this.listeBits.length();i++) {
            if(this.listeBits.get(i)) {
                res += Math.pow(2, i);
            }
        }
        return res;
    }
    
    //Affichage (dans le bon sens cette foi)
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.getTaille();i++) {
            if(this.listeBits.get(i)) {
                res = "1"+res;
            }
            else {
                res = "0"+res;
            }
        }
        if(res == "") {
            res = "0";
        }
        return res;
    }
     
    /**
     * @author Manon
     * Renvoie le résultat de l'addition de this avec mot2
     * @param mot2 un second NombreBinaire
     * @return l'addition des deux nombres Binaires sous la forme d'un nombre Binaire
     * @throws ExceptionConversionImpossible 
     */
     public NombreBinaire addition(NombreBinaire mot2) {
             int retenue = 0;
             NombreBinaire B1 = new NombreBinaire(this);
             NombreBinaire B2 = new NombreBinaire(mot2);
             BitSet bitSet = new BitSet();
             try {
                 int max = (B1.asInteger()>B2.asInteger())? B1.asInteger() : B2.asInteger();
                 for(int i=0;i<max;i++){
                     int intB1 = B1.get(i) ? 1 : 0; // 1 si true, 0 si false
                     int intB2 = B2.get(i) ? 1 : 0;
                     int calc = (retenue + intB1 + intB2);

                     retenue = calc > 1 ? 1 : 0; //si calc est supérieur à 1 la retenue est égale a 1

                     boolean res = calc % 2 == 1; //si calc == 1  le bit est true

                     bitSet.set(i, res);
               }               
            } catch (ExceptionConversionImpossible ex) {
               Logger.getLogger(NombreBinaire.class.getName()).log(Level.SEVERE, null, ex);
            }
             
             return new NombreBinaire(bitSet);
     }
     
     //renvoie le resultat de l'addition de this avec mot3
     public NombreBinaire soustraction(NombreBinaire mot2) {
      /**
     * Renvoie le résultat de this   - mot2 [2^32]
     * @param mot2 2nd mot binaire
     * @return le résultat de l'addition
     */
        int retenue = 0;        
        BitSet bR = new BitSet();
        BitSet bitSet1 = this.listeBits;
        BitSet bitSet2 = mot2.asBitSet();
         
        for(int i =0; i < this.getTaille(); i++) {
            int nb1 = bitSet1.get(i) ? 1 : 0; // 1 si true, 0 si false
            int nb2 = bitSet2.get(i) ? 1 : 0;
            
            int calc = (nb1 - nb2 - retenue);
             
            retenue = calc < 0 ? 1 : 0; //si calc est supérieur à 1 la retenue est égale a 1
               
            //boolean res = calc % 2 == 1; //si calc == 1  le bit est true
            
            
            bR.set(i, Math.abs(calc) % 2 == 1);
        }
        
        NombreBinaire mot3 = new NombreBinaire(bR);
        
        return mot3;
        

     }
     
     //Caclule le décalage de n bits (multiplie par 2^n)
     public NombreBinaire decalage(int n) {
       int dec = n;
       String s = "";
       for (int i = 0; i<n; i++){
           s += '0';
       }
       String mot = this.toString();
       mot += s;
       NombreBinaire nb = new NombreBinaire(mot);
       return nb;
     }
     
     //Calcul la multiplication de this avec mot2
     public NombreBinaire multiplication(NombreBinaire mot2) {
       //TODO
       return null;
     }
     
     //
     /**
      * @author Manon
      * Renvoie si this est plus petit ou égal à mot2
      * @param mot2
      * @return 
      */
     public boolean estInferieurA(NombreBinaire mot2) {
         boolean res = false;
        try {
            //Vérification de mot1 inférieur à mot2
            res = (this.asInteger()<=mot2.asInteger())? true : false;
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(NombreBinaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
     }
     
     //Calcul this modulo mot2 via une division euclidienne
     public NombreBinaire modulo(NombreBinaire mot2) {
       //TODO
       return null;
     }  

     //Calcul le quotient dans la division euclidienne de this par mot2
     public NombreBinaire quotient(NombreBinaire mot2) {
       //TODO
       return null;
     }
     
     //Calcul de this^exposant modulo m par exponentiation modulaire rapide
     public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) {
       //TODO
       return null;
     }
     /**
      * @author Mathys
      * @param mot2
      * @return
      * @throws ExceptionConversionImpossible 
      */
     public boolean estEgal(NombreBinaire mot2){
        boolean res = false;
        try {
            //Retourner "true" si il y a égalité.
             res = (this.asInteger()==mot2.asInteger())? true : false;
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(NombreBinaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
     }
     
     //Renvoie si un nombre est pair
     public boolean estPair() {
        boolean res = false;

        //Si le nombre binaire est très grand, le calcul plantera donc
            //on n'effectue le calcul que sur le dernier digit
        int lastDigit = this.get(0)? 1:0;
        //lastDigit est à false si le dernier bit est un 0, true sinon
        //Donc le calcul de lastDigit % 2 == 0 
        res = (lastDigit % 2 == 0)? true : false;       
         System.out.println("lastDigit : "+lastDigit+" res : "+res);
        
        return res;
     }
     
     
     public NombreBinaire PGCD(NombreBinaire mot2) {
       //TODO
       return null;
     }
     
     //Calcul de l'inverse modulo nombre
     //Basé sur l'algo d'euclide étendu (adapté).
     public NombreBinaire inverseModulaire(NombreBinaire nombre) {
         NombreBinaire ZERO = new NombreBinaire(0);
            
         NombreBinaire n0 = new NombreBinaire(nombre);
         NombreBinaire b0 = new NombreBinaire(this);
         NombreBinaire t0 = new NombreBinaire(0);
         NombreBinaire t = new NombreBinaire(1);
         
         NombreBinaire q = n0.quotient(b0);
         NombreBinaire r = n0.modulo(b0);
         while(!r.estEgal(ZERO)) {
             NombreBinaire produit = q.multiplication(t);
             NombreBinaire memoire;
             //Gére le fait qu'un nombreBinaire ne peut pas être négatif......
             if(t0.estInferieurA(produit)) {
                memoire = nombre.soustraction(produit.soustraction(t0).modulo(nombre));
             }
             else {
                memoire = t0.soustraction(produit).modulo(nombre);  
             }
             
             t0 = t;
             t = memoire;
             n0 = b0;
             b0 = r;
             q = n0.quotient(b0);
             r = n0.modulo(b0);
         }
         return t;
     }
}
