package donnees.messages;

import donnees.MotBinaire;
import exceptions.ExceptionConversionImpossible;
import java.util.ArrayList;

/**
 * Description de la classe
 * @author Matthieu
 */
public class MessageBinaire implements Message {

    private MotBinaire listeBits;
    
    public MessageBinaire(MotBinaire listeBits) {
        this.listeBits = listeBits;
    }
    
    @Override
    public String asString() throws ExceptionConversionImpossible {
        return this.listeBits.asString();
    }

    @Override
    public Integer asInteger() throws ExceptionConversionImpossible {
        return this.listeBits.asInteger();
    }

    @Override
    public ArrayList<Integer> getListAsciiCode() throws ExceptionConversionImpossible {
        throw new ExceptionConversionImpossible("MessageBinaire vers AsciiCode");
    }

    @Override
    public MotBinaire asMotBinaire() throws ExceptionConversionImpossible {
        return this.listeBits;
    }

}
