
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

import java.io.PrintStream;
import eu.combal_weiss.victor.lifesimulator.main.C;


public class Interpreter {
    
    private int maximumNumberOfLoops;
    
    public Interpreter(int maxNumberOfLoops){
        maximumNumberOfLoops=maxNumberOfLoops;
    }
    
    
    public void execute(Code code, VariableEnvironment variableEnvironment) throws LanguageImplementationException, InfiniteLoopDnaException{
        
        if(C.debug)
            C.println("Entering execute from DNA Interpreter");
        int initialVariableNumber=variableEnvironment.size();
        
        for(int i=0; i<code.size(); i++){
            if(C.debug){
                C.print("Execute Instruction ");
                C.appendln(code.get(i).printType());
            }
            executeInstruction(code.get(i), variableEnvironment);
            if(C.debug){
                C.print("Instruction ");
                C.append(code.get(i).printType());
                C.appendln(" executed");
            }
        }
        // Clearing variables created inside the "code"
        while(variableEnvironment.size()>initialVariableNumber){
            variableEnvironment.removeLast();
        }
        
        if(C.debug){
            C.println("Existing execute");
        }
    }
    
    
    private void executeInstruction(OneDeeDnaInstruction inst, VariableEnvironment variableEnvironment) throws LanguageImplementationException, InfiniteLoopDnaException{
        int type=inst.getType();
        if(type==Constant.ASSIGN){
            Assign assign=(Assign) inst;
            int value=calculateValue(assign.getValue(),variableEnvironment);
            int index=calculateValue(assign.getVariableIndex(), variableEnvironment);
            if(C.debug){
                C.print("Assigning ");
                C.append(value);
                C.append("to variable at index ");
                C.appendln(index);
            }
            variableEnvironment.set(index, new Integer(value));
            return;
        }
        if(type==Constant.DEFINE){
            Define define=(Define) inst;
            int value=calculateValue(define.getValue(), variableEnvironment);
            variableEnvironment.add(new Integer(value));
            return;
        }
        if(type==Constant.IF){
            InstructionIf instIf=(InstructionIf) inst;
            int condition=calculateValue(instIf.getCondition(), variableEnvironment);
            if(condition>0)
                execute(instIf.getCodeThen(), variableEnvironment);
            else
                execute(instIf.getCodeElse(), variableEnvironment);
            return;
        }
        
        if(type==Constant.WHILE){
            InstructionWhile instWhile=(InstructionWhile) inst;
            
            int condition=calculateValue(instWhile.getCondition(),variableEnvironment);
            
            // One variable environment is defined to detect the most simple infinite loop
            VariableEnvironment environment2=variableEnvironment.duplicate();
            int numberOfLoops=0;
            while(condition>0){
                numberOfLoops++;
                if(numberOfLoops>=maximumNumberOfLoops)
                    throw new InfiniteLoopDnaException("This being is thinking too much : passed the maximum number of loops");
                execute(instWhile.getCode(),variableEnvironment);
                condition=calculateValue(instWhile.getCondition(),variableEnvironment);
                
                //Checking for the most simple infinite loop
                if(condition>0 && variableEnvironment.isEqualto(environment2)) throw new InfiniteLoopDnaException("This being is bugged : he is in an infinite loop, like Corneille");
                environment2=variableEnvironment.duplicate();
            }
            return;
        }
        throw new LanguageImplementationException("Problem in executeInstruction() : instruction with a wrong type number");
    }
        
