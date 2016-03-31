/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.combal_weiss.victor.lifesimulator.main;

/**
 *
 * @author vic
 */
public class C {
    
    public static final boolean debug=false;
    public static final boolean memoryCalculations=false;
    
    public static final int defaultMaxNumberOfLoops=100;
    
    public static void append(String s){
        System.out.print(s);
    }
    
    public static void appendln(String s){
        System.out.println(s);
    }
    
    public static void append(int i){
        System.out.print(i);
    }
    
    public static void appendln(int i){
        System.out.println(i);
    }
    
    public static void print(String s){
        printTab();
        append(s);
    }
    
    public static void println(String s){
        printTab();
        appendln(s);
    }
    
    public static void print(int i){
        printTab();
        append(i);
    }
    
    public static void println(int i){
        printTab();
        appendln(i);
    }
    
    public static int level=0;
    
    private static void printTab(){
        for(int i=0; i<level; i++){
            System.out.print("   ");
        }
    }
    
    public static void add(int[] receiver, int[] data){
        if(data.length<receiver.length)
            return;
        for(int i=0; i<receiver.length; i++){
            receiver[i]=receiver[i]+data[i];
        }
    }
    
}
