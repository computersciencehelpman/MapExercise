import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapPractice {

    public static void main(String[] args) {
        String fileName = "PokerHands.csv";
       Map<String, Integer> flush;
        MapPractice.ReadPokerHands(fileName);
       
       
    }

    public static Map<String, Integer> ReadPokerHands(String fileName) {
        Map<String, Integer> flush = new HashMap<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] playersArray = line.split(",");
    
                String playerName = playersArray[0];
                String hand = playersArray[1];
                if (hand.equalsIgnoreCase("FLUSH")) {
                    // Update the flush count for the current player
                    flush.put(playerName, flush.getOrDefault(playerName, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(flush);
        
   
        
         //Write the flush count to a new CSV file
        try (FileWriter writer = new FileWriter(fileName)) {
          
        	  // create new BufferedWriter for the output file
    	BufferedWriter bf = new BufferedWriter(new FileWriter(fileName));
        	
        	// loop through flush map and at each iteration write to file 
        	for(Map.Entry<String, Integer> flushes : flush.entrySet()) {
        		bf.write(flushes.getKey() + ":" + flushes.getValue());
        		
        		// new line
        		bf.newLine();
        	}
        	bf.flush();
        	bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      

        return flush;
    }
}
