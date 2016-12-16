package iloveyouboss;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by asaetsu on 2016/12/12.
 */
public class ScoreCollectionTest {

    // 2つの数値の算術平均を返す
    @Test
    public void answersArithmeticMeanOfTwoNumbers() {

        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        int actualResult = collection.arthmeticMean();
        assertEquals(actualResult, 6);

    }

}
