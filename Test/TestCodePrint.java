/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import OneDeeDNACode.Code;
import OneDeeDNACode.Interpreter;
import OneDeeDNACode.SampleCode;
import lifesimulator.C;

/**
 *
 * @author vic
 */
public class TestCodePrint {
    
    public static void main(String[] arg){
        
        Code codeEasyWalking = new Code();
        SampleCode.codeEasyWalking(codeEasyWalking);
        
        Code codeDumbWalking = new Code();
        SampleCode.codeDumbWalking(codeDumbWalking);
        
        Interpreter interpreter=new Interpreter(C.defaultMaxNumberOfLoops);
        
        
        System.out.println("Code Easy Walking : ");
        System.out.println();
        
        interpreter.println(codeEasyWalking, System.out);
        
        System.out.println();
        System.out.println("Code Dumb Walking : ");
        System.out.println();
        
        interpreter.println(codeDumbWalking, System.out);
        
    }
    
}
