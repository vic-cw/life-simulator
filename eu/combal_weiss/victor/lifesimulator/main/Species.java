
package eu.combal_weiss.victor.lifesimulator.main;

import eu.combal_weiss.victor.lifesimulator.onedeednacode.OneDeeDnaInstruction;

public class Species {

    private Species parent;
    private int mutationNumber;
    private OneDeeDNA dna;
    
    public Species(OneDeeWorld world, Species parentSpecies, OneDeeDNA myDna) throws Exception{
        parent=parentSpecies;
        mutationNumber=world.numberOfMutantSpeciesFrom(parentSpecies);
        world.registerNewSpecies(this);
        dna=myDna;
    }
    
    public Species(OneDeeWorld world, OneDeeDNA myDna) throws Exception{
        this(world, null, myDna);
    }
    
    public String getCode(){
        if(parent==null) return Integer.toString(mutationNumber);
        return parent.getCode().concat("-").concat(Integer.toString(mutationNumber));        
    }
    
    public Species getParent(){
        return parent;
    }
    
    public int getMutationNumber(){
        return mutationNumber;
    }
    
    public OneDeeDNA getDna(){
        return dna;
    }

    public int[] getNumberOfEachCodeNode() {
        int[] register=new int[7];
        for(OneDeeDnaInstruction inst : dna.code){
            inst.addNumberOfEachCodeNode(register);
        }
        return register;
    }
    
    
}
