/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

import eu.combal_weiss.victor.lifesimulator.main.C;

/**
 *
 * @author vic
 */
public class Assign implements OneDeeDnaInstruction {
    
    @Override
    public int getType(){
        return Constant.ASSIGN;
    }
    
    @Override
    public int getCodeSize(){
        return codeSize;
    }
    
    @Override
    public String printType(){
        return "Assign";
    }
    
    private OneDeeDnaValue value;
    private OneDeeDnaValue variableIndex;
    private int codeSize;
    
    public Assign(OneDeeDnaValue v, OneDeeDnaValue vI){
        value=v;
        variableIndex=vI;
        codeSize=1+value.getCodeSize()+variableIndex.getCodeSize();
    }
    
    public OneDeeDnaValue getValue(){
        return value;
    }
    
    public OneDeeDnaValue getVariableIndex(){
        return variableIndex;
    }
    
    @Override
    public Assign duplicate(){
        return new Assign(value.duplicate(),variableIndex.duplicate());
    }

    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[0]+=1;
        value.addNumberOfEachCodeNode(register);
        variableIndex.addNumberOfEachCodeNode(register);
    }
}
