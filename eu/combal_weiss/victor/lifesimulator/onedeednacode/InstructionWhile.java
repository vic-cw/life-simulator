
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

import eu.combal_weiss.victor.lifesimulator.main.C;

public class InstructionWhile implements OneDeeDnaInstruction {
    
    private OneDeeDnaValue condition;
    private Code code;
    
    @Override
    public int getType(){
        return Constant.WHILE;
    }
    
    @Override
    public String printType(){
        return "InstructionWhile";
    }
    
    public InstructionWhile(OneDeeDnaValue cond, Code cod){
        condition=cond;
        code=cod;
    }
    
    public OneDeeDnaValue getCondition(){
        return condition;
    }
    
    public Code getCode(){
        return code;
    }
    
    @Override
    public InstructionWhile duplicate(){
        return new InstructionWhile(condition.duplicate(), code.duplicate());
    }
    
    @Override
    public int getCodeSize(){
        return 1+condition.getCodeSize()+code.getCodeSize();
    }

    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[3]+=1;
        condition.addNumberOfEachCodeNode(register);
        for(OneDeeDnaInstruction inst : code){
            inst.addNumberOfEachCodeNode(register);
        }
    }
    
}
