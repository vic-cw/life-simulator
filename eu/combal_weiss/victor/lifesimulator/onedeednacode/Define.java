
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

import eu.combal_weiss.victor.lifesimulator.main.C;

public class Define implements OneDeeDnaInstruction {
    
    private OneDeeDnaValue value;
    
    @Override
    public int getType(){
        return Constant.DEFINE;
    }
    
    @Override
    public String printType(){
        return "Define";
    }
    
    public Define(OneDeeDnaValue v){
        value=v;
    }
    
    public OneDeeDnaValue getValue(){
        return value;
    }
    
    @Override
    public Define duplicate(){
        return new Define(value.duplicate());
    }
    
    @Override
    public int getCodeSize(){
        return 1+value.getCodeSize();
    }

    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[1]+=1;
        value.addNumberOfEachCodeNode(register);
    }
}
