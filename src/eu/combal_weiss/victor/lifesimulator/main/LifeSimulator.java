
package eu.combal_weiss.victor.lifesimulator.main;


import eu.combal_weiss.victor.lifesimulator.onedeednacode.LanguageImplementationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LifeSimulator {

    public static void main(String[] args) throws LanguageImplementationException, Exception {
        int[] initialPopulation= new int[30];
        for(int i=0; i<initialPopulation.length; i++){
            if(i<initialPopulation.length/2) initialPopulation[i]=0; else initialPopulation[i]=4;
        }
        
        OneDeeWorld world=new OneDeeWorld(2, C.defaultMaxNumberOfLoops, 50, 3, initialPopulation);
        
        textInterface(world);
            
    }
    
    public static void textInterface(OneDeeWorld world) throws IOException, LanguageImplementationException, Exception{
        
        System.out.println("---- WELCOME TO LIFE SIMULATOR ----\n");
        
        System.out.println("Warning : Computing time increases non-linearly with age of the world."+"\n"+
                "You are advised to proceed progressively, starting with less than 20 iterations.\n");
        
        System.out.println("For description of available commands, type \"help\" or \"h\".\n");
        
        System.out.println("Your world is created. How can I help you ?");
        
        String input=new String();
        String[] elementsOfInput;
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(!input.equals("quit")){
            System.out.println();
            System.out.print("> ");
            input=in.readLine();
            if(input.equals("quit")){
                System.out.println("Bye bye :)");
                break;
            }
            if(input.equals("display world")){
                world.displayWorld(System.out);
                continue;
            }
            if(input.equals("help") || input.equals("h") ||input.equals("-h")){
                displayTextInterfaceHelp();
                continue;
            }
            
            if(input.equals("iterate once") || input.equals("iterate")){
                System.out.println("Iterating date "+(world.getDate()+1)+"...");
                world.iterate();
                System.out.println("Iteration finished.");
                continue;
            }
            if(input.startsWith("iterate")){
                elementsOfInput=input.split(" ");
                for(int i=0; i<(new Integer(elementsOfInput[1]).intValue()); i++){
                    System.out.println("Iterating date "+(world.getDate()+1)+"...");
                    world.iterate();
                    System.out.println("Iteration finished.");
                }
                continue;
            }
            if(input.equals("display DNA composition") || input.equals("display dna composition")){
                int[] register=world.getAverageNumberOfCodeNodePerBeing(null);
        
                System.out.println("Instruction Assign : \t"+register[0]);
                System.out.println("Instruction Define : \t"+register[1]);
                System.out.println("Instruction If : \t"+register[2]);
                System.out.println("Instruction While : \t"+register[3]);
                System.out.println("Value 0 or 1 : \t\t"+register[4]);
                System.out.println("Value Sum or Sub : \t"+register[5]);
                System.out.println("Value Variable : \t"+register[6]);
                continue;
            }
            if(input.startsWith("display")){
                elementsOfInput=input.split(" ");
                if(elementsOfInput.length<2){
                    world.displayWorld(System.out);
                    continue;
                }
                if(elementsOfInput[1].equals("being")){
                    if(elementsOfInput.length<3){
                        System.out.println("Please specify the id number of the being you would like to display");
                        continue;
                    }
                    world.displayBeing(new Integer(elementsOfInput[2]), System.out);
                    continue;
                }
                if(elementsOfInput[1].equals("population")){
                    world.displayPopulation(System.out);
                    continue;
                }
                if(elementsOfInput[1].equals("date")){
                    System.out.print("Date of today : ");
                    System.out.println(world.getDate());
                    continue;
                }
                if(elementsOfInput[1].equals("species")){
                    if(elementsOfInput.length<3){
                        System.out.println("Please specify the code of the species you would like to display");
                        continue;
                    }
                    if(elementsOfInput[2].equals("all"))
                        world.displaySpeciesPopulation(System.out);
                    else
                        world.displaySpeciesCompletely(elementsOfInput[2], System.out);
                    continue;
                }
                if(elementsOfInput[1].equals("number")){
                    if(elementsOfInput.length<4 || !elementsOfInput[2].equals("of")){
                        System.out.println("Please specify what you would like to display in the shape \"Number of ...\" ");
                        continue;
                    }
                    if(elementsOfInput[3].equals("beings")){
                        System.out.print("Number of beings today : ");
                        System.out.println(world.getNumberOfLivingBeings());
                        continue;
                    }
                    
                    if(elementsOfInput[3].equals("species")){
                        System.out.print("Number of species living today : ");
                        System.out.println(world.getNumberOfLivingSpecies());
                        continue;
                    }
                }
                System.out.println("Command not recognized, please try again");
                continue;
            }
            else
                System.out.println("Command not recognized, please try again");
            
        }
    }
    
    public static void doXIterations(OneDeeWorld world, int iterationsNumber) throws LanguageImplementationException, Exception{
        world.displayWorld(System.out);
            
        for(int t=0;t<iterationsNumber;t++){
            world.iterate();
            world.displayWorld(System.out);
        }
    }

    private static void displayTextInterfaceHelp() {
        System.out.println("Text interface Help : ");
        
        System.out.println("\n\"quit\" : quit the program ");
        
        System.out.println("\n\"display date\" : display the current date ");
        
        System.out.println("\n\"display\" or \"display world\" : display all characteristics of the world to date ");
        System.out.println("\"display population\" : display living beings ");
        System.out.println("\"display number of beings\" : display number of living beings ");
        System.out.println("\"display being x\" : display the living being with id equal to x ");
        
        System.out.println("\n\"display species all\" : display all living species ");
        System.out.println("\"display number of species\" : display number of living species ");
        System.out.println("\"display species x-x-x...\" : display the species with code x-x-x ");
        
        System.out.println("\n\"iterate\" or \"iterate once\" : iterate the world once ");
        
        System.out.println("\n\"display DNA composition\" : display total number of each "+
                "type of DNA code element across all living beings");
    }
}
