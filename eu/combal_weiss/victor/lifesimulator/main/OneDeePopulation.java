/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.combal_weiss.victor.lifesimulator.main;

import java.io.PrintStream;
import java.util.LinkedList;

/**
 *
 * @author vic
 */
public class OneDeePopulation extends LinkedList<OneDeeBeing> {
    
    
    public void println(PrintStream out){
        print(out);
        out.println();
    }
    
    public void print(PrintStream out){
        for(OneDeeBeing being : this){
            being.println(out);
        }
    }
    
}
