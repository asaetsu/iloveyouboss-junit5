package iloveyouboss;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scratch.AssertTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by asaetsu on 2016/12/12.
 */
public class ScoreCollectionTest {

    private ScoreCollection collection;

    @BeforeEach
    public void create() {
        collection = new ScoreCollection();
    }

    // 2つの数値の算術平均を返す
    @Test
    public void answersArithmeticMeanOfTwoNumbers() {

        collection.add(() -> 5);
        collection.add(() -> 7);

        int actualResult = collection.arithmeticMean();
        assertThat(actualResult).isEqualTo(6);
    }

    @Test
    public void throwsExceptionWhenAddingNull() {
        assertThatThrownBy(() -> {
            collection.add(null);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void answersZeroWhenNoElementsAdded() {
        assertThat(collection.arithmeticMean()).isEqualTo(0);
    }

    @Test
    public void dealsWithIntegerOverflow() {
        collection.add(() -> Integer.MAX_VALUE);
        collection.add(() -> 1);
        assertThat(collection.arithmeticMean() < 0).isTrue();
    }
}
