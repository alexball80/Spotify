package spotify.bilateral;

/**
 *
 * @author Alex Ball
 * mail: alexball80@gmail.com
 * 
 */

import java.io.*;
import java.util.ArrayList;

public class Bilateral {

    private File data;  
    
    public Bilateral(String sou){ 
        
        data = new File(sou);
        
    }
    
    public void reduceList() { 

        BufferedReader br = null;
        ArrayList<String> person = new ArrayList<String>();
        ArrayList<String> workWith = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
            
        try{ 
            
            br = new BufferedReader(new FileReader(data));
            String str;
            
            while ((str = br.readLine()) != null){
                String[] el = str.split(" ");
                int i = person.indexOf(el[0]);
                if(i != -1) {
                    String wW = workWith.get(i) + ',' + el[1];
                    workWith.set(i, wW);
                }else{
                    person.add(el[0]);
                    workWith.add(el[1]);
                }
            }

        } catch (IOException e) {}

        finally{ 
            
            try{ 
                
                if (br != null)
                br.close();
                
            } catch (IOException e) {}        
        }
        
        System.out.println("Ok, your friend is 1009, so the result is: ");
        
        for(int k = 0; k < person.size(); k++){
            
            String[] howMany = workWith.get(k).split(","); 
            if(howMany.length > 1){
                result.add(person.get(k));
            }
            else if (!result.contains(workWith.get(k))){
                if(person.get(k).contains("1009")){ //Check if is my friend
                    result.add(person.get(k));
                }else{
                    result.add(workWith.get(k));
                }
            }
            
        }
        
        for(int r = 0; r < result.size(); r++){
            if(result.get(r).contains("1009")){
                System.out.println("Goooood my friend: "+result.get(r)+" is in the list");
            }else{
                System.out.println(result.get(r)+" is in the list");
            }
        }      
        
    }
    
    public static void main(String args[]){
        
        String sou = "/../src/spotify/bilateral/data.rtf";
        Bilateral list = new Bilateral(sou);
        list.reduceList();
       
    }
}
