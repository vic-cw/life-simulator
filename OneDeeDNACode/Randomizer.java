/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OneDeeDNACode;

import java.util.Random;
import lifesimulator.C;

/**
 *
 * @author vic
 */
public class Randomizer {
    
    
    /**
     * This function modifies the code given as a parameter by introducing one or several mutations.
     * 
     * @param code
     * @param numberOfMutations
     * @throws Exception 
     */
    public void randomize(Code code, int numberOfMutations) throws Exception{
        
        Random random=new Random();
        
        for(int i=0; i<numberOfMutations; i++){
            int placeOfMutation;
            if(code.getCodeSize()==0){
                placeOfMutation=0;
                if(C.debug)
                    System.out.println("CODE SIZED 0 !!!!");
            }
            else
                placeOfMutation=random.nextInt(code.getCodeSize());
            if(C.debug){
                System.out.print("Mutation decided at place : ");
                System.out.println(placeOfMutation);
            }
            introduceMutation(code, placeOfMutation);
        }
    }
    
    /**
     * This function introduces at least one mutation, and more according to the probability given as a parameter.
     * The probability represents the probability for an element of the code to get a mutation.
     * 
     * @param code
     * @param probabilityOfMutation
     * @throws Exception 
     */
    public void randomize(Code code, double probabilityOfMutation) throws Exception{
        
        if(probabilityOfMutation>0){
            int numberOfMutations=Math.max((int) (code.getCodeSize()*probabilityOfMutation),1);
            randomize(code, numberOfMutations);
        }
        
    }
    
    public void introduceMutation(Code code, int placeOfMutation) throws Exception{
        int parsedElementsCount=0;
        for(int i=0; i<code.size(); i++){
            OneDeeDnaInstruction inst=code.get(i);
             
            // If we have to go further
            if(parsedElementsCount+inst.getCodeSize()<=placeOfMutation){
                parsedElementsCount+=inst.getCodeSize();
                continue;
            }
            
            // If the mutation is on the first element of the instruction
            // In this case, we are either going to insert an instruction before this one, or delete or modify this one.
            if(parsedElementsCount==placeOfMutation){
                Random random=new Random();
                int mutationType=1000+random.nextInt(3);
                if(mutationType==Constant.MUTATION_DELETION){
                    /*
                     * The policy for deletion is the following :
                     * Assign and Define only get deleted. If and While get deleted but insert
                     * the code that they should play once instead of the if instruction.
                     * This can totally be changed.
                     */
                    if(C.debug)
                        System.out.println("Mutation type : Deletion");
                    code.remove(i);
                    switch(inst.getType()){
                        case Constant.ASSIGN : 
                            break;
                        case Constant.DEFINE :
                            break;
                        case Constant.IF :
                            Code codeToInsert=((InstructionIf)inst).getCodeThen();
                            code.addAll(i, codeToInsert);
                            break;
                        case Constant.WHILE :
                            codeToInsert=((InstructionWhile)inst).getCode();
                            code.addAll(i, codeToInsert);
                            break;
                    }
                    break;
                }
                
                if(mutationType==Constant.MUTATION_ADDITION){
                    if(C.debug)
                        System.out.println("Mutation type : Addition");
                    code.add(i, newRandomInstruction());
                    break;
                }
                
                if(mutationType==Constant.MUTATION_MODIFICATION){
                    if(C.debug)
                        System.out.println("Mutation type : Modification");
                    code.set(i, randomlyModifyInstruction(inst));
                    break;
                }
                
            }
            
            // If the element is inside the current instruction but further
            int relativePlace=placeOfMutation-parsedElementsCount-1;
            switch(inst.getType()){

                case Constant.ASSIGN :
                    Assign instAssign=(Assign) inst;
                    if(relativePlace>=instAssign.getValue().getCodeSize())
                        code.set(i, 
                                new Assign(instAssign.getValue().duplicate(),
                                randomlyModifyValue(relativePlace-instAssign.getValue().getCodeSize(),
                                instAssign.getVariableIndex())));
                    else
                        code.set(i,
                                new Assign(randomlyModifyValue(relativePlace,instAssign.getValue()),
                                instAssign.getVariableIndex().duplicate()));
                    break;

                case Constant.DEFINE :
                    Define instDefine=(Define)inst;
                    code.set(i, new Define(randomlyModifyValue(relativePlace,instDefine.getValue())));
                    break;
                    
                case Constant.IF :
                    InstructionIf instIf=(InstructionIf)inst;
                    if(relativePlace>=instIf.getCondition().getCodeSize()+instIf.getCodeThen().getCodeSize()){
                        Code codeElse=instIf.getCodeElse().duplicate();
                        introduceMutation(codeElse, relativePlace-instIf.getCondition().getCodeSize() + instIf.getCodeThen().getCodeSize());
                        code.set(i, 
                                new InstructionIf(
                                        instIf.getCondition().duplicate(), 
                                        instIf.getCodeThen().duplicate(),
                                        codeElse
                                        ));
                        break;
                    }
                    if(relativePlace>=instIf.getCondition().getCodeSize()){
                        Code codeThen=instIf.getCodeThen().duplicate();
                        introduceMutation(codeThen, relativePlace-instIf.getCondition().getCodeSize());
                        code.set(i, 
                                new InstructionIf(
                                        instIf.getCondition().duplicate(),
                                        codeThen,
                                        instIf.getCodeElse().duplicate()));
                        break;
                    }
                    code.set(i, new InstructionIf(
                            randomlyModifyValue(relativePlace, instIf.getCondition()),
                            instIf.getCodeThen().duplicate(),
                            instIf.getCodeElse().duplicate()));
                    break;
                    
                case Constant.WHILE :
                    InstructionWhile instWhile=(InstructionWhile)inst;
                    if(relativePlace>=instWhile.getCondition().getCodeSize()){
                        Code codeWhile=instWhile.getCode().duplicate();
                        introduceMutation(codeWhile, relativePlace-instWhile.getCondition().getCodeSize());
                        code.set(i, new InstructionWhile(instWhile.getCondition().duplicate(), codeWhile));
                        break;
                    }
                    code.set(i, 
                            new InstructionWhile(randomlyModifyValue(relativePlace, instWhile.getCondition()), 
                                    instWhile.getCode().duplicate()));
                    break;

            }
            break;
             
        }
    }
    