    /**
     * This function returns true if the value reads a variable at some point, and false if it is only a constant number.
     * @param value
     * @return 
     */
    private boolean hasACallToVariable(OneDeeDnaValue value){
        switch(value.getType()){
            case Constant.INT_ONE:
            case Constant.INT_ZERO:
                return false;
            case Constant.SUBSTRACTION :
                Substraction subValue=(Substraction) value;
                return hasACallToVariable(subValue.getOperand1()) || hasACallToVariable(subValue.getOperand2());
            case Constant.SUM :
                Sum sumValue=(Sum) value;
                return hasACallToVariable(sumValue.getOperand1()) || hasACallToVariable(sumValue.getOperand2());
            case Constant.VARIABLE :
                return true;
            default :
                return true;
        }
    }

    
    // What to do when tries to go to a variable that wasn't defined ? Both to read or to write ?
    // If we chose to return something and see what happens, we let more room for innovation
    // If we chose to die in that case, we put more pressure on the livings.
    
    
    public int calculateValue(OneDeeDnaValue value, VariableEnvironment variableEnvironment) throws LanguageImplementationException{
        
        if(C.debug){
            C.print("Entering CalculateValue with type ");
            C.appendln(value.printType());
        }
        int type=value.getType();
        if(type==Constant.INT_ZERO) return 0;
        if(type==Constant.INT_ONE) return 1;
        if(type==Constant.VARIABLE) {
            Variable variable=(Variable) value;
            int index=calculateValue(variable.getIndex(), variableEnvironment);
            return variableEnvironment.get(index).intValue();
        }
        if(type==Constant.SUM){
            Sum sum=(Sum) value;
            int operand1=calculateValue(sum.getOperand1(), variableEnvironment);
            int operand2=calculateValue(sum.getOperand2(), variableEnvironment);
            return operand1+operand2;
        }
        if(type==Constant.SUBSTRACTION){
            Substraction sub=(Substraction) value;
            int operand1=calculateValue(sub.getOperand1(), variableEnvironment);
            int operand2=calculateValue(sub.getOperand2(), variableEnvironment);
            return operand1-operand2;
        }
        throw new LanguageImplementationException("Problem in calculateValue() : value with a wrong type number");
    }
    
    
    public void println(Code code, PrintStream out){
        print(code,out);
        out.println();
    }
    
    public void print(Code code, PrintStream out){
        print(code,out,0);
    }
    
    private void print(Code code, PrintStream out, int indent){
        for(OneDeeDnaInstruction inst : code){
            print(inst, out, indent);
        }
    }
    
    private void println(Code code, PrintStream out, int indent){
        print(code, out, indent);
        out.println();
    }
    
    private void print(OneDeeDnaInstruction inst, PrintStream out, int indent){
        out.print("> ");
        for(int i=0; i<indent; i++){
            out.print("   ");
        }
        switch(inst.getType()){
            case Constant.ASSIGN :
                Assign instAssign=(Assign)inst;
                out.println("Assign ");
                out.print("> ");
                println(instAssign.getValue(), out, indent+1);
                print("to variable at index : ", out, indent);
                println(instAssign.getVariableIndex(), out, 0);
                break;
            case Constant.DEFINE :
                Define instDefine=(Define) inst;
                out.println("Define new Variable with value :");
                out.print("> ");
                println(instDefine.getValue(), out, indent+1);
                break;
            case Constant.IF :
                InstructionIf instIf=(InstructionIf) inst;
                out.print("If( ");
                print(instIf.getCondition(), out);
                out.println(" > 0 ) then {");
                print(instIf.getCodeThen(), out, indent+1);
                println("} else {", out, indent);
                print(instIf.getCodeElse(), out, indent+1);
                println("}",out,indent);
                break;
            case Constant.WHILE :
                InstructionWhile instWhile=(InstructionWhile)inst;
                out.print("While( ");
                print(instWhile.getCondition(), out);
                out.println(" > 0 ) {");
                println(instWhile.getCode(), out, indent+1);
                println("}", out, indent);
                break;
                
        }
    }
    
    private void println(OneDeeDnaValue value, PrintStream out, int indent){
        
        for(int i=0; i<indent; i++){
            out.print("   ");
        }
        print(value, out);
        out.println();
    }
    
    private void print(OneDeeDnaValue value, PrintStream out){
        
        switch(value.getType()){
            case Constant.INT_ZERO :
                out.print("0");
                break;
            case Constant.INT_ONE :
                out.print("1");
                break;
            case Constant.SUM :
                Sum sumValue= (Sum)value;
                out.print("(");
                print(sumValue.getOperand1(), out);
                out.print("+");
                print(sumValue.getOperand2(), out);
                out.print(")");
                break;
            case Constant.SUBSTRACTION :
                Substraction subValue=(Substraction)value;
                out.print("(");
                print(subValue.getOperand1(), out);
                out.print("-");
                print(subValue.getOperand2(), out);
                out.print(")");
                break;
            case Constant.VARIABLE :
                Variable varValue =(Variable)value;
                out.print("(Variable at address ");
                print(varValue.getIndex(), out);
                out.print(")");
                break;
        }
    }
    
    private void println(String s, PrintStream out, int indent){
        out.print("> ");
        for(int i=0; i<indent; i++){
            out.print("   ");
        }
        out.println(s);
    }
    
    private void print(String s, PrintStream out, int indent){
        out.print("> ");
        for(int i=0; i<indent; i++){
            out.print("   ");
        }
        out.print(s);
    }
    
}
