
import java.math.BigInteger;
import java.security.spec.RSAKeyGenParameterSpec;
import java.net.ServerSocket;
import java.net.Socket;
/* Simple Chat Client */
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.io.InputStream;
import java.io.FilterInputStream;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.net.Socket;
import java.security.*;
import java.security.interfaces.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.io.InputStream;
import java.io.FileInputStream;
import java.security.Key;
import javax.crypto.KeyGenerator;

import java.util.Base64;
import javax.crypto.*;
import java.security.AlgorithmParameters;
import javax.crypto.Cipher; 
import java.util.*; 
import java.security.spec.*;

import java.security.interfaces.RSAKey;
import java.security.spec.RSAPrivateKeySpec; 
import java.security.spec.RSAPublicKeySpec;

import java.lang.Object;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyStore;
import java.security.KeyFactory;
import java.io.FileReader;
import java.io.BufferedReader;

public class AsymmetricServer {

 public static void main(String args[]) throws Exception
 {

 	if(args.length > 0) {
 	
	String message;
	String messagereturn;
	String EENCStringMessage; //from client \
	String EEmessagereturnString;
	String EncodedencryptedStringData;
        int num = 0;
        int min = 10000;
        int max = 99999;
        int randomNum;
        System.out.println("Server has started...");
	ServerSocket serversock = new ServerSocket(12345); //listen on port 12345
	while(true)
	{
		Socket connsock = serversock.accept(); //wait for a connection
		InputStreamReader fromClient =  new InputStreamReader(connsock.getInputStream());
		DataOutputStream toClient = new DataOutputStream(connsock.getOutputStream());
		num +=1;
        BufferedReader in = new BufferedReader(fromClient);
		EncodedencryptedStringData = in.readLine();
		//System.out.println("EncodedencryptedStringData:  " + EncodedencryptedStringData);
		String keyFile = args[0];
	

		//convert to byte and decode the message from message first 
		byte[] DecodedencryptedByteData = Base64.getDecoder().decode(EncodedencryptedStringData.getBytes());


		//7. Use client's private key to decode the message 
		//i. read private key first 
		File file2 = new File(args[0]);
        FileInputStream fis2 = new FileInputStream(file2);
        DataInputStream dis2 = new DataInputStream(fis2);

        byte[] keyBytes2 = new byte[(int) file2.length()];
        dis2.readFully(keyBytes2);
        dis2.close();

        //ii. reconstruct key 
        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyBytes2));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //RSAPublicKey originalPubKey = (RSAPublicKey)keyFactory.generatePublic(pubSpec);
        PrivateKey originalPrivKey = keyFactory.generatePrivate(privSpec);

        //iii. decrypt using the key 
        Cipher p = Cipher.getInstance("RSA"); // create conversion processing object
   	    p.init(Cipher.DECRYPT_MODE, originalPrivKey); // initialize object's mode and key
    	byte[] DecryptedByteData = p.doFinal(DecodedencryptedByteData); // use object for encryption
    	//8. Convert DDECmessage to string -> DDECmessageString
		String DecryptedStringData = new String(DecryptedByteData);
    	//System.out.println("DecryptedStringData:  " + (DecryptedStringData));
		
		//9. server sends plaintext mesage with Gbye to client 

        
		///System.out.println(DecryptedStringData);
		//10. Add extra stuff to string DDECmessageString
		messagereturn = DecryptedStringData+ "  Goodbye!!";
		//System.out.println(messagereturn);

		//11. convert string messagereturn to messagereturnByte
		byte[] messagereturnByte = messagereturn.getBytes();

    
		//12. Encode EmessagereturnByte to EEmessagereturnByte 
		byte[] EncodedmessagereturnByte = Base64.getEncoder().encode(messagereturnByte);

		//14. Convert EEmessagereturnByte to String EEmessagereturnString and send to server 
		String EncodedmessagereturnString = new String(EncodedmessagereturnByte);

		toClient.writeBytes(EncodedmessagereturnString + "\n"); //send the answer
            connsock.close(); //close connection
	}//while 
 }//args check 

}//main
}