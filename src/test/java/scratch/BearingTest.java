/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
 ***/
package scratch;

import iloveyouboss.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;



public class BearingTest {

    @Test
    public void throwsOnNegativeNumber() {
        assertThatThrownBy(() -> { new Bearing(-1); }).isInstanceOf(BearingOutOfRangeException.class);
    }

    @Test
    public void throwsWhenBearingTooLarge() {
        assertThatThrownBy(() -> { new Bearing(Bearing.MAX + 1); }).isInstanceOf(BearingOutOfRangeException.class);
    }

    @Test
    public void answersValidBearing() {
        assertThat(new Bearing(Bearing.MAX).equals(Bearing.MAX));
    }

    @Test
    public void answersAngleBetweenItAndAnotherBearing() {
        assertThat(new Bearing(15).angleBetween(new Bearing(12))).isEqualTo(3);
    }

    @Test
    public void angleBetweenIsNegativeWhenThisBearingSmaller() {
        assertThat(new Bearing(12).angleBetween(new Bearing(15))).isEqualTo(-3);
    }
}
