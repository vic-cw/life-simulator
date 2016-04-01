
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

public interface OneDeeDnaInstruction {
    
    public int getType();
    
    public OneDeeDnaInstruction duplicate();
    
    public int getCodeSize();
    
    public String printType();

    public void addNumberOfEachCodeNode(int[] register);
}

 
