/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

/**
 *
 * @author vic
 */
public class INT_ZERO implements OneDeeDnaValue {
    
    @Override
    public int getType(){
        return Constant.INT_ZERO;
    }
    
    @Override
    public INT_ZERO duplicate(){
        return Constant.ZERO;
    }
    
    @Override
    public int getCodeSize(){
        return 1;
    }
    
    @Override
    public String printType(){
        return "INT_ZERO";
    }
    
    
    @Override
    public void addNumberOfEachCodeNode(int[] register) {
        register[4]++;
    }
}
