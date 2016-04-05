package eu.combal_weiss.victor.lifesimulator.main;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Combal-Weiss <vic-cw@users.noreply.github.com>
 */
public class CTest {
    

    /**
     * Test of append method, of class C.
     */
    @Test
    public void testAppend_String() {
        System.out.println("append");
        String s = "";
        C.append(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendln method, of class C.
     */
    @Test
    public void testAppendln_String() {
        System.out.println("appendln");
        String s = "";
        C.appendln(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class C.
     */
    @Test
    public void testAppend_int() {
        System.out.println("append");
        int i = 0;
        C.append(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendln method, of class C.
     */
    @Test
    public void testAppendln_int() {
        System.out.println("appendln");
        int i = 0;
        C.appendln(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class C.
     */
    @Test
    public void testPrint_String() {
        System.out.println("print");
        String s = "";
        C.print(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of println method, of class C.
     */
    @Test
    public void testPrintln_String() {
        System.out.println("println");
        String s = "";
        C.println(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class C.
     */
    @Test
    public void testPrint_int() {
        System.out.println("print");
        int i = 0;
        C.print(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of println method, of class C.
     */
    @Test
    public void testPrintln_int() {
        System.out.println("println");
        int i = 0;
        C.println(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class C.
     */
    @Test
    public void testAdd() {
        
        int[][][] positiveTestCases = {
            {
                {0,1,2,3,4,5,6,7,8,9},
                {1,1,1,1,1,1,1,1,1,1},
                {1,2,3,4,5,6,7,8,9,10}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {2,4,6,8,10,12,14,16,18,20},
                {2,5,8,11,14,17,20,23,26,29}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {0,1,0,0,0,0,0,0,0,0},
                {0,2,2,3,4,5,6,7,8,9}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {0,0,0,0,1,0,0,0,0,0},
                {0,1,2,3,5,5,6,7,8,9}
            },
            {
                {1,9,2,8,3,7,4,6,5},
                {2,20,4,18,6,16,8,14,10},
                {3,29,6,26,9,23,12,20,15}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,2,3,4,5,6,7,8,9,10}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30},
                {2,5,8,11,14,17,20,23,26,29}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,2,2,3,4,5,6,7,8,9}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {0,0,0,0,0,0,0,0,0,0,0,1,0},
                {0,1,2,3,4,5,6,7,8,9}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8,9}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {1},
                {0,1,2,3,4,5,6,7,8,9}
            },
            {
                {0,1,2,3,4,5,6,7,8,9},
                {},
                {0,1,2,3,4,5,6,7,8,9}
            },
            {
                {1,9,2,8,3,7,4,6,5},
                {2,3,4},
                {1,9,2,8,3,7,4,6,5}
            },
            {
                {1},
                {2},
                {3}
            },
            {
                {1},
                {2,4},
                {3}
            },
            {
                {1},
                {},
                {1}
            },
            {
                {},
                {2},
                {}
            },
            {
                {},
                {},
                {}
            }
        };
        for(int i=0; i<positiveTestCases.length; i++){
            int[] receiver = positiveTestCases[i][0];
            int[] data = positiveTestCases[i][1];
            int[] receiverRecord = receiver.clone();
            int[] expected = positiveTestCases[i][2];
            
            C.add(receiver, data);
            Assert.assertArrayEquals(
                "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                    Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                    " and is "+Arrays.toString(receiver), expected, receiver);
        }
        
        
        // Test additions of null, which should trigger an exception
        
        int[][] exceptionTestCases = {
            {},
            {10},
            {0,1,2}
        };
        
        for(int i=0; i<exceptionTestCases.length; i++){
            int[] existingArray = exceptionTestCases[i];
            int[] nullArray = null;
            int[] existingArrayRecord = existingArray.clone();
            
            
            // Test when data is null
            
            try {
                C.add(existingArray, nullArray);
                fail("Should throw exception when trying to add null to "+
                        Arrays.toString(existingArray));
            }
            catch(NullPointerException e){
                assertNull("Wrong message of exception thrown by adding null to "+
                        Arrays.toString(existingArray)+" : "+e.getMessage(),
                        e.getMessage());
                assertArrayEquals(
                        "Array has been modified by addition of null , should be "+
                        Arrays.toString(existingArrayRecord)+", and is : "+
                        Arrays.toString(existingArray),
                        existingArrayRecord,
                        existingArray
                        );
                assertNull(
                        "Array has been modified by addition to "+
                        Arrays.toString(existingArray)+", should be null, is "+
                        Arrays.toString(nullArray),
                        nullArray
                        );
            }
            
            // Test when receiver is null
            
            try {
                C.add(nullArray, existingArray);
                fail("Should throw exception when trying to add "+
                        Arrays.toString(existingArray)+" to null");
            }
            catch(NullPointerException e){
                assertNull("Wrong message of exception thrown by adding "+
                        Arrays.toString(existingArray)+" to null : "+e.getMessage(),
                        e.getMessage());
                assertArrayEquals(
                        "Array has been modified by addition to null, should be "+
                        Arrays.toString(existingArrayRecord)+", and is : "+
                        Arrays.toString(existingArray),
                        existingArrayRecord,
                        existingArray
                        );
                assertNull(
                        "Array has been modified by addition of "+
                        Arrays.toString(existingArray)+", should be null, is "+
                        Arrays.toString(nullArray),
                        nullArray
                        );
            }
            
            // Test when both data and receiver are null
            
            int[] secondNullArray = null;
            try {
                C.add(nullArray, secondNullArray);
                fail("Should throw exception when trying to add null to null");
            }
            catch(NullPointerException e){
                assertNull("Wrong message of exception thrown by adding null to null : "+
                        e.getMessage(),
                        e.getMessage());
                assertNull(
                        "Array has been modified by addition of null to null, should be null, is "+
                        Arrays.toString(nullArray),
                        nullArray
                        );
                assertNull(
                        "Array has been modified by addition of null to null, should be null, is "+
                        Arrays.toString(secondNullArray),
                        secondNullArray
                        );
            }
        }
        
    }
}