    public OneDeeDnaInstruction newRandomInstruction() throws Exception{
        Random random=new Random();
        int instructionType=random.nextInt(4);
        switch(instructionType){
            case Constant.ASSIGN :
                return new Assign(newRandomValue(),newRandomValue());
                // Here we made the choice of having a very large randomness
                // by enabling any assignment to be made without any control
            case Constant.DEFINE :
                return new Define(newRandomValue());
                // Here we made the choice of having a very large randomness
                // by enabling any number in the definition of the variable.
            case Constant.IF :
                Code codeThen=new Code();
                Code codeElse=new Code();
                codeThen.add(newRandomSimpleInstruction());
                codeElse.add(newRandomSimpleInstruction());
                return new InstructionIf(newRandomValue(), codeThen, codeElse);
                // Here we chose to have only simple instructions in the "else code" 
                // and the "then code". the goal is not to create too much complication in just one mutation
            case Constant.WHILE :
                Code code=new Code();
                code.add(newRandomSimpleInstruction());
                return new InstructionWhile(newRandomValue(), code);
                // Similarly, we enable the while loop to have only one instruction, and a simple one
        }
        throw new Exception("Problem, instructionType not defined in newRandomInstruction()");
    }
    
    
    public OneDeeDnaInstruction newRandomSimpleInstruction() throws Exception{
        Random random=new Random();
        int instructionType=random.nextInt(2);
        switch(instructionType){
            case Constant.ASSIGN :
                return new Assign(newRandomValue(),newRandomValue());
                // Here we made the choice of having a very large randomness
                // by enabling any assignment to be made without any control
            case Constant.DEFINE :
                return new Define(newRandomValue());
                // Here we made the choice of having a very large randomness
                // by enabling any number in the definition of the variable.
        }
        throw new Exception("Problem, instructionType not defined in newRandomSimpleInstruction()");
    }
    
    
    /**
     * This function generates a new Value object, randomly. It can be any length.
     * Probabilities of appearance of 1 or 0 are 25% each, Variable is 25% and Sum and Substraction both have
     * a 12.5% probability. This ensures termination of process.
     * 
     * @return a new random value
     * @throws Exception 
     */
    public OneDeeDnaValue newRandomValue() throws Exception{
        
        // Here we chose to enable any number to be created, however
        // In order for it not to loop to infinity, we put more probability on a zero or a one
        // to happen than to a sum or substraction. This can be changed.
        Random random=new Random();
        switch(random.nextInt(8)){
            case 0 :
            case 1 :
                return Constant.ONE;
            case 2 :
            case 3 :
                return Constant.ZERO;
            case 4 :
            case 5 :
                return new Variable(newRandomValue());
            case 6 :
                return new Sum(newRandomValue(), newRandomValue());
            case 7 :
                return new Substraction(newRandomValue(), newRandomValue());
        }
        
        throw new Exception("Problem in newRandomValue()");
    }
    
    
    
