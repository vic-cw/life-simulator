/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifesimulator;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author vic
 */
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
    
    /* -> Code to find a specie by specifying her code, in case we don't have a register for that
    public Species getSpecies(String code){
        String[] indexStringList=code.split("-");
        
        LinkedList<Species> speciesSet=this.get(null);
        String speciesBeingSearched="";
        for(int i=0; i<indexStringList.length; i++){
            speciesBeingSearched=speciesBeingSearched.concat("-").concat(indexStringList[i]);
        }
    }*/
    
    
    
}
