/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OneDeeDNACode;

import lifesimulator.C;


/**
 *
 * @author vic
 */
public class InstructionIf implements OneDeeDnaInstruction {
    
    @Override
    public int getType(){
        return Constant.IF;
    }
    
    @Override
    public String printType(){
        return "InstructionIf";
    }
    
    private OneDeeDnaValue condition;
    private Code codeThen;
    private Code codeElse;
    
    public InstructionIf(OneDeeDnaValue c, Code cThen, Code cElse){
        condition=c;
        codeThen=cThen;
        codeElse=cElse;
    }
    
    public OneDeeDnaValue getCondition(){
        return condition;
    }
    
    public Code getCodeThen(){
        return codeThen;
    }
    
    public Code getCodeElse(){
        return codeElse;
    }
    
    @Override
    public InstructionIf duplicate(){
        return new InstructionIf(condition.duplicate(), codeThen.duplicate(), codeElse.duplicate());
    }
    
    @Override
    public int getCodeSize(){
        return 1+condition.getCodeSize()+codeThen.getCodeSize()+codeElse.getCodeSize();
    }

    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[2]+=1;
        condition.addNumberOfEachCodeNode(register);
        for(OneDeeDnaInstruction inst : codeThen){
            inst.addNumberOfEachCodeNode(register);
        }
        for(OneDeeDnaInstruction inst : codeElse){
            inst.addNumberOfEachCodeNode(register);
        }
    }
}
