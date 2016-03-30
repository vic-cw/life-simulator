/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifesimulator;

import OneDeeDNACode.Interpreter;
import OneDeeDNACode.LanguageImplementationException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


/**
 *
 * @author vic
 */
public class OneDeeWorld {
    
    private int hole;
    private int maximumNumberOfLoopsInDnaCode;
    private int date;
    private int lifeCount;
    private CounterByPosition foodDistribution;
    private CounterByPosition beingDistribution;
    private SpeciesMutantsRegister speciesMutantsRegister;
    private SpeciesPopulationRegister populationRegisterBySpecies;
    private Species smartSpecies;
    private Species dumbSpecies;
    private HashMap<Integer, OneDeeBeing> beingsRegisterById;
    private HashMap<String, Species> speciesRegisterbyCode;
    public Random random;
    
    
    /**
     * We could have natural death be part someone's DNA. Or the dying decision could be.
     * We could also have natural death be done with a random distribution rather than certain death.
     */
    private final int NATURAL_DEATH_AGE;
    /**
     * We could have starvation depend on some factors. We could also make it randomly distributed
     */
    private final int STARVATION_LIMIT;
    
    
    /**
     * Creates a new world
     * 
     * @param ihole : position of the hole
     * @param naturalDeathAge : age at which beings die of natural death
     * @param starvationLimit : time after which a being dies if he hasn't eaten.
     */
    public OneDeeWorld(int ihole, int maxNumberOfLoops, int naturalDeathAge, int starvationLimit) throws Exception{
        
        // Registration of world's characteristics
        hole=ihole;
        maximumNumberOfLoopsInDnaCode=maxNumberOfLoops;
        NATURAL_DEATH_AGE=naturalDeathAge;
        STARVATION_LIMIT=starvationLimit;
        
        // Initialization of other variables
        date=0;
        lifeCount=0;
        foodDistribution=new CounterByPosition();
        beingDistribution=new CounterByPosition();
        speciesMutantsRegister=new SpeciesMutantsRegister();
        populationRegisterBySpecies= new SpeciesPopulationRegister();
        speciesRegisterbyCode=new HashMap<String, Species>();
        smartSpecies=new Species(this, new WalkFromHoleDna());
        dumbSpecies=new Species(this, new WalkToHoleDna());
        beingsRegisterById=new HashMap<Integer, OneDeeBeing>();
        random=new Random();
    }
    
    /**
     * Creates a new world with an initial population distributed geographically
     * 
     * @param ihole : position of the hole
     * @param maxNumberOfLoops : the maximum number of loops inside a while instruction authorized in a DNA code
     * @param naturalDeathAge : age at which beings die of natural death
     * @param starvationLimit : time after which a being dies if he hasn't eaten.
     * @param initialPopulation : the geographical distribution of the initial population
     * @throws Exception 
     */
    public OneDeeWorld(int ihole, int maxNumberOfLoops, int naturalDeathAge, int starvationLimit, int[] initialPopulation) throws Exception{
        this(ihole, maxNumberOfLoops, naturalDeathAge, starvationLimit);
        for(int i=0; i<initialPopulation.length; i++){
            if(random.nextBoolean())
                addBeing((new OneDeeBeing(initialPopulation[i], this, lifeCount, null, smartSpecies)));
            else
                addBeing((new OneDeeBeing(initialPopulation[i], this, lifeCount, null, dumbSpecies)));
        }
    }
    
    public void addBeing(int pos, OneDeeBeing a, Species s) throws Exception{
        addBeing(new OneDeeBeing(pos, this, lifeCount, a, s));
    }
    
    public OneDeeBeing addAndReturnBeing(int pos, OneDeeBeing a, Species s) throws Exception{
        OneDeeBeing being=new OneDeeBeing(pos, this, lifeCount, a, s);
        addBeing(being);
        return being;
    }

    public final void addBeing(OneDeeBeing being){
        beingDistribution.increment(being.getPosition());
        populationRegisterBySpecies.registerBeing(being);
        beingsRegisterById.put(lifeCount, being);
        lifeCount++;
    }
    
    public void registerMove(int initialPosition, int finalPosition) throws Exception{
        beingDistribution.decrement(initialPosition);
        beingDistribution.increment(finalPosition);
    }
    
    public void registerLunch(int position) throws Exception{
        if(!foodDistribution.containsKey(position)) throw new Exception("Problem, trying to eat where there is no food");
        foodDistribution.decrement(position);
    }
    
    public void registerNewSpecies(Species s) {
            speciesMutantsRegister.addMutation(s.getParent(), s);
            speciesRegisterbyCode.put(s.getCode(), s);
    }
    
    
    // @TODO : chose whether I don't replace all of this by just "first arrived, first served", possibly with random iterating of population
    
