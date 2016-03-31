/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.combal_weiss.victor.lifesimulator.main;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vic
 */
public class CounterByPosition extends HashMap<Integer,Integer> {
    
    public void increment(int position){
        int positionInteger=new Integer(position);
        if(this.get(positionInteger)!=null){
            this.put(positionInteger, this.get(positionInteger)+1);
        } else
            this.put(positionInteger, 1);
    }
    
    public void decrement(int position) throws Exception{
        int positionInteger=new Integer(position);
        Integer number=this.get(positionInteger);
        if(number!=null){
            if(number==1){
                remove(positionInteger);
                return;
            }
            if(number<1)
                throw new Exception("trying to decrement a geographic counter with zero");
            put(positionInteger, number-1);
        } else
            throw new Exception("trying to decrement a geographic counter with zero");
    }
    
    public void set(int position, int quantity){
        this.put(new Integer(position), new Integer(quantity));
    }
    
    public int totalItems(){
        int total=0;
        for(Map.Entry<Integer,Integer> e : this.entrySet()){
            total+=e.getValue();
        }
        return total;
    }
    
    public void print(PrintStream out){
        Map.Entry<Integer,Integer>[] entrySet=this.entrySet().toArray(new Map.Entry[0]);
        Arrays.sort(entrySet, new Comparator() {
            @Override
            public int compare(Object t, Object t1) {
                Map.Entry<Integer,Integer> entry1=(Map.Entry<Integer,Integer>) t;
                Map.Entry<Integer,Integer> entry2=(Map.Entry<Integer,Integer>) t1;
                return entry1.getKey()-entry2.getKey();
            }
        });
        for(int i=0; i<entrySet.length; i++)
            out.print(entrySet[i].getKey()+":"+entrySet[i].getValue()+" ");
    }
    
    public void println(PrintStream out){
        print(out);
        out.println();
    }
    
}
