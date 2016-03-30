/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OneDeeDNACode;

import java.util.LinkedList;
import lifesimulator.C;

/**
 *
 * @author vic
 * 
 * On suppose que le décodeur placera les variables suivantes dans la liste de variables :
 * 0 : la valeur de retour (négatif -> -1, zéro -> 0, positif -> +1)
 * 1 : position du being
 * 2 : position du trou
 * On va essayer d'implémenter le code du being intelligent qui s'éloigne du trou.
 */
public class SampleCode {
    
    
    
    
    public static void main(String[] args) throws LanguageImplementationException, InfiniteLoopDnaException{
        
        Code code=new Code();        
        VariableEnvironment varEnv=new VariableEnvironment();
        
        
        varEnv.add(null);
        varEnv.add(new Integer(-20));
        varEnv.add(new Integer(-11));
        
        codeDumbWalking(code);
        
        Interpreter interpreter=new Interpreter(C.defaultMaxNumberOfLoops);
        interpreter.execute(code, varEnv);
        
        System.out.print("Answer : ");
        System.out.println(varEnv.get(0).intValue());
        
    }
    
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
