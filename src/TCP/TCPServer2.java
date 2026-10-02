package TCP;



import java.io.*; 
import java.net.*; 
import java.util.Scanner;


public class TCPServer2 {
	
	public static void main(String argv[]) throws Exception 
	{ 
		String clientSentence; 
		String capitalizedSentence; 

		Scanner sc = new Scanner(System.in);
		ServerSocket welcomeSocket = new ServerSocket(6549); 

		while(true) { 

			Socket connectionSocket = welcomeSocket.accept(); 

			BufferedReader inFromClient = 
					new BufferedReader(new
							InputStreamReader(connectionSocket.getInputStream())); 
			DataOutputStream  outToClient = 
					new DataOutputStream(connectionSocket.getOutputStream()); 

			clientSentence = inFromClient.readLine(); 
			System.out.println("FROM: "+clientSentence);

			capitalizedSentence = sc.nextLine() + '\n'; 

			outToClient.writeBytes(capitalizedSentence); 
		} 
	} 
} 




