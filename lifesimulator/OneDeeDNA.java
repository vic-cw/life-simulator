/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifesimulator;

import OneDeeDNACode.Code;
import OneDeeDNACode.Interpreter;
import OneDeeDNACode.OneDeeDnaInstruction;
import OneDeeDNACode.SampleCode;
import java.io.PrintStream;
import java.util.Random;

/**
 *
 * @author vic
 */
public class OneDeeDNA {
    
    protected Code code;
    
    
    
    public OneDeeDNA(){
        code=new Code();
        Random random=new Random();
        if(random.nextBoolean()){
            SampleCode.codeEasyWalking(code);
        }
        else {
            SampleCode.codeDumbWalking(code);
        }
    }
    
    public OneDeeDNA(Code c){
        code=c;
    }
    
    public OneDeeDNA duplicate(){
        OneDeeDNA copy=new OneDeeDNA();
        copy.code=new Code();
        for(OneDeeDnaInstruction inst : code){
            copy.code.add(inst.duplicate());
        }
        return copy;
    }
    
    public Code getCode(){
        return code;
    }
    
    // TODO : code print function for DNA
    public void print(PrintStream out){
        out.print("DNA : ");
        if(code.size()>1){
            out.print("dumb");
        } else
            out.print("intelligent");
        out.print(", ");
        
    }
    
    public void println(PrintStream out){
        print(out);
        out.println();
    }
    
    /*
     * Réflexion sur la construction d'un ADN qui puisse évoluer et être modifié par l'ordinateur directement
     * j'ai l'intuition qu'il faut que je définisse un langage, pour que le simulateur puisse modifier aléatoirement le code
     * et ensuite il me faut quelque chose qui lise ce code et l'exécute
     * 
     * Premières idées :
     * On peut voir le DNA de plusieurs manières :
     *  - de manière très large, ça peut être le truc qui définit entièrement comment va se passer la partie iterate d'un Being :
     *      tout ce qu'il va faire le temps d'une période. Pour l'instant on a breed et takeAction
     *  - on peut voir ça de manière plus restreinte, en disant que la reproduction n'est pas régulée par l'ADN
     *  - dans tous les cas, j'ai l'impression qu'il faut définir très précisément ce que peut décider l'ADN et ce auquel il a accès.
     * Comme ça j'aurais l'impression que l'ADN :
     *   - on peut lui donner facilement accès à toutes les données du monde. Lui cacher certaines données se révèle 
     * être un choix de voir ce que ça donne. ça correspondrait plus à une caractéristique du monde.
     *   - dans le même genre, l'ADN doit pouvoir modifier certaines variables. Là aussi c'est un peu les lois de la physique du
     * monde que ça définit. J'ai l'impression qu'une première version à peu près logique serait de dire que l'ADN peut modifier
     * la position du being, éventuellement que d'une unité à la fois. Donc en gros dans une première version, l'ADN serait un code
     * qui une fois interprété, partirait d'un ensemble de données du monde ou du being, et produirait "+1", "0", ou "-1".
     * 
     * Proposition de langage :
     * 
     * Le "code" serait représenté par une liste d'instructions. Les instructions que cela pourrait former seraient :
     * assign(valeur, variable)...
     * en mettant comme mot-clef du langage "1" et "0", et en faisant les opérations + et -, ça nous permet d'avoir n'importe quel
     * int par additions ou soustractions successives.
     * on va partir du principe qu'à chaque moment où le DNA est interprété, on a mis l'ensemble des variables du monde auquel il
     * a accès dans une liste triée. bien sûr la liste sera triée toujours dans le même sens. Comme ça, à l'intérieur du code du
     * DNA, un index "3", fera toujours référence à la variable du monde en troisième position. Si le code appelle un truc au-dessu
     * de la limite, dans ce cas c'est que le code est faux et le being est tué.
     * Du coup quand on lit que le code veut définir une nouvelle variable, il suffit de la placer au-dessus de la pile. le code
     * devra juste appeler le bon numéro pour tomber dessus.
     */
    
    
}


