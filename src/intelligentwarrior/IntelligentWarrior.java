/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentwarrior;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Farazath Ahamed
 */
public class IntelligentWarrior {

    /**
     * @param args the command line arguments
     */
    
    static String commands[] = {"RIGHT#","SHOOT#","LEFT#","SHOOT#"};//,"UP#","DOWN#"};
    static int count=0;
    
    public static void main(String[] args) {
             //   writeToServer("JOIN#");
                
                Communicator c = Communicator.GetInstance();
                c.sendData("JOIN#");
                
                
                while(true){
                    
                    try {
                        String result = c.reciveData();
                        MessageDecoder md = new MessageDecoder();
                        String test = md.splitmessages(result);
                                
                        if(!"E".equals(test) && !"NO REPLY".equals(test) && !"S".equals(test) && !"I".equals(test)){
                          c.sendData(commands[count%4]);
                            count++;                            
                        }
                        if("Attack".equalsIgnoreCase(test)){
                           c.sendData("SHOOT#");
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(IntelligentWarrior.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
    }
    
    public static void writeToServer(String message ){
         BufferedWriter writer;
        
        try{
        Socket serverSocket = new Socket("127.0.0.1",6000);
        
        writer = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
        writer.write(message);
        writer.close();
      //  listenToServer();
        }catch(Exception ex){
            System.out.println("Error");
       }            
        
    }
    
    public static void listenToServer(){
        BufferedReader read;
        
        try{
            ServerSocket clientServerSocket = new ServerSocket(7000);
            Socket clientSocket = clientServerSocket.accept();
            read = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            String update = read.readLine();
            System.out.println(update);
            handleUpdate(update);
            read.close();
            
        }
        catch(Exception ex){
            
        }   
    }
    
   public static void handleUpdate(String update){
                if(update.charAt(0)=='G'){
                    System.out.println("Global Update");
                }
                else if(update.charAt(0)=='I'){
                    System.out.println("Initial Update");
                }
                else{
                    System.out.println("something else");
                }
   } 
    
    
 
}
