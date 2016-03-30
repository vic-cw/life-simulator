/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author vic
 */
public class SwitchTest {
    
    public static void main(String[] arg){
        
        switch(coucou()){
            case 0 :
                System.out.println("this is 0");
                break;
            case 1 :
                System.out.println("this is 1");
                break;
        }
        
        
        
    }
    
    public static int coucou(){
        
        System.out.println("coucou");
        return 1;
    }
    
}
