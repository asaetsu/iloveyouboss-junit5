package iloveyouboss;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asaetsu on 2016/12/16.
 */
public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable scoreable) {
        scores.add(scoreable);
    }

    public int arthmeticMean(){
        int total = scores.stream().mapToInt(Scoreable::getScore).sum();
        return total/ scores.size();
    }

}




