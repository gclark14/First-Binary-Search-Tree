package dna.rosalind;

import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by gabe.clark on 6/29/17.
 */
public class AdderTest {

    private static Adder adder = new Adder();

    @Test
    public void testAddition(){
        assertThat(adder.add(2,2), is(4));
    }

    @Test
    public void add_should_return_null_when_operand_is_null(){
        assertNull(adder.add("foo", null));
        assertNull(adder.add(null, "foo"));
    }

    @Test
    public void add_should_concatenate_strings(){
        assertThat(adder.add("foo","bar"), is("foobar"));
    }

}