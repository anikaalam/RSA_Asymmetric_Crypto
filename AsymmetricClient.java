
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

public class AsymmetricClient {

	public static void main(String[] args) throws Exception{

		if(args.length > 0) {
		String	message;
		String	returnmessage;
		String EncodedencryptedStringData; 
		String EEmessagereturnString;

		//1. read the server's public key 
		File file = new File(args[0]);
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);

        byte[] keyBytes = new byte[(int) file.length()];
        dis.readFully(keyBytes);
        dis.close();//end of reading the public key

        //client reads msg stdin
		BufferedReader userdata = new BufferedReader(new InputStreamReader(System.in));
		
		//connect to server
        //server has to be listening on this port
		Socket mysock = new Socket("localhost", 12345); 
		DataOutputStream toServer = new DataOutputStream( mysock.getOutputStream());
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(mysock.getInputStream()));	
		System.out.println("Client is here... " );
        System.out.print("Enter text: ");
        message = userdata.readLine();
        byte[] byteMessage = message.getBytes();

        //Client: plaintext should be read from stdin, but sent to the server after it is encrypted with the Server’s public
		//key and encoded.

		//****uncomment RSAkeyspec to see what happens 

		//rebuild public key 
		X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(Base64.getDecoder().decode(keyBytes));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //RSAPublicKey originalPubKey = (RSAPublicKey)keyFactory.generatePublic(pubSpec);
        PublicKey originalPubKey = keyFactory.generatePublic(pubSpec);


        //3. Encyrpt message using originalPubKey - server's public key 
    
       Cipher cipher = Cipher.getInstance("RSA"); // create conversion processing object
   	   cipher.init(Cipher.ENCRYPT_MODE, originalPubKey); // initialize object's mode and key
       byte[] encryptedByteData = cipher.doFinal(byteMessage); // use object for encryption
      // System.out.println("encryptedByteData:  " + encryptedByteData);


        //4. Encode encryptedByteData before sending to server - convert to string 
        byte[] EncodedencryptedByteData = Base64.getEncoder().encode(encryptedByteData);
		EncodedencryptedStringData = new String(EncodedencryptedByteData);
		//System.out.println("EncodedencryptedStringData:  " + EncodedencryptedStringData);
		toServer.writeBytes(EncodedencryptedStringData + "\n");


	   //15. client receives encoded message 
		EEmessagereturnString = fromServer.readLine();

		//16. convert EEmessagereturnString to EEmessagereturnByte
		byte[] EEmessagereturnByte = EEmessagereturnString.getBytes();
		//17. Decodes EEmessagereturnByte to DEEmessagereturnByte
		byte[] DEEmessagereturnByte = Base64.getDecoder().decode(EEmessagereturnByte);
		String newString = new String(DEEmessagereturnByte);
		
		//19. Print out DDEEmessagereturnString
		System.out.println("Server replied: " + newString);
		mysock.close();
	

	}//args check 	

}//main 
}