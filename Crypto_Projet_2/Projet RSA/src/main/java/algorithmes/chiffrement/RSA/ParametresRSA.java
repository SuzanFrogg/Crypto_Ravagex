package algorithmes.chiffrement.RSA;

public class ParametresRSA {

    //La taille des morceaux doit au maximum etre TailleDemiCle*2-2 (sinon, risque de dépasser la clé par malchance).
    //Plus la taille de la clé est grande plus l'algo est lent (mais sûr), ici 128bits code 1ko en 20s (pas si mal)
    private static int TAILLEMORCEAU=126;
    private static int TAILLEMDEMICLE=64;
    
    public static int getTailleMorceau() {
        return TAILLEMORCEAU;
    }
    
    public static int getTailleCle() {
        return TAILLEMDEMICLE*2;
    }
    
    public static int getTailleDemiCle() {
        return TAILLEMDEMICLE;
    }
    
}
