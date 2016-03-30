/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifesimulator;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author vic
 */
class SpeciesPopulationRegister extends HashMap<Species, OneDeePopulation>{
    
    private int totalPopulationSize;
    
    public SpeciesPopulationRegister(){
        super();
        totalPopulationSize=0;
    }
    
    public void registerBeing(OneDeeBeing being){
        totalPopulationSize++;
        if(!this.containsKey(being.species)){
            OneDeePopulation population=new OneDeePopulation();
            population.add(being);
            this.put(being.species, population);
        } else
            this.get(being.species).add(being);
    }
    
    public int getPopulationSize(Species s){
        if(!this.containsKey(s))
            return 0;
        return this.get(s).size();
    }
    
    public int getTotalPopulationSize(){
        return totalPopulationSize;
    }
    
    public void registerDeath(OneDeeBeing being){
        this.get(being.species).remove(being);
        totalPopulationSize--;
    }
    
    public OneDeePopulation getSpeciesPopulation(Species s){
        if(!this.containsKey(s))
            return null;
        return this.get(s);
    }
    
    public void println(SpeciesMutantsRegister reg, PrintStream out){
        print(reg, out);
        out.println();
    }
    
    public void print(final SpeciesMutantsRegister reg, PrintStream out){
        
        Species[] speciesList=this.keySet().toArray(new Species[1]);
        Arrays.sort(speciesList, new Comparator<Species>() {
            @Override
            public int compare(Species t, Species t1) {
                return getDescentPopulation(t1, reg)-getDescentPopulation(t, reg);
            }
        });
        out.print(speciesList.length);
        out.println(" species registered ; ");
        for(Species s : speciesList){
            if(getPopulationSize(s)==0)
                continue;
            displaySpecies(s, reg, out);
        }
    }
    
    public void displaySpecies(Species s, SpeciesMutantsRegister reg, PrintStream out){
        out.print("Species ");
        out.print(s.getCode());
        out.print(", population ");
        out.print(getPopulationSize(s));
        out.print(" beings, ie ");
        out.print((int)((100*getPopulationSize(s))/totalPopulationSize));
        out.print(" % of the population, and ");
        out.print((int)((100*getDescentPopulation(s, reg))/totalPopulationSize));
        out.print(" % of the population is made of descendance");
        out.println();
    }
    
    
    public int getDescentPopulation(Species s, SpeciesMutantsRegister reg){
        int population=getPopulationSize(s);
        if(!reg.containsKey(s))
            return population;
        for(Species mutant : reg.get(s)){
            population+=getDescentPopulation(mutant, reg);
        }
        return population;
    }
    
    
}
