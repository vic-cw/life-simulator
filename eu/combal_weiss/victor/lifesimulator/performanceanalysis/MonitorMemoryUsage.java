/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.combal_weiss.victor.lifesimulator.performanceanalysis;

import eu.combal_weiss.victor.lifesimulator.onedeednacode.LanguageImplementationException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import eu.combal_weiss.victor.lifesimulator.main.C;
import eu.combal_weiss.victor.lifesimulator.main.OneDeeWorld;
import eu.combal_weiss.victor.lifesimulator.main.Species;

/**
 *
 * @author vic
 */
public class MonitorMemoryUsage {
    
    
    public static void main(String[] args) throws LanguageImplementationException, Exception {
        
        /**
        System.out.println("Scuse me sir, which address would you like to write ?");
        String filename=in.readLine(); */
        
        //++++++++++++++++++++++++++ 
        // Asking for parameters
        //++++++++++++++++++++++++++ 
        
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        
        
        System.out.println("Scuse me sir, how many worlds would you like to create ?");
        String worldsNumberString=in.readLine();
        int worldsNumber=new Integer(worldsNumberString).intValue();
        System.out.println("Scuse me sir, how many iterations would you like to do with each world ?");
        String iterationsNumberString=in.readLine();
        int iterationsNumber=new Integer(iterationsNumberString).intValue();
        System.out.println("Ok sir, thank you");
        
        String filename=String.valueOf(System.currentTimeMillis())+".csv";
        PrintWriter out=new PrintWriter(new FileWriter(filename));
        Runtime runtime=Runtime.getRuntime();
        
        OneDeeWorld world;
        
        
        //++++++++++++++++++++++++++ 
        // Printing column headers
        //++++++++++++++++++++++++++ 
        
        out.print("Date,Number of living beings, Number of living species, Life count, Total number of Species, Memory used");

        if(C.memoryCalculations)
            out.print(", Number of Positions, Geographical range");

        if(C.memoryCalculations)
            out.print(", Average species string length");
        
        if(C.memoryCalculations)
            out.print(", Average number of Assign node, Average number of Define node, "
                + "Average number of If node, Average number of While node, Average number of 0 or 1 node,"
                + " Average number of Sum or Substraction node, Average number of Variable node");
        
        out.print(", Number of extinct species");
        
        out.println();
        
        
        //++++++++++++++++++++++++++ 
        // Iterating worlds
        //++++++++++++++++++++++++++ 
        
        for(int i=0; i<worldsNumber; i++){
            world=createWorld();
            System.gc();
            HashMap<Species, int[]> registerForSpecies;
            if(C.memoryCalculations)
                registerForSpecies=new HashMap<Species, int[]>();
            
            for(int j=0; j<iterationsNumber; j++){
                System.out.print("Iterating date "+j+"... ");
                world.iterate();
                System.gc();
                out.print(world.getDate() + "," + world.getNumberOfLivingBeings() + "," + world.getNumberOfLivingSpecies()
                        + "," + world.getLifeCount() + "," +world.getTotalNumberOfSpecies() + "," + (runtime.totalMemory()-runtime.freeMemory())+",");
                
                if(C.memoryCalculations)
                    out.print(world.getNumberOfOccupiedPositions()+","+world.getGeographicalRange()+",");
                
                if(C.memoryCalculations)
                    out.print(world.getAverageSpeciesStringLength()+",");
                
                if(C.memoryCalculations){
                    int[] numbersOfCodeNodes=world.getAverageNumberOfCodeNodePerBeing(registerForSpecies);
                    int intPart;
                    int decimalPart;
                    for(int k=0; k<numbersOfCodeNodes.length; k++){
                        intPart=numbersOfCodeNodes[k]/100;
                        decimalPart=numbersOfCodeNodes[k]-(intPart*100);
                        out.print(intPart+"."+decimalPart+",");
                    }
                }
                
                out.print(world.getNumberOfExtinctSpecies()+",");
                
                out.println();
                System.out.println("Finished.");
            }
            System.out.println("World "+i+" finished");
            System.gc();
        }
        out.close();
        System.out.println("Ok sir I have finished. You can find your results in the file \""+filename+"\". Cumreaplie");
    }
    
    
    private static OneDeeWorld createWorld() throws Exception{
        int[] initialPopulation= new int[30];
        for(int i=0; i<initialPopulation.length; i++){
            if(i<initialPopulation.length/2) initialPopulation[i]=0; else initialPopulation[i]=4;
        }
        
        return new OneDeeWorld(2, C.defaultMaxNumberOfLoops, 50, 3, initialPopulation);
    }
}

/*
 * Empirical information I need :
 * - Number of positions occupied and range of positions
 * - Average length of species code
 * - 
 */
