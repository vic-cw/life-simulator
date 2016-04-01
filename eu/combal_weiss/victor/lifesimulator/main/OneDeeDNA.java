
package eu.combal_weiss.victor.lifesimulator.main;

import eu.combal_weiss.victor.lifesimulator.onedeednacode.Code;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.Interpreter;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.OneDeeDnaInstruction;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.SampleCode;
import java.io.PrintStream;
import java.util.Random;

public class OneDeeDNA {
    
    protected Code code;
    
    
    
    public OneDeeDNA(){
        code=new Code();
        Random random=new Random();
        if(random.nextBoolean()){
            SampleCode.codeEasyWalking(code);
        }
        else {
            SampleCode.codeDumbWalking(code);
        }
    }
    
    public OneDeeDNA(Code c){
        code=c;
    }
    
    public OneDeeDNA duplicate(){
        OneDeeDNA copy=new OneDeeDNA();
        copy.code=new Code();
        for(OneDeeDnaInstruction inst : code){
            copy.code.add(inst.duplicate());
        }
        return copy;
    }
    
    public Code getCode(){
        return code;
    }
    
    // TODO : code print function for DNA
    public void print(PrintStream out){
        out.print("DNA : ");
        if(code.size()>1){
            out.print("dumb");
        } else
            out.print("intelligent");
        out.print(", ");
        
    }
    
    public void println(PrintStream out){
        print(out);
        out.println();
    }
    
}


