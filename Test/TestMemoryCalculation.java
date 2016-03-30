/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.HashMap;
import lifesimulator.C;
import lifesimulator.OneDeeWorld;
import lifesimulator.Species;

/**
 *
 * @author vic
 */
public class TestMemoryCalculation {
    
    public static void main(String[] args) throws Exception{
        
        int[] initialPopulation= new int[30];
        for(int i=0; i<initialPopulation.length; i++){
            if(i<initialPopulation.length/2) initialPopulation[i]=0; else initialPopulation[i]=4;
        }
        
        OneDeeWorld world=new OneDeeWorld(2, C.defaultMaxNumberOfLoops, 50, 3, initialPopulation);
        
        world.displayWorld(System.out);
        
        System.out.println("Average number of code nodes :");
        
        int[] register=world.getAverageNumberOfCodeNodePerBeing(new HashMap<Species, int[]>());
        
        System.out.println("Instruction Assign : "+register[0]);
        System.out.println("Instruction Define : "+register[1]);
        System.out.println("Instruction If : "+register[2]);
        System.out.println("Instruction While : "+register[3]);
        System.out.println("Value 0 or 1 : "+register[4]);
        System.out.println("Value Sum or Sub : "+register[5]);
        System.out.println("Value Variable : "+register[6]);
    }
}
