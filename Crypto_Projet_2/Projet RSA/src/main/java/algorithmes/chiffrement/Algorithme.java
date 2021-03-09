package algorithmes.chiffrement;

import donnees.cles.Cles;
import donnees.messages.Message;
import exceptions.ExceptionCryptographie;

/**
 * Interface représentant un algorithme de cryptographie
 * @author simonetma
 */
public interface Algorithme {
    
    /**
     * Renvoie le nom de l'algorithme
     * @return le nom de l'algorithme
     */
    public String getNom();
    
    /**
     * Méthode servant à chiffrer un message avec un ensemble de clés
     * @param message Le message à chiffrer
     * @param clesPubliques Série des clés publiques utilisées pour le codage
     * @param clesPrivees Série des clés privées utilisées pour le codage
     * @return Le message codé
     * @throws ExceptionCryptographie en cas de bug
     */
    public Message chiffrer(Message message,Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie;
    
    /**
     * Méthode servant à décoder un message avec un ensemble de clés
     * @param message Le message à décoder
     * @param clesPubliques Série des clés publiques utilisées pour le décodage
     * @param clesPrivees Série des clés privées utilisées pour le décodage
     * @return Le message décodé
     * @throws ExceptionCryptographie en cas de bug
     */
    public Message dechiffrer(Message message,Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie;
}
