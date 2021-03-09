package donnees.messages;

import donnees.MotBinaire;
import exceptions.ExceptionConversionImpossible;
import java.util.ArrayList;

/**
 * Interface représentant un message (crypté ou nom)
 * @author simonetma
 */
public interface Message {

    /**
     * Renvoie le message sous forme d'une chaine de caractères
     * @return le message sous forme d'une chaine de caractères
     * @throws crypto2020.exceptions.ExceptionConversionImpossible
     */
    public String asString() throws ExceptionConversionImpossible;
    
    /**
     * Renvoie le message sous forme d'un entier
     * @return le message sous forme d'un entier
     */
    public Integer asInteger() throws ExceptionConversionImpossible;
    
    /**
     * Renvoie la liste des codes ascii des caractères
     * @return
     */
    public ArrayList<Integer> getListAsciiCode() throws ExceptionConversionImpossible;
    
    /**
     * Renvoie le message sous forme d'un mot binaire
     * @return
     * @throws ExceptionConversionImpossible 
     */
    public MotBinaire asMotBinaire() throws ExceptionConversionImpossible;
}
