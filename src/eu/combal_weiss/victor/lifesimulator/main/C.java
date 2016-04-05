
package eu.combal_weiss.victor.lifesimulator.main;

/**
 * Utility static class, comprising often used methods, with a short class name
 * for quick addition to code.
 * 
 * @author Victor Combal-Weiss <vic-cw@users.noreply.github.com>
 * @version 1.0
 */
public class C {
    
    /**
     * Flag to enable debug mode.
     * <p>
     * Set to true to enable output of debug information.
     * By default set to false.
     */
    public static final boolean debug=false;
    
    /**
     * Flag to enable detailed memory monitoring.
     * <p>
     * Set to true to enable calculation and output of detailed
     * composition of memory use when running memory monitoring.
     * By default set to false.
     * 
     * @see eu.combal_weiss.victor.lifesimulator.performanceanalysis.MonitorMemoryUsage
     */
    public static final boolean memoryCalculations=false;
    
    /**
     * Maximum iterations allowed for a single while loop in DNA.
     * <p>
     * If a being's DNA comprises a while loop, and execution
     * ends up requiring more than that number, interpretation of the being's
     * DNA is halted.
     * <p>
     * Default 100. 
     * 
     * @see eu.combal_weiss.victor.lifesimulator.onedeednacode.Interpreter#maximumNumberOfLoops
     * @see eu.combal_weiss.victor.lifesimulator.onedeednacode.Interpreter#execute(eu.combal_weiss.victor.lifesimulator.onedeednacode.Code, eu.combal_weiss.victor.lifesimulator.onedeednacode.VariableEnvironment) 
     */
    public static final int defaultMaxNumberOfLoops=100;
    
    
    /**
     * Prints a string to System.out without adding new line.
     * <p>
     * Shortcut method for System.out.print
     * 
     * @param s String to print
     */
    public static void append(String s){
        System.out.print(s);
    }
    
    
    /**
     * Prints a string to System.out and adds a new line.
     * <p>
     * Shortcut method for System.out.println
     * 
     * @param s String to print
     */
    public static void appendln(String s){
        System.out.println(s);
    }
    
    
    /**
     * Prints an int to System.out without adding new line.
     * <p>
     * Shortcut method for System.out.print
     * 
     * @param i Int to print
     */
    public static void append(int i){
        System.out.print(i);
    }
    
    
    /**
     * Prints an int to System.out and adds a new line.
     * <p>
     * Shortcut method for System.out.println
     * 
     * @param i Int to print
     */
    public static void appendln(int i){
        System.out.println(i);
    }
    
    /**
     * Prints string preceded by indentation, without new line.
     * <p>
     * Uses {@link #level} field to print spaces according to required indentation,
     * then prints string to System.out
     * 
     * @param s String to print
     * @see #level
     * @see #println(java.lang.String) 
     * @see #print(int)
     */
    public static void print(String s){
        printTab();
        append(s);
    }
    
    /**
     * Prints string preceded by indentation and followed by a new line.
     * <p>
     * Uses {@link #level} field to print spaces according to required indentation,
     * then prints string to System.out
     * 
     * @param s String to print
     * @see #level
     * @see #print(java.lang.String) 
     * @see #println(int)
     */
    public static void println(String s){
        printTab();
        appendln(s);
    }
    
    /**
     * Prints int preceded by indentation, without new line.
     * <p>
     * Uses {@link #level} field to print spaces according to required indentation,
     * then prints int to System.out
     * 
     * @param i Int to print
     * @see #level
     * @see #print(java.lang.String) 
     * @see #println(int)
     */
    public static void print(int i){
        printTab();
        append(i);
    }
    
    /**
     * Prints int preceded by indentation and followed by a new line.
     * <p>
     * Uses {@link #level} field to print spaces according to required indentation,
     * then prints int to System.out
     * 
     * @param i Int to print
     * @see #level
     * @see #println(java.lang.String) 
     * @see #print(int)
     */
    public static void println(int i){
        printTab();
        appendln(i);
    }
    
    /**
     * Indentation level for print and println.
     * <p>
     * Defines how many indentations will be added to the beginning of new lines
     * when calling print or println.
     * <p>
     * Initially set to 0.
     * 
     * @see #print(java.lang.String)
     * @see #print(int) 
     * @see #println(java.lang.String) 
     * @see #println(int) 
     */
    public static int level=0;
    
    /**
     * Prints spaces according to indentation level.
     * <p>
     * Refers to {@link #level} to output spaces as many times as is specified.
     * <p>
     * Doesn't add any new line, neither before nor after.
     * <br>
     * Used by {@link #print(java.lang.String)}, {@link #print(int)}, 
     * {@link #println(java.lang.String)}, {@link #println(int)}
     * 
     * @see #level
     * @see #print(java.lang.String) 
     * @see #print(int) 
     * @see #println(java.lang.String) 
     * @see #println(int) 
     */
    private static void printTab(){
        for(int i=0; i<level; i++){
            System.out.print("   ");
        }
    }
    
    /**
     * Adds values in {@code data} to {@code receiver}.
     * <p>
     * Array {@code receiver} is modified by adding to each element the corresponding
     * element in {@code data}.
     * <p>
     * Ex : {@code receiver[0]} is incremented by {@code data[0]}, 
     * {@code receiver[1]} is incremented by {@code data[1]}, etc
     * <p>
     * If {@code data}'s length is smaller than {@code receiver}, nothing happens.
     * 
     * @param receiver Array to increment
     * @param data Values by which to increment {@code receiver}
     */
    public static void add(int[] receiver, int[] data){
        if(data.length<receiver.length)
            return;
        for(int i=0; i<receiver.length; i++){
            receiver[i]=receiver[i]+data[i];
        }
    }
    
}
