
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

public class Variable implements OneDeeDnaValue {
    
    @Override
    public int getType(){
        return Constant.VARIABLE;
    }
    
    @Override
    public String printType(){
        return "Variable";
    }
    
    
    private OneDeeDnaValue index;
    
    public OneDeeDnaValue getIndex(){
        return index;
    }
    
    public Variable(OneDeeDnaValue i){
        index=i;
    }
    
    @Override
    public Variable duplicate(){
        return new Variable(index.duplicate());
    }
    
    @Override
    public int getCodeSize(){
        return 1+index.getCodeSize();
    }
    
    
    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[6]++;
        index.addNumberOfEachCodeNode(register);
    }
}
