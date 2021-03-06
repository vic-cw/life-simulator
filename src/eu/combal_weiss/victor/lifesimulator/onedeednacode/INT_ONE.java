
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

public class INT_ONE implements OneDeeDnaValue {
    
    @Override
    public int getType(){
        return Constant.INT_ONE;
    }
    
    @Override
    public INT_ONE duplicate(){
        return Constant.ONE;
    }
    
    @Override
    public int getCodeSize(){
        return 1;
    }
    
    @Override
    public String printType(){
        return "INT_ONE";
    }

    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[4]++;
    }
    
}
