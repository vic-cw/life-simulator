/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author vic
 */
public class TestTextInput {
    
    public static void main(String[] arg) throws IOException{
        String input=new String();
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(!input.equals("quit")){
            input=in.readLine();
            if(input.equals("quit")){
                System.out.println("Bye bye");
                break;
            }
            System.out.print("You have input : ");
            System.out.println(input);
        }
    }
    
}
