/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OneDeeDNACode;

/**
 *
 * @author vic
 */
public interface OneDeeDnaValue {
    
    public int getType();
    
    public OneDeeDnaValue duplicate();
    
    public int getCodeSize();
    
    public String printType();

    public void addNumberOfEachCodeNode(int[] register);
}
