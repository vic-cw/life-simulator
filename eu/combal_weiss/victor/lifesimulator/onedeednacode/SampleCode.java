
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

import java.util.LinkedList;
import eu.combal_weiss.victor.lifesimulator.main.C;

public class SampleCode {
    
    
    public static void calculateDistanceToHole(Code code){
        
        // This is the code for someone just walking away from the hole
        code.add(new Assign(new Substraction(new Variable(Constant.ONE), new Variable(new Sum(Constant.ONE, Constant.ONE))),Constant.ZERO));
        
    }
    
    public static void codeEasyWalking(Code code){
        calculateDistanceToHole(code);
    }
    
    public static void codeDumbWalking(Code code){
        
        calculateDistanceToHole(code);
        
        Code codeThen=new Code();
        codeThen.add(new Assign( new Substraction(Constant.ZERO, Constant.ONE) , Constant.ZERO));
        
        Code codeElse=new Code();
        codeElse.add(new Assign(Constant.ONE, Constant.ZERO));
        
        // By adding this instruction to the code, we make the being do the opposite -> walk towards the hole
        code.add(new InstructionIf(new Variable(Constant.ZERO), codeThen, codeElse));
        
    }
    
    public static void codeLazyWalking(Code code){
        
        calculateDistanceToHole(code);
        
        //We will code here that if the distance between hole and being is more than 5, then no need to move
        OneDeeDnaValue distanceToHole=new Variable(Constant.ZERO);
    }
    
}
