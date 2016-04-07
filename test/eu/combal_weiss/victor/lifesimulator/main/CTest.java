package eu.combal_weiss.victor.lifesimulator.main;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Combal-Weiss <vic-cw@users.noreply.github.com>
 */
public class CTest {
    
    
    private ByteArrayOutputStream buffer;
    
    public CTest(){
        buffer = new ByteArrayOutputStream();
    }
    
    private void bufferOutput(){
        System.setOut(new PrintStream(buffer));
    }
    
    private String getBufferContent(){
        return buffer.toString();
    }
    
    private void stopBufferingOutput(){
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
    
    private String resetBuffer(){
        String content = buffer.toString();
        buffer.reset();
        return content;
    }
    
    @After
    public void reset(){
        stopBufferingOutput();
        resetBuffer();
        C.level = 0;
    }

    /**
     * Test of append(String) method, of class C, when nothing has been outputted yet.
     */
    @Test
    public void testAppend_StringArgumentEmptyStream() {
        
        String contentToAdd = "A cow has ";
        bufferOutput();
        
        C.append(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                contentToAdd+", actual : "+result+"\n",
                contentToAdd,
                result
                );
    }
    
    /**
     * Test of append(String) method, of class C, when something has already been outputted.
     */
    @Test
    public void testAppend_StringArgumentNonEmptyStream(){
        
        String initialContent = "A cow has ";
        String contentToAdd = "four legs";
        String expected = initialContent+contentToAdd;
        bufferOutput();
        System.out.print(initialContent);
        
        C.append(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
        
    }

    /**
     * Test of appendln(String) method, of class C, when nothing has been outputted yet.
     */
    @Test
    public void testAppendln_StringArgumentEmptyStream() {
        
        String contentToAdd = "A cow has four legs";
        String expected = contentToAdd+"\n";
        bufferOutput();
        
        C.appendln(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.appendln didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
        
    }
     
    /**
     * Test of appendln(String) method, of class C, when something has already been outputted.
     */
    @Test
    public void testAppendln_StringArgumentNonEmptyStream(){
        
        String initialContent = "A cow has four legs";
        String contentToAdd = "A horse as well";
        String expected = initialContent+contentToAdd+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.appendln(contentToAdd);
        
        stopBufferingOutput();
        String result = getBufferContent();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }

    /**
     * Test of append(int) method, of class C, when nothing has been outputted yet.
     */
    @Test
    public void testAppend_IntArgumentEmptyStream() {
        
        int contentToAdd = 42;
        String expected = new Integer(contentToAdd).toString();
        bufferOutput();
        
        C.append(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct int. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of append(int) method, of class C, when something has already been 
     * outputted.
     */
    @Test
    public void testAppend_IntArgumentNonEmptyStream(){
        
        String initialContent = "The answer is ";
        int contentToAdd = 42;
        String expected = initialContent+(new Integer(contentToAdd).toString());
        bufferOutput();
        System.out.print(initialContent);
        
        C.append(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct int. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }

    /**
     * Test of appendln(int) method, of class C, when nothing has been outputted yet.
     */
    @Test
    public void testAppendln_IntArgumentEmptyString() {
        int contentToAdd = 42;
        String expected = new Integer(contentToAdd).toString()+"\n";
        bufferOutput();
        
        C.appendln(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct int. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of appendln(int) method, of class C, when something has already
     * been outputted.
     */
    @Test
    public void testAppendln_IntArgumentNonEmptyString(){
        String initialContent = "The answer is ";
        int contentToAdd = 42;
        String expected = initialContent+(new Integer(contentToAdd).toString())+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.appendln(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct int. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }

    /**
     * Test of print(String) method, of class C, when indentation level is set to 0
     * and nothing has been outputted yet.
     */
    @Test
    public void testPrint_StringArgumentNoIndentationEmptyStream() {
        C.level = 0;
        String contentToAdd = "A cow has four legs";
        bufferOutput();
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                contentToAdd+", actual : "+result+"\n",
                contentToAdd,
                result
                );
    }
    
    /**
     * Test of print(String) method, of class C, when indentation level is set to 0
     * and something has already been outputted.
     */
    @Test
    public void testPrint_StringArgumentNoIndentationNonEmptyStream(){
        C.level = 0;
        String initialContent = "A cow has ";
        String contentToAdd = "four legs";
        String expected = initialContent+contentToAdd;
        bufferOutput();
        System.out.print(initialContent);
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(String) method, of class C, when indentation level is set to 1
     * and nothing has been outputted yet.
     */
    @Test
    public void testPrint_StringArgumentSimpleIndentationEmptyStream(){
        C.level = 1;
        String contentToAdd = "A cow has four legs";
        String expected = "   "+contentToAdd;
        bufferOutput();
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(String) method, of class C, when indentation level is set to 1
     * and something has already been outputted.
     */
    @Test
    public void testPrint_StringArgumentSimpleIndentationNonEmptyStream(){
        C.level = 1;
        String initialContent = "A cow has ";
        String contentToAdd = "four legs";
        String expected = initialContent+"   "+contentToAdd;
        bufferOutput();
        System.out.print(initialContent);
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(String) method, of class C, when indentation level is set to 3
     * and nothing has been outputted yet.
     */
    @Test
    public void testPrint_StringArgumentLongIndentationEmptyStream(){
        C.level = 3;
        String contentToAdd = "A cow has four legs";
        String expected = "   "+"   "+"   "+contentToAdd;
        bufferOutput();
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(String) method of class C, when indentation level is set to 3, 
     * and something has already been outputted.
     */
    @Test
    public void testPrint_StringArgumentLongIndentationNonEmptyStream(){
        C.level = 3;
        String initialContent = "A cow has ";
        String contentToAdd = "four legs";
        String expected = initialContent+"   "+"   "+"   "+contentToAdd;
        bufferOutput();
        System.out.print(initialContent);
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }

    /**
     * Test of println(String) method, of class C, when indentation level is set to 0 and
     * nothing has been outputted yet.
     */
    @Test
    public void testPrintln_StringArgumentNoIndentationEmptyStream() {
        C.level = 0;
        String contentToAdd = "A cow has four legs";
        String expected = contentToAdd+"\n";
        bufferOutput();
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(String) method, of class C, when indentation level is set to 0 and
     * something has already been outputted.
     */
    @Test
    public void testPrintln_StringArgumentNoIndentationNonEmptyStream(){
        C.level = 0;
        String initialContent = "A cow has ";
        String contentToAdd = "four legs";
        String expected = initialContent+contentToAdd+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(String) method, of class C, when indentation level is set to 1 and
     * nothing has been outputted yet.
     */
    @Test
    public void testPrintln_StringArgumentSimpleIndentationEmptyStream(){
        C.level = 1;
        String contentToAdd = "A cow has four legs";
        String expected = "   "+contentToAdd+"\n";
        bufferOutput();
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(String) method, of class C, when indentation level is set to 1 and
     * something has already been outputted.
     */
    @Test
    public void testPrintln_StringArgumentSimpleIndentationNonEmptyStream(){
        C.level = 1;
        String initialContent = "A cow has ";
        String contentToAdd = "four legs";
        String expected = initialContent+"   "+contentToAdd+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(String) method, of class C, when indentation level is set to 3 and 
     * nothing has been outputted yet.
     */
    @Test
    public void testPrintln_StringArgumentLongIndentationEmptyStream(){
        C.level = 3;
        String contentToAdd = "A cow has four legs";
        String expected = "   "+"   "+"   "+contentToAdd+"\n";
        bufferOutput();
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(String) method, of class C, when indentation level is set to 3 and
     * something has already been outputted.
     */
    @Test
    public void testPrintln_StringArgumentLongIndentationNonEmptyStream(){
        C.level = 3;
        String initialContent = "A cow has ";
        String contentToAdd = "four legs";
        String expected = initialContent+"   "+"   "+"   "+contentToAdd+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }

    /**
     * Test of print(int) method, of class C, when indentation level is set to 0
     * and nothing has been outputted yet.
     */
    @Test
    public void testPrint_IntArgumentNoIndentationEmptyStream() {
        C.level = 0;
        int contentToAdd = 68;
        String expected = new Integer(contentToAdd).toString();
        bufferOutput();
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(int) method, of class C, when indentation level is set to 0
     * and something has already been outputted.
     */
    @Test
    public void testPrint_IntArgumentNoIndentationNonEmptyStream(){
        C.level = 0;
        String initialContent = "The answer is ";
        int contentToAdd = 68;
        String expected = initialContent+(new Integer(contentToAdd).toString());
        bufferOutput();
        System.out.print(initialContent);
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(int) method, of class C, when indentation level is set to 1
     * and nothing has been outputted yet.
     */
    @Test
    public void testPrint_IntArgumentSimpleIndentationEmptyStream(){
        C.level = 1;
        int contentToAdd = 68;
        String expected = "   "+new Integer(contentToAdd).toString();
        bufferOutput();
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(int) method, of class C, when indentation level is set to 1
     * and something has already been outputted.
     */
    @Test
    public void testPrint_IntArgumentSimpleIndentationNonEmptyStream(){
        C.level = 1;
        String initialContent = "The answer is ";
        int contentToAdd = 68;
        String expected = initialContent+"   "+new Integer(contentToAdd).toString();
        bufferOutput();
        System.out.print(initialContent);
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(int) method, of class C, when indentation level is set to 3
     * and nothing has been outputted yet.
     */
    @Test
    public void testPrint_IntArgumentLongIndentationEmptyStream(){
        C.level = 3;
        int contentToAdd = 68;
        String expected = "   "+"   "+"   "+new Integer(contentToAdd).toString();
        bufferOutput();
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of print(int) method of class C, when indentation level is set to 3, 
     * and something has already been outputted.
     */
    @Test
    public void testPrint_IntArgumentLongIndentationNonEmptyStream(){
        C.level = 3;
        String initialContent = "The answer is ";
        int contentToAdd = 68;
        String expected = initialContent+"   "+"   "+"   "+new Integer(contentToAdd).toString();
        bufferOutput();
        System.out.print(initialContent);
        
        C.print(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }

    /**
     * Test of println(int) method, of class C, when indentation level is set to 0 and
     * nothing has been outputted yet.
     */
    @Test
    public void testPrintln_IntArgumentNoIndentationEmptyStream() {
        C.level = 0;
        int contentToAdd = 68;
        String expected = new Integer(contentToAdd).toString()+"\n";
        bufferOutput();
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(int) method, of class C, when indentation level is set to 0 and
     * something has already been outputted.
     */
    @Test
    public void testPrintln_IntArgumentNoIndentationNonEmptyStream(){
        C.level = 0;
        String initialContent = "The answer is ";
        int contentToAdd = 68;
        String expected = initialContent+new Integer(contentToAdd).toString()+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(int) method, of class C, when indentation level is set to 1 and
     * nothing has been outputted yet.
     */
    @Test
    public void testPrintln_IntArgumentSimpleIndentationEmptyStream(){
        C.level = 1;
        int contentToAdd = 68;
        String expected = "   "+new Integer(contentToAdd).toString()+"\n";
        bufferOutput();
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(int) method, of class C, when indentation level is set to 1 and
     * something has already been outputted.
     */
    @Test
    public void testPrintln_IntArgumentSimpleIndentationNonEmptyStream(){
        C.level = 1;
        String initialContent = "The answer is ";
        int contentToAdd = 68;
        String expected = initialContent+"   "+new Integer(contentToAdd).toString()+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(int) method, of class C, when indentation level is set to 3 and 
     * nothing has been outputted yet.
     */
    @Test
    public void testPrintln_IntArgumentLongIndentationEmptyStream(){
        C.level = 3;
        int contentToAdd = 68;
        String expected = "   "+"   "+"   "+new Integer(contentToAdd).toString()+"\n";
        bufferOutput();
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }
    
    /**
     * Test of println(int) method, of class C, when indentation level is set to 3 and
     * something has already been outputted.
     */
    @Test
    public void testPrintln_IntArgumentLongIndentationNonEmptyStream(){
        C.level = 3;
        String initialContent = "The answer is ";
        int contentToAdd = 68;
        String expected = initialContent+"   "+"   "+"   "+new Integer(contentToAdd).toString()+"\n";
        bufferOutput();
        System.out.print(initialContent);
        
        C.println(contentToAdd);
        
        stopBufferingOutput();
        String result = resetBuffer();
        
        assertEquals(
                "C.append didn't append the correct string. Expected output : "+
                expected+", actual : "+result+"\n",
                expected,
                result
                );
    }

    /**
     * Test of add method, of class C, when given a sorted array and a 
     * constant same length array.
     */
    @Test
    public void testAdd_SortedReceiverConstantDataSameLength(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {1,1,1,1,1,1,1,1,1,1};
        int[] expected = {1,2,3,4,5,6,7,8,9,10};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array and a 
     * non-constant same length array.
     */
    @Test
    public void testAdd_SortedReceiverNonConstantDataSameLength(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {2,4,6,8,10,12,14,16,18,20};
        int[] expected = {2,5,8,11,14,17,20,23,26,29};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array, and a vector
     * array of same length.
     */
    @Test
    public void testAdd_SortedReceiverVectorDataSameLength(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {0,1,0,0,0,0,0,0,0,0};
        int[] expected = {0,2,2,3,4,5,6,7,8,9};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given an unsorted array and a non constant
     * array of same length.
     */
    @Test
    public void testAdd_UnsortedReceiverNonConstantDataSameLength(){
        int[] receiver = {1,9,2,8,3,7,4,6,5};
        int[] data = {2,20,4,18,6,16,8,14,10};
        int[] expected = {3,29,6,26,9,23,12,20,15};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array and a 
     * constant array of greater length.
     */
    @Test
    public void testAdd_SortedReceiverConstantDataLarger(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] expected = {1,2,3,4,5,6,7,8,9,10};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array, and a
     * non constant array of greater length
     */
    @Test
    public void testAdd_SortedReceiverNonConstantDataLarger(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30};
        int[] expected = {2,5,8,11,14,17,20,23,26,29};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array and a vector
     * array of greater length.
     */
    @Test
    public void testAdd_SortedReceiverVectorDataLarger(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {0,1,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] expected = {0,2,2,3,4,5,6,7,8,9};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array, and a vector
     * array of greater length, with non-null value out of the scope of first
     * array
     */
    @Test
    public void testAdd_SortedReceiverVectorDataLargerWithValueOutside_NoChange(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {0,0,0,0,0,0,0,0,0,0,0,1,0};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array and a shorter
     * sorted array.
     */
    @Test
    public void testAdd_SortedReceiverSortedDataShorter_NoChange(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {0,1,2,3,4,5,6,7,8};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array and a singleton
     * array.
     */
    @Test
    public void testAdd_SortedReceiverSingletonData_NoChange(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {1};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a sorted array and an empty array.
     */
    @Test
    public void testAdd_SortedReceiverEmptyData_NoChange(){
        int[] receiver = {0,1,2,3,4,5,6,7,8,9};
        int[] data = {};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given an unsorted array and a shorter
     * array.
     */
    @Test
    public void testAdd_UnsortedReceiverSortedDataShorter_NoChange(){
        int[] receiver = {1,9,2,8,3,7,4,6,5};
        int[] data = {2,3,4};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    
    /**
     * Test of add method, of class C, when given two singleton arrays.
     */
    @Test
    public void testAdd_SingletonReceiverSingletonData(){
        int[] receiver = {1};
        int[] data = {2};
        int[] expected = {3};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a singleton and a larger array.
     */
    @Test
    public void testAdd_SingletonReceiverLargerData(){
        int[] receiver = {1};
        int[] data = {2,4};
        int[] expected = {3};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(expected)+
                " and is "+Arrays.toString(receiver), 
            expected, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given a singleton array and an empty array.
     */
    @Test
    public void testAdd_SingletonArrayEmptyData_NoChange(){
        int[] receiver = {1};
        int[] data = {};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given an emtpy array and a singleton.
     */
    @Test
    public void testAdd_EmptyReceiverSingletonData_NoChange(){
        int[] receiver = {};
        int[] data = {2};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given 2 empty arrays.
     */
    @Test
    public void testAdd_EmptyReceiverEmptyData_NoChange(){
        int[] receiver = {};
        int[] data = {};
        int[] receiverRecord = receiver.clone();
        
        C.add(receiver, data);
        
        Assert.assertArrayEquals(
            "Wrong result for C.add when adding "+Arrays.toString(data)+" to "+
                Arrays.toString(receiverRecord)+" : should be "+Arrays.toString(receiverRecord)+
                " and is "+Arrays.toString(receiver), 
            receiverRecord, 
            receiver);
    }
    
    /**
     * Test of add method, of class C, when given empty array and null as 
     * second parameter. Test that exception is thrown.
     */
    @Test
    public void testAdd_EmptyReceiverNullData_ThrowException(){
        int[] existingArray = {};
        int[] nullArray = null;
        
        try {
            C.add(existingArray, nullArray);
            fail("Should throw exception when trying to add null to "+
                    Arrays.toString(existingArray));
        }
        catch(NullPointerException e){
            assertNull("Wrong message of exception thrown by adding null to "+
                    Arrays.toString(existingArray)+" : "+e.getMessage(),
                    e.getMessage());
        }
    }
    
    /**
     * Test of add method, of class C, when given singleton and 
     * null as second parameter. Test that exception is thrown.
     */
    @Test
    public void testAdd_SingletonReceiverNullData_ThrowException(){
        int[] existingArray = {10};
        int[] nullArray = null;
        
        try {
            C.add(existingArray, nullArray);
            fail("Should throw exception when trying to add null to "+
                    Arrays.toString(existingArray));
        }
        catch(NullPointerException e){
            assertNull("Wrong message of exception thrown by adding null to "+
                    Arrays.toString(existingArray)+" : "+e.getMessage(),
                    e.getMessage());
        }
    }
    
    /**
     * Test of add method, of class C, when given normal array and null as 
     * second parameter. Test that throws exception.
     */
    @Test
    public void testAdd_NormalReceiverNullData_ThrowException(){
        int[] existingArray = {0,1,2};
        int[] nullArray = null;
        
        try {
            C.add(existingArray, nullArray);
            fail("Should throw exception when trying to add null to "+
                    Arrays.toString(existingArray));
        }
        catch(NullPointerException e){
            assertNull("Wrong message of exception thrown by adding null to "+
                    Arrays.toString(existingArray)+" : "+e.getMessage(),
                    e.getMessage());
        }
    }
    
    /**
     * Test of add method, of class C, when given an empty array and null. Test
     * that doesn't modify arrays.
     */
    @Test
    public void testAdd_EmptyReceiverNullData_PreserveArrays(){
        int[] existingArray = {};
        int[] nullArray = null;
        int[] existingArrayRecord = existingArray.clone();

        try {
            C.add(existingArray, nullArray);
        }
        catch(NullPointerException e){
            // Nothing to do
        }
        
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
    
    /**
     * Test of add method, of class C, when given a singleton and null.
     * Test that arrays are not modified.
     */
    @Test
    public void testAdd_SingletonReceiverNullData_PreserveArrays(){
        int[] existingArray = {10};
        int[] nullArray = null;
        int[] existingArrayRecord = existingArray.clone();

        try {
            C.add(existingArray, nullArray);
        }
        catch(NullPointerException e){
            // Nothing to do
        }
        
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
    
    /**
     * Test of add method, of class C, when given a normal array and null.
     * Test that arrays are preserved.
     */
    @Test
    public void testAdd_NormalReceiverNullData_PreserveArrays(){
        int[] existingArray = {0,1,2};
        int[] nullArray = null;
        int[] existingArrayRecord = existingArray.clone();

        try {
            C.add(existingArray, nullArray);
        }
        catch(NullPointerException e){
            // Nothing to do
        }
        
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
    
    /**
     * Test of add method, of class C, when given null as first parameter and
     * an empty array. Test that throws exception.
     */
    @Test
    public void testAdd_NullReceiverEmptyData_ThrowException(){
        int[] nullArray = null;
        int[] existingArray = {};
        
        try {
            C.add(nullArray, existingArray);
            fail("Should throw exception when trying to add "+
                    Arrays.toString(existingArray)+" to null");
        }
        catch(NullPointerException e){
            assertNull("Wrong message of exception thrown by adding "+
                    Arrays.toString(existingArray)+" to null : "+e.getMessage(),
                    e.getMessage());
        }
    }
    
    /**
     * Test of add method, of class C, when given null as first parameter and
     * a singleton. Test that throws exception.
     */
    @Test
    public void testAdd_NullReceiverSingletonData_ThrowException(){
        int[] nullArray = null;
        int[] existingArray = {10};
        
        try {
            C.add(nullArray, existingArray);
            fail("Should throw exception when trying to add "+
                    Arrays.toString(existingArray)+" to null");
        }
        catch(NullPointerException e){
            assertNull("Wrong message of exception thrown by adding "+
                    Arrays.toString(existingArray)+" to null : "+e.getMessage(),
                    e.getMessage());
        }
    }
    
    /**
     * Test of add method, of class C, when given null as first parameter and
     * a normal array. Test that throws exception.
     */
    @Test
    public void testAdd_NullReceiverNormalData_ThrowException(){
        int[] nullArray = null;
        int[] existingArray = {0,1,2};
        
        try {
            C.add(nullArray, existingArray);
            fail("Should throw exception when trying to add "+
                    Arrays.toString(existingArray)+" to null");
        }
        catch(NullPointerException e){
            assertNull("Wrong message of exception thrown by adding "+
                    Arrays.toString(existingArray)+" to null : "+e.getMessage(),
                    e.getMessage());
        }
    }
    
    /**
     * Test of add method, of class C, when given null as first parameter and
     * an empty array. Test that arrays are not modified.
     */
    @Test
    public void testAdd_NullReceiverEmptyData_PreserveArrays(){
        int[] nullArray = null;
        int[] existingArray = {};
        int[] existingArrayRecord = existingArray.clone();

        try {
            C.add(nullArray, existingArray);
        }
        catch(NullPointerException e){
            // Nothing to do
        }
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
    
    /**
     * Test of add method, of class C, when given null as first parameter and
     * a singleton. Test that arrays are not modified.
     */
    @Test
    public void testAdd_NullReceiverSingletonData_PreserveArrays(){
        int[] nullArray = null;
        int[] existingArray = {10};
        int[] existingArrayRecord = existingArray.clone();

        try {
            C.add(nullArray, existingArray);
        }
        catch(NullPointerException e){
            // Nothing to do
        }
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
    
    /**
     * Test of add method, of class C, when given null as first parameter, and
     * a normal array. Test that arrays are not modified.
     */
    @Test
    public void testAdd_NullReceiverNormalData_PreserveArrays(){
        int[] nullArray = null;
        int[] existingArray = {0,1,2};
        int[] existingArrayRecord = existingArray.clone();

        try {
            C.add(nullArray, existingArray);
        }
        catch(NullPointerException e){
            // Nothing to do
        }
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
    
    /**
     * Test of add method, of class C, when given null as both parameters. Test
     * that throws exception.
     */
    @Test
    public void testAdd_NullReceiverNullData_ThrowException(){
        int[] nullArray = null;
        int[] secondNullArray = null;
        
        try {
            C.add(nullArray, secondNullArray);
            fail("Should throw exception when trying to add null to null");
        }
        catch(NullPointerException e){
            assertNull("Wrong message of exception thrown by adding null to null : "+
                    e.getMessage(),
                    e.getMessage());
        }
    }
    
    /**
     * Test of add method, of class C, when given null as both parameters. Test
     * that arrays are not modified.
     */
    @Test
    public void testAdd_NullReceiverNullData_PreserveArrays(){
        int[] nullArray = null;
        int[] secondNullArray = null;
        
        try {
            C.add(nullArray, secondNullArray);
        }
        catch(NullPointerException e){
            // Nothing to do
        }
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