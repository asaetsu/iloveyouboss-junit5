package scratch;

/**
 * Created by asaetsu on 2017/01/28.
 */
import org.assertj.core.api.Assertions;

public class RectangleAssert extends Assertions {
    public static RectangleSidesAssert assertThat(Rectangle actual) {
        return new RectangleSidesAssert(actual);
    }
}