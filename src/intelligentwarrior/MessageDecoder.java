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
static final int size = 20;
    public void sortMessage(String message) {

        String type = message.substring(0,2);
        System.out.println(type);
        switch (type) {
            case "I:":
                this.initialUpdate(message);
                break;

            case "S:":
                //start update
                break;

            case "G:":
                //global update
                break;

            case "C:":
                //coin update
                break;

            case "L:":
                //life
                break;

            default:
                // othr messages
                break;

        }
    }

    public String[][] initialUpdate(String message) {
        
        String map[][]= this.buildMap(this.size);
        String temp[] = message.substring(0,message.length()).split(":");
        AI.PlayerName = temp[1];
        
        String bricks[]= temp[2].split(";");
        String stones[]= temp[3].split(";");
        String water[] = temp[4].split(";");

        for (String i : bricks) {      
               String[] xy = i.split(",");
               map[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "B";
        }
        for (String j : stones){
               String[] xy = j.split(",");
               map[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "S"; 
        }
         for (String j :water){
               String[] xy = j.split(",");
               map[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "W"; 
        }
        
//        for(int t=0;t<size;t++){
//            for(int s=0;s<size;s++){
//                System.out.print(map[t][s]+" ");
//            }
//            System.out.println("");
//        }
         
         return map;
    }
    
    

    public String splitmessages(String msg) {

        if (msg != null) {

            if (msg.contains("_") || msg.contains("DEAD") || msg.contains("OBSTACLE")) {
                System.out.println("Special Messages / Error Message");
                return "E";
            } else if (msg.charAt(0) == 'G') {
                System.out.println("Global Update");
                return "G";
            } else if (msg.charAt(0) == 'L') {
                System.out.println("Life Pack Update");
                return "L";
            } else if (msg.charAt(0) == 'C') {
                System.out.println("Coin Update");
                return "C";
            } else if (msg.charAt(0) == 'S') {
                System.out.println("Start Update");
                return "S";
            } else if (msg.charAt(0) == 'I') {
                System.out.println("Initial Update");
                return "I";
            } else if (msg.charAt(0) == 'C' && msg.charAt(1) == 'E') {
                return "Attack";
            } else {
                System.out.println("Error");
                return "NULL";
            }

        } else {

            System.out.println("No reply from the server");
            return "NO REPLY";
        }
    }

    public String[][] buildMap(int size) {

        String map[][] = new String[size][size];
        
        for (int t = 0; t < size; t++) {
            for (int s = 0; s < size; s++) {
                map[t][s]="N";
            }
        }
        return map;
    }

}
