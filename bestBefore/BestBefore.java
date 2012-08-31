package spotify.bestBefore;

/**
 *
 * @author Alex Ball
 * mail: alexball80@gmail.com
 * 
 */

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BestBefore {
    
    private Date today = new Date();
    SimpleDateFormat YMD = new SimpleDateFormat("yyyy/MM/dd");
    private String date;
    int Y = Calendar.getInstance().get(Calendar.YEAR);
    public String out;

    
    ArrayList<Long> possibleDate = new ArrayList<Long>();

    
    public void calc(String str){
        
        date = str;
        
        String[] dt = date.split("/");
        
        
        for(int i = 0; i < 3; i++){
            
            int y = Integer.parseInt(dt[i]);
            
            //If the year is min than 999 add 2000 else is up to 2000
            if(y <= 999) y = y + 2000;
            
            //If the year is min than actual year don't proced
            //Loop on possible combination of year . month and day
            if(y >= Y){
            
                String m = Integer.toString(Integer.parseInt(dt[1])); 
                String d = Integer.toString(Integer.parseInt(dt[2]));

                if(i == 1){
                    m = Integer.toString(Integer.parseInt(dt[0]));
                    d = Integer.toString(Integer.parseInt(dt[2]));
                }
                if(i == 2){
                    m = Integer.toString(Integer.parseInt(dt[0]));
                    d = Integer.toString(Integer.parseInt(dt[1]));
                }       

                isValidDate(Integer.toString(y)+'/'+m+'/'+d);
                isValidDate(Integer.toString(y)+'/'+d+'/'+m);
            }
            
        }
        
        if(possibleDate.size() > 0){
        
            //Sort date from min to max
            Collections.sort(possibleDate);

            //Remove date before today
            for(int j = possibleDate.size() - 1; j >= 0; j--){
                if(possibleDate.get(j) < today.getTime()){
                    possibleDate.remove(j);
                }
            }

            Date result;
            result = new Date(possibleDate.get(0));
            out = new SimpleDateFormat("yyyy-MM-dd").format(result);
            System.out.println(out);
        
        }else{
            
            System.err.println(date+" is illegal");
            
        }

        
    }
    
    //Check if date is valid
    public void isValidDate(String dat) {
     
        try {
            YMD.setLenient(false);
            Date dt = YMD.parse(dat);
            possibleDate.add(dt.getTime());
        } catch (Exception e) {
            System.out.println(dat + " is not valid");
        }        
        
    }
    
    public void ask() {

        System.out.print("1) - Test a case: ");
        
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);
        String str = new String();

        BestBefore beBe = new BestBefore();
        
        try {
            str = input.readLine();
            if(str.split("/").length <= 2){
                System.out.println("Bad format date, try again!");
                beBe.ask();
            }
        } catch (IOException e) {
            System.out.println("OPS, we have an error: " + e.getMessage());
            System.exit(-1);
        }

        System.out.println("2) - Let's go calculate the best before date for: " + str);
        
        beBe.calc(str);
        beBe.ask();

    }

    
    public static void main(String args[]){
        
        System.out.println("Please insert numbers separed by single slash: ex. 01/9/2053");
        String date = "01/9/2053";
        BestBefore beBe = new BestBefore();
        beBe.calc(date);
        beBe.ask();
        
    }
    
}