package dna.rosalind;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by gabe.clark on 6/29/17.
 */
public class MultiplierTest {
    Multiplier multiplier = new Multiplier();

    @Test
    public void multiply_should_return_product_given_two_integers(){
        assertThat(multiplier.multiply(2,2), is(4));
    }

    @Test
    public void multiply_should_return_product_given_two_doubles(){
        assertThat(multiplier.multiply(1.5,2.0), is(3.0));
    }

    @Test
    public void multiply_should_return_product_given_three_real_numbers(){
        assertThat(multiplier.multiply(1.0,1.0,2.0), is(2.0));
    }

}