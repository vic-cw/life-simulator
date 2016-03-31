/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

/**
 *
 * @author vic
 */
public class Substraction implements OneDeeDnaValue {
    
    private OneDeeDnaValue operand1;
    private OneDeeDnaValue operand2;
    
    @Override
    public int getType(){
        return Constant.SUBSTRACTION;
    }
    
    public Substraction(OneDeeDnaValue x, OneDeeDnaValue y){
        operand1=x;
        operand2=y;
    }
    
    public OneDeeDnaValue getOperand1(){
        return operand1;
    }
    
    public OneDeeDnaValue getOperand2(){
        return operand2;
    }
    
    @Override
    public Substraction duplicate(){
        return new Substraction(operand1.duplicate(), operand2.duplicate());
    }
    
    @Override
    public int getCodeSize(){
        return 1+operand1.getCodeSize()+operand2.getCodeSize();
    }
    
    @Override
    public String printType(){
        return "Substraction";
    }
    
    
    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[5]++;
        operand1.addNumberOfEachCodeNode(register);
        operand2.addNumberOfEachCodeNode(register);
    }
}
