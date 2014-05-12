/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentwarrior;

/**
 *
 * @author Farazath Ahamed
 */
public class MessageDecoder {
    

public String splitmessages(String msg){
     
    if(msg!= null){
        
        if (msg.contains("_") || msg.contains("DEAD") || msg.contains("OBSTACLE")) {
            System.out.println("Special Messages / Error Message");
            return "E";
        } 
        else if (msg.charAt(0) == 'G') {
            System.out.println("Global Update");
             return "G";
        }
        else if (msg.charAt(0) == 'L') {
            System.out.println("Life Pack Update");
             return "L";
        }
        else if (msg.charAt(0) == 'C') {
            System.out.println("Coin Update");
             return "C";
        }
        else if (msg.charAt(0) == 'S') {
            System.out.println("Start Update");
             return "S";
        }
        else if (msg.charAt(0) == 'I') {
            System.out.println("Initial Update");
             return "I";
        }
        else if(msg.charAt(0)=='C' && msg.charAt(1)=='E'){
            return "Attack";
        }
        else{
            System.out.println("Error");
            return "NULL";
        }
    
    }
    
    else{
    
        System.out.println("No reply from the server");
        return "NO REPLY";
    }
}    

public String[][] buildMap(){

return new String[10][10];
}     
    
    
}
