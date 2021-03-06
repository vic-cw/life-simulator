
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

import java.util.LinkedList;

public class VariableEnvironment extends LinkedList<Integer>{
    
    public boolean isEqualto(VariableEnvironment env){
        if(this.size()!=env.size()) return false;
        for(int i=0; i<env.size(); i++){
            if(this.get(i).intValue()!=env.get(i).intValue()) return false;
        }
        return true;
    }
    
    public VariableEnvironment duplicate(){
        VariableEnvironment env=new VariableEnvironment();
        for(int i=0; i<this.size(); i++){
            env.add(new Integer(this.get(i).intValue()));
        }
        return env;
    }
    
}