    public void displayWorld(PrintStream out){
        out.println("\n\n-------- One dimension world display ---------\n\n");
        out.print("Date : ");
        out.println(date);
        out.print("Hole position : ");
        out.println(hole);
        out.print("Food distribution : ");
        foodDistribution.println(System.out);
        out.print("Beings positions : ");
        beingDistribution.println(System.out);
        displaySpeciesPopulation(out);
        out.print("Population (size ");
        out.print(populationRegisterBySpecies.getTotalPopulationSize());
        out.println("), census :");
        displayPopulation(out);
    }
    
    public void displayBeing(int id, PrintStream out){
        if(!beingsRegisterById.containsKey(id))
            out.println("No living being with id number "+id+", sorry.");
        beingsRegisterById.get(id).println(out);
    }
    
    public void displayPopulation(PrintStream out){
        for(int i=0; i<lifeCount; i++){
            if(!beingsRegisterById.containsKey(i))
                continue;
            OneDeeBeing being=beingsRegisterById.get(i);
            being.println(out);
        }
    }
    
    public void displaySpecies(String code, PrintStream out){
        if(!speciesRegisterbyCode.containsKey(code)){
            out.print("Species not found by code, please try again. Code : ");
            out.println(code);
            return;
        }
        populationRegisterBySpecies.displaySpecies(speciesRegisterbyCode.get(code), speciesMutantsRegister, out);
    }
    
    public void displaySpeciesCompletely(String code, PrintStream out){
        displaySpecies(code, out);
        if(!speciesRegisterbyCode.containsKey(code))
            out.println("No living species with code"+code+", sorry.");
        out.println("DNA code : ");
        OneDeeDNA dna=speciesRegisterbyCode.get(code).getDna();
        (new Interpreter(maximumNumberOfLoopsInDnaCode)).println(dna.getCode(), out);
    }
    
    
    public void displaySpeciesPopulation(PrintStream out){
        populationRegisterBySpecies.println(speciesMutantsRegister, out);
    }
    
    // Le passage du temps
    public void iterate() throws LanguageImplementationException, Exception{
        if(C.debug){
            C.println("Beginning an iteration");
        }
        date++;
        System.out.print("   Starting killing beings... ");
        killBeings();
        System.out.println("done");
        naturalModifications();
        
        // Preparing randomizization of beings iteration
        System.out.print("   Starting shuffling of beings... ");
        LinkedList<Entry<Integer, OneDeeBeing>> entryShuffledOrder=new LinkedList<Entry<Integer, OneDeeBeing>>();
        
        for(Entry<Integer, OneDeeBeing> entry : beingsRegisterById.entrySet())
            entryShuffledOrder.add(entry);
        Collections.shuffle(entryShuffledOrder);
        System.out.println("done");
        
        // Beings iteration
        System.out.print("   Starting iterating every being ("+entryShuffledOrder.size()+" beings)... ");
        long duration=System.currentTimeMillis();
        for(Entry<Integer,OneDeeBeing> entry : entryShuffledOrder){
            entry.getValue().iterate();
            System.gc();
        }
        duration=System.currentTimeMillis()-duration;
        System.out.println("done in "+duration+" milliseconds, ie "+(duration/entryShuffledOrder.size())+" milliseconds per being");
        
        distributeFood();
        System.gc();
    }
    
    // We could make food distribution parameters such as diameter or quantity be characteristics of the world.
    // We could also have food get old and rot. It could disappear or become poisonous.
    
    /**
     * Fonction de distribution qui s'arrange pour qu'il y ait autant de nourriture que d'êtres vivants
     * et qu'elle soit répartie aléatoirement avec une distance moyenne entre deux trucs de nourriture égale à 3,
     * et centrée autour de zéro.
     */
    
    private void distributeFood(){
        if(C.debug){
            C.println("Entering distributeFood() function");
        }
        int populationSize=populationRegisterBySpecies.getTotalPopulationSize();
        int foodWanted=1*populationSize;
        int foodToDrop=foodWanted-foodDistribution.totalItems();
        int diameter=3*foodWanted;
        for(int i=0; i<foodToDrop; i++){
            foodDistribution.increment(random.nextInt(diameter+1)-(diameter/2));
        }
        if(C.debug){
            C.println("Exiting distributeFood() function");
        }
    }
    
    
    // @TODO : implement a history of why beings die
    
    /**
     * This function iterates through the population and kills the beings who should die.
     * @throws Exception 
     */
    private void killBeings() throws Exception{
        if(C.debug){
            C.println("Entering killBeings() function");
        }
        
        LinkedList<OneDeeBeing> dead=new LinkedList<OneDeeBeing>();
        
        for(Entry<Integer,OneDeeBeing> entry : beingsRegisterById.entrySet()){
            if(killingDecision(entry.getValue())){
                beingDistribution.decrement(entry.getValue().getPosition());
                populationRegisterBySpecies.registerDeath(entry.getValue());
                dead.add(entry.getValue());
            }
        }
        
        for(OneDeeBeing deadBeing : dead){
            beingsRegisterById.remove(deadBeing.getId());
            // Here I can suppress a dead specie if I want
        }
        
        dead=null;
        System.gc();
        
        /*
        for(int i=0; i<lifeCount; i++){
            if(!beingsRegisterById.containsKey(i))
                continue;
            OneDeeBeing being=beingsRegisterById.get(i);
            if(killingDecision(being)){
                beingDistribution.decrement(being.getPosition());
                beingsRegisterById.remove(i);
                populationRegisterBySpecies.registerDeath(being);
            }
        }*/
        
        if(C.debug){
            C.println("Exiting killBeings() function");
        }
        
    }
    
