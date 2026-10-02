package TCP;




import java.io.*;
import java.net.*;

public class TCPClient2 {
    public static void main(String[] args) throws Exception {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String input;
        System.out.println("Type 'CONNECT' to connect to the server");
        while (!(input = userInput.readLine()).equals("CONNECT")) {
            System.out.println("INVALID INPUT");
        }

        String serverAddress = "192.168.1.5";
        Socket clientSocket = new Socket(serverAddress, 2531);

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

        System.out.println("Connected to server");

        
        new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = inFromServer.readLine()) != null) {
                    System.out.println("Server: " + serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        
        String clientMessage;
        
        while ((clientMessage = userInput.readLine()) != null) {
            outToServer.println(clientMessage);
        }
        
        inFromServer.close();
        outToServer.close();
        clientSocket.close();
    }
}
