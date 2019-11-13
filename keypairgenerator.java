import java.lang.Object;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyStore;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Base64;



public class keypairgenerator {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
	
		KeyPairGenerator KeyPairGen = KeyPairGenerator.getInstance("RSA");
		KeyPairGen.initialize(512);//2048
		KeyPair keyPair= KeyPairGen.generateKeyPair();

	//	String encodedPrivateKey = Base64.getMimeEncoder().encodeToString( keyPair.getPrivate().getEncoded());
	  //  String encodedPublicKey = Base64.getMimeEncoder().encodeToString( keyPair.getPublic().getEncoded());

	    String encodedPrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

	    String encodedPublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

	 //   PrivateKey privateKey = keyPair.getPrivate();
	

		FileWriter out;

		String outFile = "";
		out = new FileWriter(outFile + "privateKey.txt");
		out.write(encodedPrivateKey) ;
		out.close();

		String outFile2 = "";
		out = new FileWriter(outFile2 + "publicKey.txt");
		out.write(encodedPublicKey) ;
		out.close();
		



	}

}



