
package eu.combal_weiss.victor.lifesimulator.main;

import eu.combal_weiss.victor.lifesimulator.onedeednacode.*;

public class WalkToHoleDna extends OneDeeDNA {
    
    public WalkToHoleDna(){
        
        code=new Code();
        
        code.add(new Assign(new Substraction(new Variable(Constant.ONE), new Variable(new Sum(Constant.ONE, Constant.ONE))),Constant.ZERO));
        
        Code codeThen=new Code();
        codeThen.add(new Assign( new Substraction(Constant.ZERO, Constant.ONE) , Constant.ZERO));
        
        Code codeElse=new Code();
        codeElse.add(new Assign(Constant.ONE,Constant.ZERO));
        
        // By adding this instruction to the code, we make the being do the opposite -> walk towards the hole
        code.add(new InstructionIf(new Variable(Constant.ZERO), codeThen, codeElse));
        
    }
    
    public WalkToHoleDna(Code c, Interpreter i){
        this();
    }
}
