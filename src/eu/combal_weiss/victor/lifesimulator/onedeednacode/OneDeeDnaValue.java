
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

public interface OneDeeDnaValue {
    
    public int getType();
    
    public OneDeeDnaValue duplicate();
    
    public int getCodeSize();
    
    public String printType();

    public void addNumberOfEachCodeNode(int[] register);
}
