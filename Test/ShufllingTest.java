/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author vic
 */
public class ShufllingTest {
    
    public static void main(String[] arg){
          
        
        LinkedList<Integer> shuffledOrder= new LinkedList<Integer>();
        
        for(int i=0; i<10; i++){
            shuffledOrder.add(new Integer(i));
        }
        Collections.shuffle(shuffledOrder);
        
        for(int i=0; i<10; i++){
            System.out.println(shuffledOrder.get(i));
        }
        
        
        
    }
    
}
