/* Simple Chat Client */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.net.Socket;

public class SimpleClient {

	public static void main(String[] args) throws Exception{
		String	message;
		String	returnmessage;
		BufferedReader userdata = new BufferedReader(new InputStreamReader(System.in));
		
		//connect to server
        //server has to be listening on this port
		Socket mysock = new Socket("localhost", 12345); 
		DataOutputStream toServer = new DataOutputStream( mysock.getOutputStream());
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(mysock.getInputStream()));	
		System.out.println("Client " + args[0] + " is here... " );
        System.out.print("Enter text: ");
        message = userdata.readLine();
		toServer.writeBytes(message + "\n");
		returnmessage = fromServer.readLine();
		System.out.println("Server replied: " + returnmessage);
		mysock.close();
	}
	

}
