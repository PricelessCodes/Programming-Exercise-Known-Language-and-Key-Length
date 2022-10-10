import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices)
    {
        StringBuilder sb = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i += totalSlices)
        {
            sb.append(message.charAt(i));
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        for (int i = 0; i < klength; i++)
        {
            String message = sliceString(encrypted, i, klength);
            
            CaesarCracker cc = new CaesarCracker();
            
            int k = cc.getKey(message);
            
            key[i] = k;
        }
        
        return key;
    }

    public void breakVigenere () {
        
        FileResource fr = new FileResource("messages/secretmessage1.txt");
        
        String message = fr.asString();
        
        int kLength = 4;
        char mostCommenLetter = 'e';
        
        int[] key = tryKeyLength(message, kLength, mostCommenLetter);
        
        for (int i = 0; i < key.length; i++)
        {
            System.out.println(key[i]);
        }
        
        VigenereCipher vc = new VigenereCipher(key);
        
        String decryptedMessage =  vc.decrypt(message);
        
        System.out.println("decrypted Message: ");
        System.out.println(decryptedMessage);
    }
    
}
