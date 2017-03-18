package iloveyouboss;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by asaetsu on 2017/03/18.
 */
public class AnswerTest {
    @Test
    public void matchAgainstNullAnswerReturnsFalse() {
        assertThat(new Answer(new BooleanQuestion(0, ""), Bool.TRUE)
                .match(null)).isFalse();
    }
}
