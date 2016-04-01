
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

import java.util.LinkedList;

public class Code extends LinkedList<OneDeeDnaInstruction>{
    
    
    public Code duplicate(){
        Code copy=new Code();
        for(OneDeeDnaInstruction inst : this){
            copy.add(inst.duplicate());
        }
        return copy;
    }
    
    public int getCodeSize(){
        int size=0;
        for(OneDeeDnaInstruction inst : this){
            size+=inst.getCodeSize();
        }
        return size;
    }
    
}
