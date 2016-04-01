
package eu.combal_weiss.victor.lifesimulator.main;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;

public class SpeciesMutantsRegister extends HashMap<Species, LinkedList<Species>> {
    
    public SpeciesMutantsRegister(){
        super();
        this.put(null, new LinkedList<Species>());
    }
    
    public int getNumberOfMutations(Species s){
        return this.get(s).size();
    }
    
    public void addMutation(Species parent, Species mutant) {
        if(!this.containsKey(parent)){
            LinkedList<Species> liste=new LinkedList<Species>();
            liste.add(mutant);
            this.put(parent, liste);
            
        } else
            this.get(parent).add(mutant);
    }
    
    public void println(PrintStream out){
        print(out);
        out.println();
    }
    
    public void print(PrintStream out){
        
        // @TODO : code the display function for mutants genealogy
    }
    
}
