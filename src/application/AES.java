package application;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Possible KEY_SIZE values are 128, 192 and 256
 * Possible T_LEN values are 128, 120, 112, 104 and 96
 */

public class AES {
	private SecretKey key;
    private int KEY_SIZE = 128;
    private int T_LEN = 128;
    //private byte[] IV;
	private Cipher encryptionCipher;

    public void init(String path) throws Exception {
    	BufferedReader reader = new BufferedReader(new FileReader(path));
    	BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
    	encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    	String line;
    	
    	if ((line = reader.readLine())==null) {
    		KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(KEY_SIZE);
            key = generator.generateKey();
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
         //   IV = encryptionCipher.getIV();
            System.out.println("wrote to file:" +encode(key.getEncoded()));
            writer.write(encode(key.getEncoded()));
    	}else {
    		System.out.println("loaded from file:"+line);
    		String[] data = line.split(",");
    		initFromStrings(data[0]);
    	}
    	reader.close();
    	writer.flush();
    	writer.close();
        
    }

    private void initFromStrings(String secretKey) throws Exception {
        key = new SecretKeySpec(decode(secretKey), "AES");
        encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        //this.IV = decode(IV);
    }

    public String encrypt(String message) throws Exception {
        byte[] messageInBytes = message.getBytes();
        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
    }
    public String decrypt(String encryptedMessage) throws Exception {
        byte[] messageInBytes = decode(encryptedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES");
       // GCMParameterSpec spec = new GCMParameterSpec(T_LEN);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedBytes);
    }


    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }


    
}