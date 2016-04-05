
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

public class Constant {
    
    // Instruction types 
    public static final int ASSIGN=0;
    public static final int DEFINE=1;
    public static final int IF=2;
    public static final int WHILE=3;
    
    // Value types
    public static final int INT_ZERO=100;
    public static final int INT_ONE=101;
    public static final int SUM=102;
    public static final int SUBSTRACTION=103;
    public static final int VARIABLE=104;
    
    // Mutation types
    public static final int MUTATION_DELETION=1000;
    public static final int MUTATION_MODIFICATION=1001;
    public static final int MUTATION_ADDITION=1002;
    
    public static final INT_ONE ONE=new INT_ONE();
    public static final INT_ZERO ZERO=new INT_ZERO();
    
}
