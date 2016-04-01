
package eu.combal_weiss.victor.lifesimulator.main;

import eu.combal_weiss.victor.lifesimulator.onedeednacode.*;

public class WalkFromHoleDna extends OneDeeDNA {
    
    
    public WalkFromHoleDna(){
        
        code=new Code();
        
        code.add(new Assign(new Substraction(new Variable(Constant.ONE), new Variable(new Sum(Constant.ONE, Constant.ONE))),Constant.ZERO));
        
    }
    
    public WalkFromHoleDna(Code c, Interpreter i){
        this();
    }
}
