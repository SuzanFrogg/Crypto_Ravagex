package algorithmes.chiffrement.RSA;

import donnees.NombreBinaire;

/**
 * Description de la classe
 * @author Matthieu
 */
public class RabinMiller {

    
    /**
     * @author Mathys
     * Algorithme provenant de Wikipedia
     * Méthode renvoyant si a est un témoin de Miller de n (preuve que n est composé)
     * @param n
     * @param a
     * @return true si a est un témoin de Miller ou false
     */
    public static boolean temoin(NombreBinaire n, NombreBinaire a) {
        //x = n-1
        NombreBinaire nMinusOne = n.soustraction(new NombreBinaire(1));
    System.out.println("nMinusOne : "+nMinusOne);
        int s = 0;
        NombreBinaire d = new NombreBinaire();

        //Calcul de s correspond au nombre de 0 avant le premier bit à 1
        for(int i = nMinusOne.getTaille(); i >0;i--) {
            if(nMinusOne.get(i)) {
                s++;
            }
            else { break; }
        }  
    System.out.println("s : "+s);
        //Récupération de la partie sans ces bit à 0
        for(int j =0; j<nMinusOne.getTaille()-s;j++) {
            d.set(j, nMinusOne.get(j));
        }
        //calcul de x
        NombreBinaire x = a.puissanceModulo(d, n);
    System.out.println("x : "+x);
        //Dans ces deux cas a n'est pas un témoin de Miller
        if(x.estEgal(new NombreBinaire(1)) || x.estEgal(nMinusOne)) {
    System.out.println("Probleme 1");
            return false;            
        }
    System.out.println("boucle k");

        //Calcul du reste de la division de x^2 par n
        for(int k =0;k<s-1;k++) {
            x = x.puissanceModulo(new NombreBinaire(2), n);
    System.out.println("new x : "+x);
            //Dans ce cas a n'est pas un témoin de Miller
            if(x.estEgal(nMinusOne)) { 
    System.out.println("probleme 2");
                return false; }
        }
        //Si aucuns problème rencontré : a est un témoin de Miller, n est composé
        return true;
    }
    
    //Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
    public static boolean testRabinMiller(NombreBinaire n) {
       //TODO
       return false;
    }
    
    //Renvoie le premier nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) {
       //TODO
       return null;
    }
}
