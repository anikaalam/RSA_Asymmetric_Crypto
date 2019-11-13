/* Simple Chat Server */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

 public static void main(String args[]) throws Exception
 {
	String message;
	String messagereturn;
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
		message = in.readLine();
            	randomNum= min + (int)(Math.random() * ((max-min)+1));
		System.out.println("Client #"+ num +": " + message);
		messagereturn = "You send me -> " +message.toUpperCase() + ". Your random # is: "+randomNum;
		toClient.writeBytes(messagereturn + "\n"); //send the answer
            connsock.close(); //close connection
	}
 }

        /* You will need this piece of code for PartI to convert 
		 * the String-format of the key (that will be passed as an argument)
		 * to a byte array that will be used for the
		 * symmetric encryption and decryption functions.
		 * You should add/uncomment this piece of code to the server and
		 * client to get the symmetric key in your proper format
		 * to use with the JCE functions. */
		
 // You will need to add the proper libraries for the following code to work
 // Reads key from the text file, decodes
 // it and returns it as a SecretKey object
/* public static SecretKey readKey() throws Exception{
	try{
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		byte[] decodedKey = Base64.getDecoder().decode(in.readLine());
		// rebuild key using SecretKeySpec
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
		in.close();

		return originalKey;
	}catch(Exception e){
		return null;
	}
 }*/ 
}
