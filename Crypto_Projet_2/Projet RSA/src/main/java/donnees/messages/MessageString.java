package donnees.messages;

import donnees.MotBinaire;
import exceptions.ExceptionConversionImpossible;
import java.util.ArrayList;

public class MessageString implements Message {

    private String message;
    
    public MessageString(String message) {
        this.message = message;
    }
    
    @Override
    public String asString() throws ExceptionConversionImpossible {
        return message;
    }

    @Override
    public Integer asInteger() throws ExceptionConversionImpossible{
        throw new ExceptionConversionImpossible("Message_String vers Integer");
    }

    @Override
    public ArrayList<Integer> getListAsciiCode() throws ExceptionConversionImpossible {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<message.length();i++) {
            result.add(Integer.valueOf(message.charAt(i)));
        }
        return result;
    }

    @Override
    public MotBinaire asMotBinaire() throws ExceptionConversionImpossible {
        throw new ExceptionConversionImpossible("\"Message_String vers Mot binaires"); 
    }

    
    
}
