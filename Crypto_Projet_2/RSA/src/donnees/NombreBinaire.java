package donnees;

import exceptions.ExceptionConversionImpossible;
import java.util.BitSet;
import java.util.Random;

/**
 * Description de la classe
 * @author Matthieu
 */
public class NombreBinaire {
        
    private BitSet listeBits;
    
    /**
     * @author Mathys
     * Génère un nombre binaire aléatoire de "taille" bits au maximum.
     * @param taille la taille maximale autorisée
     * @return Un nombre binaire de la 
     */
    public static NombreBinaire randomAvecTailleMax(int taille) {
        //Il y a une probabilité d'obtenir comme taille 0, il faut le prendre en compte
        String res = (taille==0)? "":"1";
        Random rand = new Random();

        for(int i =0;i<taille-(int)(Math.random()*taille)-1;i++) {
            res += String.valueOf(rand.nextInt(2));
        }
        
        return new NombreBinaire(res);
    }
    
    
    /**
     * @author Mathys
     * Renvoie un nombre aléatoire entre min (inclu) et max (non inclu) 
     * @param min le nombre minimum INclu
     * @param max le nombre maximum EXclu 
     * @return 
     */
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max) {
        //Si il n'y a que un de différence entre min et max alors renvoyé min
        if(min.addition(new NombreBinaire(1)).toString().equals(max.toString())) {
            return min;
        }
        
        //Sinon il faut effectuer des 
        NombreBinaire diff = max.soustraction(min);
        NombreBinaire res = null;

        while(res== null || !res.estInferieurA(diff)){
            res = NombreBinaire.randomAvecTailleMax(diff.getTaille());
        }
         return res.addition(min);
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
     * @author Albane
     * Renvoie le résultat de l'addition de this avec mot2
     * @param mot2 un second NombreBinaire
     * @return l'addition des deux nombres Binaires sous la forme d'un nombre Binaire
     * @throws ExceptionConversionImpossible 
     */
     public NombreBinaire addition(NombreBinaire mot2) {
        int retenue = 0;        
        BitSet bR = new BitSet();
        BitSet bitSet1 = this.listeBits;
        BitSet bitSet2 = mot2.asBitSet();
        
        int taille = 0;
        if (this.getTaille()>mot2.getTaille()){
            taille = this.getTaille();
        }
        else{
            taille = mot2.getTaille();
        }
        for(int i = 0; i < taille; i++) {
            int nb1 = bitSet1.get(i) ? 1 : 0; // 1 si true, 0 si false
            int nb2 = bitSet2.get(i) ? 1 : 0;
            
            int calc = (nb1 + nb2 + retenue);
             
            retenue = calc > 1 ? 1 : 0; //si calc est supérieur à 1 la retenue est égale a 1
            
            boolean res = calc % 2 == 1; //si calc == 1  le bit est true
            
            bR.set(i, res);
        }
        if (retenue == 1){
            bR.set(taille, true);
        }
        
        NombreBinaire mot3 = new NombreBinaire(bR);
        
        return mot3;
     }
     
     
     /**
      * @author Manon
      * renvoie le resultat de la soustraction de this avec mot2
      * @param mot2 le Nombre Binaire à soustraire
      * @return la difference des deux mots
      */
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
     
     /**
      * @author Albane
      * Calcul la multiplication de this avec mot2
      * @param mot2 le 2eme nombre binaire
      * @return le resultat de la multiplication
      */
     public NombreBinaire multiplication(NombreBinaire mot2) {
        //initialisation des variables de travail
        NombreBinaire res = new NombreBinaire(0);
        NombreBinaire temp = new NombreBinaire(0);
        //boucle permettant de trouver les bits à 1
        for(int i=0; i<mot2.getTaille(); i++){
            //si le bit est à 1, on fait le décalage de l'endroit ou il est - 1 soit i
            if (mot2.get(i)){
                temp = this.decalage(i);
            }
            //sinon pas de décalage
            else{
                temp = new NombreBinaire(0);
            }
            // adition au fur et à mesure
            res = temp.addition(res);
       }
       return res;
     }
     
     //
     /**
      * @author Manon
      * Renvoie si this est plus petit ou égal à mot2
      * @param mot2
      * @return true s'il est inferieur
      */
     public boolean estInferieurA(NombreBinaire mot2) {
         boolean res = true;
         //comparaison en fonction de la taille
         if (this.getTaille()< mot2.getTaille()){
             res = true;
         }
         else if (mot2.getTaille()<this.getTaille()){
             res = false;
         }
         //si tailles égales, comparaison bit par bit
         else{
             boolean sortie = false;
             int i = 1;
             while ( i<= this.getTaille()){
                 int b1 = this.get(this.getTaille()-i) ? 1 : 0; //1 si true, 0 si false
                 int b2 = mot2.get(this.getTaille()-i) ? 1 : 0; //1 si true, 0 si false
                 if (b1 < b2){
                     res = true;
                     i = this.getTaille() + 1;
                 } else if (b2 < b1) {
                     res = false;
                     i = this.getTaille() + 1;
                 }else{
                     i++;
                 }
             }
         }
        
        return res;
     }
     
     /**
      * @author Mathys
      * Calcul this modulo mot2 via une division euclidienne
      * @param mot2 le deuxième NombreBinaire
      * @return le reste de la division euclidienne
      */
     public NombreBinaire modulo(NombreBinaire mot2) {
         //Déclaration des variables
         NombreBinaire a = new NombreBinaire(this);
         NombreBinaire b = new NombreBinaire(mot2);
         NombreBinaire r = new NombreBinaire(a);
         int n = 0;
         
         //Calcul jusqu’à ce que r < b.
         while(b.estInferieurA(r)) {
             //1. Soit n le décalage nécessaire pour que r et b est la même taille.
             n = r.getTaille()-b.getTaille();
             
             //2. On calcule b′, b décalé de n bits.
             NombreBinaire bPrime = b.decalage(n);
             
             //3. Si b′ > r alors 
             if(!bPrime.estInferieurA(r)) {
                 //on remplace b′ par b décalé de n-1 bits 
                 bPrime = b.decalage(n-1);
             }
             //4. On remplace r par r - b′.
             r = r.soustraction(bPrime);
         
         }
         //On retourne le reste de la division
         return r;
     }  

     //
     /**
      * Calcul le quotient dans la division euclidienne de this par mot2
      * @param mot2 le deuxième NombreBinaire
      * @return le quotient de la division euclidienne
      */
      public NombreBinaire quotient(NombreBinaire mot2) {
         //Déclaration des variables
         NombreBinaire a = new NombreBinaire(this);
         NombreBinaire b = new NombreBinaire(mot2);
         NombreBinaire r = new NombreBinaire(a);
         int q = 0;
         int n = 0;
         
         //Calcul jusqu’à ce que r < b.
         while(b.estInferieurA(r)){
             //1. Soit n le décalage nécessaire pour que r et b est la même taille.
             n = r.getTaille()-b.getTaille();
             
             //2. On calcule b′, b décalé de n bits.
             NombreBinaire bPrime = b.decalage(n);
             
             //3. Si b′ > r alors 
             if(!bPrime.estInferieurA(r)) {
                 //on remplace b′ par b décalé de n-1 bits 
                 bPrime = b.decalage(n-1);
                 //n par n-1.
                 n--;
             }
             
             //4. On remplace r par r - b′.
             r = r.soustraction(bPrime);
             
             //5. On ajoute 2^n à q.
             q +=Math.pow(2, n);
             
         }
         //On retourne le reste de la division
         return new NombreBinaire(q);
     }
      
      
     /**
      * @author Mathys
      * Calcul de this^exposant modulo m par exponentiation modulaire rapide
      * @param exposant un Nombre Binaire
      * @param m un Nombre Binaire
      * @return 
      */ 
     public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) {
       NombreBinaire p = new NombreBinaire(1);
       NombreBinaire base = new NombreBinaire(this);
       
       for(int i = 0;i<=exposant.getTaille();i++) {
           if(exposant.get(i)) {
               p = p.multiplication(base);
               p = p.modulo(m);
           }
           
           base = base.multiplication(base).modulo(m);
       }
       
       return p;
     }
     
     /**
      * @author Mathys
      * @param mot2 le NombreBinaire à comparer
      * @return true si les deux Nombres binaire sont egaux
      */
     public boolean estEgal(NombreBinaire mot2){
            //Retourner "true" si il y a égalité.
            return (this.toString().equals(mot2.toString()));
     }
     
     //Renvoie si un nombre est pair
     public boolean estPair() {

        //Si le nombre binaire est très grand, le calcul plantera donc
            //on n'effectue le calcul que sur le dernier digit
        int lastDigit = this.get(0)? 1:0;
        //lastDigit est à false si le dernier bit est un 0, true sinon
        //Donc le calcul de lastDigit % 2 == 0 
        return (lastDigit % 2 == 0);
     }
     
     /**
      * Calcul du PGCD de this par mot2
      * @param mot2 le 2eme NombreBinaire
      * @return le PGCD de ces deux nombres binaires
      */
     public NombreBinaire PGCD(NombreBinaire mot2) {
         //Déclaration des variables
         NombreBinaire a = new NombreBinaire(this);
         NombreBinaire b = new NombreBinaire(mot2);
         NombreBinaire temp = new NombreBinaire();
         
            //1. si a < b on échange a et b
            if(a.estInferieurA(b)) {
               temp = new NombreBinaire(a);
               a = new NombreBinaire(b);
               b = new NombreBinaire(temp);
            }
            
        //2. Tant que b n’est pas nul, on remplace b par a modulo b et a par b.
        while(!b.estEgal(new NombreBinaire(0))) {    
            temp = new NombreBinaire(b);
            b = a.modulo(b);
            a = temp;
         }
         return a;
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