    // Here we always take certain possibilities to randomize. Most of them can be changed.
    // They can also be merged by introducing more randomness and chosing a policy over the other
    // based on a coin toss.
    
    /**
     * This function creates a new instruction by copying from the inst parameter and modifying it
     * randomly. It then returns this new Instruction.
     * Policy :
     *  - each other type of instruction is equally probable
     *  - when...
     * @param inst
     * @return
     * @throws Exception 
     */
    private OneDeeDnaInstruction randomlyModifyInstruction(OneDeeDnaInstruction inst) throws Exception{
        Random random=new Random();
        int replacement=random.nextInt(3);
        
        switch(inst.getType()){
            
            case Constant.ASSIGN :
                Assign instAssign=((Assign)inst);
                switch(replacement){
                    case 0 :
                        return new Define(instAssign.getValue().duplicate());
                        // If we replace an Assign by a Define, we define a new variable with
                        // the value that we wanted to give to the former variable.
                    case 1 :
                        Code codeThen=new Code();
                        Code codeElse=new Code();
                        codeThen.add(instAssign.duplicate());
                        codeElse.add(newRandomSimpleInstruction());
                        return new InstructionIf(instAssign.getValue().duplicate(),codeThen, codeElse);
                    case 2 :
                        Code code=new Code();
                        code.add(instAssign.duplicate());
                        return new InstructionWhile(instAssign.getValue().duplicate(), code);
                }
                throw new Exception("Problem in randomlyModify(), wrong replacement code with Assign");
                
            case Constant.DEFINE :
                Define instDefine=(Define)inst;
                switch(replacement){
                    case 0 :
                        return new Assign(instDefine.getValue().duplicate(), newRandomValue());
                    case 1 :
                        Code codeThen=new Code();
                        Code codeElse=new Code();
                        codeThen.add(instDefine.duplicate());
                        codeElse.add(newRandomSimpleInstruction());
                        return new InstructionIf(newRandomValue(), codeThen, codeElse);
                    case 2 :
                        Code code=new Code();
                        code.add(instDefine.duplicate());
                        return new InstructionWhile(newRandomValue(), code);
                }
                throw new Exception("Problem in randomlyModify(), wrong replacement code with Define");
                
            case Constant.IF :
                InstructionIf instIf=(InstructionIf)inst;
                switch(replacement) {
                    case 0 :
                        return new Assign(instIf.getCondition().duplicate(), newRandomValue());
                    case 1 :
                        return new Define(instIf.getCondition().duplicate());
                    case 2 :
                        return new InstructionWhile(instIf.getCondition().duplicate(),instIf.getCodeThen().duplicate());
                }
                throw new Exception("Problem in randomlyModify(), wrong replacement code with If");
                
            case Constant.WHILE :
                InstructionWhile instWhile=(InstructionWhile)inst;
                switch(replacement){
                    case 0 :
                        return new Assign(instWhile.getCondition().duplicate(), newRandomValue());
                    case 1 :
                        return new Define(instWhile.getCondition().duplicate());
                    case 2 :
                        Code codeElse=new Code();
                        codeElse.add(newRandomSimpleInstruction());
                        return new InstructionIf(instWhile.getCondition().duplicate(), 
                                instWhile.getCode().duplicate(), codeElse);
                }
                throw new Exception("Problem in randomlyModify(), wrong replacement code with While");
        }
        
        throw new Exception("Problem in randomlyModify(), wrong instruction code");
    }
    
