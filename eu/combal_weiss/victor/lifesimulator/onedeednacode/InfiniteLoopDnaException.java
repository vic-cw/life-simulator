
package eu.combal_weiss.victor.lifesimulator.onedeednacode;

public class InfiniteLoopDnaException extends Exception {

    /**
     * Creates a new instance of <code>InfiniteLoopDNA</code> without detail message.
     */
    public InfiniteLoopDnaException() {
    }

    /**
     * Constructs an instance of <code>InfiniteLoopDNA</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InfiniteLoopDnaException(String msg) {
        super(msg);
    }
}
