/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author vic
 */
public class TestingUnaryIncrement {
    
    
    public static void main(String[] args){
        int position=0;
        print(position,position++);
        System.out.println("Position : "+position);
    }
    
    private static void print(int a, int b){
        System.out.println("Int a : "+a+", int b : "+b);
    }
}
