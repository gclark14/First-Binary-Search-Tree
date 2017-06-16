package binarySearchTree;

/**
 * Created by gabe.clark on 6/14/17.
 */
public class ValueOutOfRangeException extends RuntimeException {
    public ValueOutOfRangeException(){
        super("Search value not contained within tree");
    }
    public ValueOutOfRangeException(int key){
        super(String.valueOf(key) + " not contained within tree");
    }
}
