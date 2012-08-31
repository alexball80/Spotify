package spotify.lottery;

/**
 *
 * @author Alex Ball
 * mail: alexball80@gmail.com
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Lottery {

    double probability = 0.0;
    String in;

    public void go(String str) {

        in = str;

        String[] input = in.split(" ");

        int partecipant = Integer.parseInt(input[0]);
        int draws = Integer.parseInt(input[1]);
        int tickets = Integer.parseInt(input[2]);
        int group = Integer.parseInt(input[3]);

        //coef. of min winner in my gruop for all group
        int c = (int) Math.ceil((1.0 * group) / tickets);
        
        int all = partecipant;
        int others = partecipant - group;
        
        //Calc the probability of looser
        for (int k = 0; k < c; k++) {
            probability +=  bin(others, draws - k) * bin(group, k) / bin(all, draws);
        }

        System.out.print("3)* --- The result for " + str + ": ");
        //1- looser probability give the prob of winners
        System.out.format("%.10f%n", (1 - probability));     
        
    }

     
    public double bin(int a, int b) {
        
        if (b < 0 || b > a) {
            return 0;
        }
        
        double result = 1.0;
        
        for (int i = 1; i < b + 1; i++) {       
            result *= (a - (b - i));
            result /= i;           
        }
        
        return result;

    }


    public void ask() {

        System.out.print("1) - Test a case: ");
        
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);
        String str = new String();

        try {
            str = input.readLine();
        } catch (IOException e) {
            System.out.println("OPS, we have an error: " + e.getMessage());
            System.exit(-1);
        }

        System.out.println("2) - Let's go calculate the probability for: " + str);

        Lottery lucky = new Lottery();
        lucky.go(str);
        lucky.ask();

    }

    public static void main(String[] args) {

        Lottery lucky = new Lottery();
        System.out.println("Please insert numbers separed by single space: Partecipant | Draws | Tickets | Group -> ex. 100 10 2 1");
        lucky.ask();

    }
}

