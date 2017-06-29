package dna.rosalind;

/**
 * Created by gabe.clark on 6/29/17.
 */
public class Adder {

    public int add(int a, int b){
        return a + b;
    }

    public String add(String a, String b){
        if(a == null || b == null)
            return null;

        StringBuilder builder = new StringBuilder();
        return builder.append(a).append(b).toString();
    }

}
