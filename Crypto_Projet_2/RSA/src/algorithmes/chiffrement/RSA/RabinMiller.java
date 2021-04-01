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
        int s = 0;
        NombreBinaire d = new NombreBinaire();

        //Calcul de s correspond au nombre de 0 avant le premier bit à 1
        for(int i = nMinusOne.getTaille(); i >0;i--) {
            if(nMinusOne.get(i)) {
                s++;
            }
            else { break; }
        }  

        //Récupération de la partie sans ces bit à 0
        for(int j =0; j<nMinusOne.getTaille()-s;j++) {
            d.set(j, nMinusOne.get(j));
        }
        //calcul de x
        NombreBinaire x = a.puissanceModulo(d, n);

        //Dans ces deux cas a n'est pas un témoin de Miller
        if(x.estEgal(new NombreBinaire(1)) || x.estEgal(nMinusOne)) {
            return false;            
        }

        //Calcul du reste de la division de x^2 par n
        for(int k =0;k<s-1;k++) {
            x = x.puissanceModulo(new NombreBinaire(2), n);

            //Dans ce cas a n'est pas un témoin de Miller
            if(x.estEgal(nMinusOne)) { 

                return false; }
        }
        //Si aucuns problème rencontré : a est un témoin de Miller, n est composé
        return true;
    }
    
    /**
     * Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
     * @param n le nombre binaire à tester
     * @return true si le test est validé
     */
    public static boolean testRabinMiller(NombreBinaire n) {
        for(int i= 0;i<25;i++){
            //Creation des bornes pour creer le nombrebinaire A.
            // Min est à 2 Max est à n-2
            NombreBinaire min = new NombreBinaire(2);
            NombreBinaire max = n.soustraction(min);
            NombreBinaire a = NombreBinaire.random(min, max);
            //si le temoin renvoie true, alors le test de Rabin Miller échoue
            if(temoin(n, a)) return false;
        }
        return true;
    }
    
    //Renvoie le premier nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) {
       //TODO
       return null;
    }
}