    // Ici on pourrait ajouter des morts naturelles, des morts aléatoires, etc
    
    /**
     * This function decides whether or not a being should die at this date
     * 
     * @param being The being to be assessed
     * @return True if the being should die. False otherwise
     */
    private boolean killingDecision(OneDeeBeing being){
        
        if(being.getPosition()==hole) return true;
        if(being.getAge()>NATURAL_DEATH_AGE) return true;
        if(date-being.getLastLunchDate()>STARVATION_LIMIT) return true;
        if(being.shouldDieNow()) return true;
   
        return false;
    }
    
    
    /*
     * Cette fonction modifie les paramètres du monde indépendamment de ce qui s'y passe
     */
    private void naturalModifications(){
        
    }
    
    public int getHole(){
        return hole;
    }
    
    public int getMaximumNumberOfLoops(){
        return maximumNumberOfLoopsInDnaCode;
    }
    
    public int getLifeCount(){
        return lifeCount;
    }
    
    public int getDate(){
        return date;
    }
    
    public int getNumberOfLivingBeings(){
        return populationRegisterBySpecies.getTotalPopulationSize();
    }
    
    public int getNumberOfOccupiedPositions(){
        return beingDistribution.size();
    }
    
    public int getGeographicalRange(){
        if(beingDistribution==null || beingDistribution.isEmpty())
            return 0;
        Integer[] positions=beingDistribution.keySet().toArray(new Integer[1]);
        int min=positions[0];
        int max=positions[0];
        for(int i=0; i<positions.length; i++){
            if(positions[i]<min)
                min=positions[i];
            else if(positions[i]>max)
                max=positions[i];
        }
        
        return max-min+1;
        
    }
    
    public int getAverageSpeciesStringLength(){
        int totalLength=0;
        for(Map.Entry<String, Species> entry : speciesRegisterbyCode.entrySet()){
            totalLength+=entry.getKey().length();
        }
        return totalLength/speciesRegisterbyCode.size();
    }
    
    public int[] getAverageNumberOfCodeNodePerBeing(HashMap<Species, int[]> registerForSpecies){
        int[] register=new int[7];
        int[] registerForBeings;
        if(registerForSpecies==null)
            registerForSpecies=new HashMap<Species, int[]>();
        
        for(OneDeeBeing being : beingsRegisterById.values()){
            if(registerForSpecies.containsKey(being.species))
                registerForBeings=registerForSpecies.get(being.species);
            else {
                registerForBeings=being.species.getNumberOfEachCodeNode();
                registerForSpecies.put(being.species, registerForBeings);
            }
            C.add(register, registerForBeings);
        }
        
        for(int i=0; i<register.length; i++){
            register[i]=(100*register[i])/beingsRegisterById.size();
        }
        return register;
    }
    
    /**
     * This function returns the number of species in this world which have at least of living being.
     * 
     * @return 
     */
    public int getNumberOfLivingSpecies(){
        int number=0;
        for(Map.Entry<Species, OneDeePopulation> entry : populationRegisterBySpecies.entrySet()){
            if(entry.getValue().size()>0)
                number++;
        }
        return number;
    }
    
    public boolean isThereFoodHere(int position){
        return (foodDistribution.containsKey(position) && foodDistribution.get(position).intValue()>0);
    }
    
    public int numberOfMutantSpeciesFrom(Species s) {
        if(!speciesMutantsRegister.containsKey(s))
            return 0;
        return speciesMutantsRegister.get(s).size();
    }

    public int getTotalNumberOfSpecies() {
        return populationRegisterBySpecies.size();
    }

    public int getNumberOfExtinctSpecies() {
        int number=0;
        for(Entry<Species, OneDeePopulation> entry : populationRegisterBySpecies.entrySet()){
            if(isExtinct(entry.getKey()))
                number++;
        }
        return number;
    }
    
    public boolean isExtinct(Species s){
        if(populationRegisterBySpecies.getPopulationSize(s)>0) // If species has living populations, it is not extinct
            return false;
        if(!speciesMutantsRegister.containsKey(s)) // If species hasn't population and hasn't mutants, it is extinct
            return true;
        for(Species mutant : speciesMutantsRegister.get(s)){
            if(!isExtinct(mutant))
                return false;
        }
        return true;
    }
}
