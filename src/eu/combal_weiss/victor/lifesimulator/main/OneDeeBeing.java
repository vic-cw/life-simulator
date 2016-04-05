
package eu.combal_weiss.victor.lifesimulator.main;

import eu.combal_weiss.victor.lifesimulator.onedeednacode.Code;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.InfiniteLoopDnaException;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.Interpreter;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.LanguageImplementationException;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.Randomizer;
import eu.combal_weiss.victor.lifesimulator.onedeednacode.VariableEnvironment;
import java.io.PrintStream;

public class OneDeeBeing {
    
private int id;
private int age;
private int position;
private int lastLunchDate;
private int ancestorId;
public Species species;
private OneDeeWorld world;
private boolean hasToDie;
private Interpreter interpreter;
    

/**
 * Constructor for a OneDeeBeing
 * 
 * @param pos the initial position of the being
 * @param w the world in which it is created
 * @param lifeCount the id of that being in the world
 * @param a the ancestor of that being. Will be null if has no parent.
 */
    protected OneDeeBeing(int pos, OneDeeWorld w, int lifeCount, OneDeeBeing a, Species s) throws Exception{
        if(w==null) throw new Exception("Problem : trying to create a being without a world");
        position=pos;
        lastLunchDate=w.getDate();
        age=0;
        world=w;
        id=lifeCount;
        species=s;
        hasToDie=false;
        interpreter=new Interpreter(w.getMaximumNumberOfLoops());
        
        if(a==null)
            ancestorId=-1;
        else 
            ancestorId=a.getId();
        
        // Initialisation du comportement dans le cas de pas de parent
        if(s==null)
            species=new Species(world, new OneDeeDNA());
        
    }
    
    public int getPosition(){
        return position;
    }
    
    public int getAge(){
        return age;
    }
    
    public int getLastLunchDate(){
        return lastLunchDate;
    }
        
    public int getId(){
        return id;
    }
    
    public boolean shouldDieNow(){
        return hasToDie;
    }
    
    private void setOlder(){
        age++;
    }
    
    
    // This function operates the change in the Being at each step
    // It is supposed to encompass natural changes such as aging, as well as decision making
    public void iterate() throws LanguageImplementationException, Exception{
        if(C.debug){
            C.print("Entering iterate function from being ");
            C.appendln(this.id);
        }
        takeAction();
        if(C.debug)
            C.println("Exiting takeAction function");
        setOlder();
        breed(50,20);
        if(C.debug){
            C.print("Exiting iterate function from being ");
            C.appendln(this.id);
        }
    }
    
    /** 
     * This is the decision process.
     */
    private void takeAction() throws LanguageImplementationException, Exception{
        
        if(C.debug){
            C.println("Entering takeAction function ");
        }
        
        // Creating the variable environment for DNA interpretation
        VariableEnvironment env=new VariableEnvironment();
        env.add(new Integer(0)); // place where to read response for position
        env.add(position);
        env.add(world.getHole());
        env.add(new Integer(1)); // place where to read response for eating or not
        
        // Interpreting the response of the DNA code
        if(C.debug)
            C.println("Starting to execute the DNA code and interpret it");
        try{
            interpreter.execute(species.getDna().getCode(), env);
            if(C.debug){
                C.println("we executed in the try");
            }
        } catch(InfiniteLoopDnaException e){
            if(C.debug)
                System.out.println(e.getMessage());
            hasToDie=true;
            return;
        } catch(IndexOutOfBoundsException e){
            hasToDie=true;
            return;
        } 
        if(C.debug){
            C.println("DNA Code execution and interpretation went fine");
        }
            
            
        
        
        // Transfering modifications on position variable
        int responsePosition=env.get(0);
        
        if(responsePosition>0)
            world.registerMove(position, ++position);
        
        else if(responsePosition<0) 
            world.registerMove(position, --position);
        
        
        // Transfering modifications on eating decision
        if(env.get(3)>0 && world.isThereFoodHere(position)){
            world.registerLunch(position);
            lastLunchDate=world.getDate();
        }
    }
    
    
    /**
     * This is the reproduction process. This creates children of the current being and adds them to the world.
     * the number of children could be set as a characteristics of the world, of the species, or could vary with randomness
     * For a first version, there is one child at each period, which has the same DNA
     * 
     */
    private void breed(int probabilityToBreed, int probabilityToMutate) throws Exception{
        
        
        if(C.debug){
            C.println("Entering breed function ");
        }
        
        
        // Introducing a probability under which the being doesn't breed.
        if(world.random.nextInt(100)<Math.abs(probabilityToBreed))
            return;
        
        int babyPosition;
        
        babyPosition=world.random.nextInt(5);
        
        if(world.random.nextInt(100)>Math.abs(probabilityToMutate)){
            world.addBeing(babyPosition, this, species);
        } else {
            Randomizer dnaRandomizer=new Randomizer();
            Code babyCode=species.getDna().getCode().duplicate();
            dnaRandomizer.randomize(babyCode, 0.05);
            Species babySpecies=new Species(world, species, new OneDeeDNA(babyCode));
            world.addBeing(babyPosition, this, babySpecies);
        }
        
        
        if(C.debug){
            C.println("Exiting breed function ");
        }
    }
    
    
    
    public void print(PrintStream out){
        out.print("Being number ");
        out.print(getId());
        out.print(", position ");
        out.print(getPosition());
        out.print(", age ");
        out.print(getAge());
        if(ancestorId!=-1) {
            out.print(", ancestor id ");
            out.print(ancestorId);
        } else
            out.print(", no ancestor");
        out.print(", species ");
        out.print(species.getCode());
        out.print(", hasn't eaten for ");
        out.print(world.getDate()-lastLunchDate);
        out.print(" days.");
    }
    
    public void println(PrintStream out){
        print(out);
        out.println();
    }
    
}
