/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentwarrior;

/**
 *
 * @author Farazath Ahamed
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

//This is responsible for communication over the network
public class Communicator {

    // Variables
    private BufferedWriter output; // outgoing
    private BufferedReader input; //incoming
    private final int clientPort = 7000; //client port
    private final int serverPort = 6000; //server port
    private final String IPaddress = "127.0.0.1"; // IP of the server
    private Socket serverSocket;
    private Socket clientSocket;
    private ServerSocket ServerSocketForClient;
    private static Communicator comm = new Communicator();

    private Communicator() {
    }

    public static Communicator GetInstance() {
        return comm;
    }

    public String reciveData() throws IOException {
        boolean error = false;
        clientSocket = null;
        String readLine = "#";

        try {
            ServerSocketForClient = new ServerSocket(clientPort);

            //connect
            clientSocket = ServerSocketForClient.accept();
            if (ServerSocketForClient.isBound()) {
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                while (!input.ready()) {
               // System.out.println("input isn't ready");
                    Thread.sleep(500);
                }
                readLine = input.readLine();
                System.out.println("Server Reply : "+readLine);
                input.close();
                clientSocket.close();
                ServerSocketForClient.close();
                error = false;
            }


        } catch (IOException | InterruptedException e) {
            System.out.println("Server Communication(recieving) Failed " + e.getMessage());
            error = true;
            clientSocket.close();
            ServerSocketForClient.close();
        } finally {
           
            if (clientSocket != null) {
                if (clientSocket.isConnected()) {
                    try {
                        clientSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (error) {

                this.reciveData();
            }
          //  System.out.println("message : "+readLine.split("#")[0]);
           
        }
        return readLine.split("#")[0];
    }

    public void sendData(String msg) {
        try {
            serverSocket = new Socket(IPaddress, serverPort);
            if (this.serverSocket.isConnected()) {
                //To write to the socket
                this.output = new BufferedWriter(new OutputStreamWriter(this.serverSocket.getOutputStream()));
                output.write(msg);
                output.close();
                serverSocket.close();
                System.out.println("msg: " + msg + " sent to server");
            }

        } catch (Exception e) {
            System.out.println("Server Communication(sending) Failed for " + msg + " " + e.getMessage());
        } finally {
        }

    }
}
