/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import OneDeeDNACode.Code;
import OneDeeDNACode.Interpreter;
import OneDeeDNACode.Randomizer;
import OneDeeDNACode.SampleCode;
import lifesimulator.C;

/**
 *
 * @author vic
 */
public class TestRandomizer {
    
    public static void main(String[] arg) throws Exception{
        
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
        
        Code mutantFromEasyWalking=codeEasyWalking.duplicate();
        Randomizer randomizer= new Randomizer();
        randomizer.randomize(mutantFromEasyWalking, 1);
        
        Code mutantFromDumbWalking=codeDumbWalking.duplicate();
        randomizer.randomize(mutantFromDumbWalking, 1);
        
        System.out.println();
        System.out.println("Code Mutant from Easy Walking : ");
        System.out.println();
        
        interpreter.println(mutantFromEasyWalking, System.out);
        
        System.out.println();
        System.out.println("Code Mutant from Dumb Walking : ");
        System.out.println();
        
        interpreter.println(mutantFromDumbWalking, System.out);
        
    }
    
}