    private OneDeeDnaValue randomlyModifyValue(int position, OneDeeDnaValue value) throws Exception{
        
        if(position==0){
            Random random=new Random();
            
            switch(value.getType()){
                
                case Constant.INT_ZERO :
                    return Constant.ONE;
                case Constant.INT_ONE :
                    return Constant.ZERO;
                    
                case Constant.VARIABLE :
                    Variable varValue=(Variable)value;
                    switch(random.nextInt(4)){
                        case 0 :
                            return Constant.ZERO;
                        case 1 :
                            return Constant.ONE;
                        case 2 :
                            return new Sum(varValue.getIndex().duplicate(), newRandomValue());
                        default :
                            return new Substraction(varValue.getIndex().duplicate(), newRandomValue());
                    }
                case Constant.SUM :
                    Sum sumValue=(Sum)value;
                    switch(random.nextInt(4)){
                        case 0 :
                            return Constant.ZERO;
                        case 1 :
                            return Constant.ONE;
                        case 2 :
                            return new Variable(sumValue.getOperand1().duplicate());
                        default :
                            return new Substraction(sumValue.getOperand1().duplicate(), sumValue.getOperand2().duplicate());
                    }
                case Constant.SUBSTRACTION :
                    Substraction subValue=(Substraction)value;
                    switch(random.nextInt(4)){
                        case 0 :
                            return Constant.ZERO;
                        case 1 :
                            return Constant.ONE;
                        case 2 :
                            return new Variable(subValue.getOperand1().duplicate());
                        default :
                            return new Sum(subValue.getOperand1().duplicate(), subValue.getOperand2().duplicate());
                    }
            }
            throw new Exception("Problem in randomlyModifyValue(), value type not recognized while modifying head of value");
        }
        
        switch(value.getType()){
            case Constant.INT_ONE :
            case Constant.INT_ZERO :
                throw new Exception("Problem in randomlyModifyValue(), trying to modify at a place that doesn't exist");
            case Constant.VARIABLE :
                Variable varValue=(Variable)value;
                return new Variable(randomlyModifyValue(position-1, varValue.getIndex()));
            case Constant.SUM :
                Sum sumValue=(Sum) value;
                if(position<=sumValue.getOperand1().getCodeSize())
                    return new Sum(randomlyModifyValue(position-1,sumValue.getOperand1()),
                            sumValue.getOperand2().duplicate());
                return new Sum(sumValue.getOperand1().duplicate(),
                        randomlyModifyValue(position-1-sumValue.getOperand1().getCodeSize(), sumValue.getOperand2()));
            case Constant.SUBSTRACTION :
                Substraction subValue=(Substraction) value;
                if(position<=subValue.getOperand1().getCodeSize())
                    return new Substraction(randomlyModifyValue(position-1,subValue.getOperand1()),
                            subValue.getOperand2().duplicate());
                return new Substraction(subValue.getOperand1().duplicate(),
                        randomlyModifyValue(position-1-subValue.getOperand1().getCodeSize(), subValue.getOperand2()));
        }
        throw new Exception("Porblem in randomlyModifyValue(), value type not recognized");
    }
    
    
}


/*
 * Quelle randomisation veut-on ?
 * 
 * On veut une certaine continuité de l'espèce, pour que celles qui marchent bien perdurent.
 * Donc déjà on ne fera une mutation qu'une fois sur 2 ou 3, bref ça c'est un paramètre qu'on peut mettre dans le monde,
 * ou bien dans le DNA, ou bien comme caractéristique d'un being.
 * 
 * On peut aussi ajouter une probabilité que la mutation conduise à la mort du bébé avec une certaine probabilité.
 * Dans ce cas on peut lancer une exception.
 * 
 * Ceci étant dit, quand on choisit de randomize quelqu'un, comment on fait ?
 * On a déjà dit qu'il y avait plusieurs types de modifications :
 * - suppression d'un élément
 * - ajout d'un élément
 * - modification d'un élément
 * Le petit problème que j'ai pour implémenter cette framework dérivée de l'ADN, est dans la définition d'un "élément"
 * En effet, l'ADN c'est une suite de lettres, donc on peut en modifier une par une autre et voilà. 
 * Dans le cas de mon code, c'est plutôt une suite d'arbres.
 * Chaque arbre étant une instruction. Un arbre pouvant être très petit ou très grand.
 * 
 * Une fois qu'on est capable de compter les éléments et de savoir rapidement combien il y en a au total, 
 * qu'est-ce qu'on fait ?
 * Moi j'avais en tête de faire une seule mutation et voilà. Mais en fait on peut en faire plusieurs.
 * Une autre possibilité c'est de dire que chaque noeud a une probabilité de muter donnée. En fait ça revient au même,
 * à un changement de variable près.
 * Donc je vais faire deux fonctions. Une qui prend en paramètre le nombre de mutation. Une qui prend en paramètre la probabilité de muter.
 * 
 */