package dna.rosalind;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by gabe.clark on 6/29/17.
 */
public class AdderTest {

    private static Adder adder = new Adder();

    @Test
    public void testAddition(){
        assertThat(adder.add(2,2), is(4));
    }

}