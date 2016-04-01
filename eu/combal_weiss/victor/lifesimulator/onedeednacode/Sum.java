
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

public class Sum implements OneDeeDnaValue {
    
    @Override
    public int getType(){
        return Constant.SUM;
    }
    
    private OneDeeDnaValue operand1;
    private OneDeeDnaValue operand2;
    
    public OneDeeDnaValue getOperand1(){
        return operand1;
    }
    
    public OneDeeDnaValue getOperand2(){
        return operand2;
    }
    
    public Sum(OneDeeDnaValue x, OneDeeDnaValue y){
        operand1=x;
        operand2=y;
    }
    
    @Override
    public Sum duplicate(){
        return new Sum(operand1.duplicate(), operand2.duplicate());
    }
    
    @Override
    public int getCodeSize(){
        return 1+operand1.getCodeSize()+operand2.getCodeSize();
    }
    
    @Override
    public String printType(){
        return "Sum";
    }
    
    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[5]++;
        operand1.addNumberOfEachCodeNode(register);
        operand2.addNumberOfEachCodeNode(register);
    }
    
}
