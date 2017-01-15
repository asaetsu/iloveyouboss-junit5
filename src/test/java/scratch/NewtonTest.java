package scratch;

import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.within;

/**
 * Created by asaetsu on 2017/01/15.
 */
public class NewtonTest {

    static class Newton {
        private static final double TOLERANCE = 1E-16;

        public static double squareRoot(double n) {
            // approx == 約
            double approx = n;

            while (abs(approx - n / approx) > TOLERANCE * approx)
                approx = (n / approx + approx) / 2.0;
            return approx;
        }
    }

    @Test
    public void squareRootTest(){
        double result = Newton.squareRoot(250.0);
        assertThat(result*result).isCloseTo(250, within(Newton.TOLERANCE));
    }
}
