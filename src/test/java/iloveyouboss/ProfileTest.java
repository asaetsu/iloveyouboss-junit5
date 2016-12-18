package iloveyouboss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by asaetsu on 2016/12/18.
 */
class ProfileTest {

    // 必須の条件にマッチしない場合、matchesはfalseを返す
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet(){
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "ボーナスは支給されますか？");
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);

        boolean matches = profile.matches(criteria);
        assertFalse(matches);
    }

    // 不問の条件があればmatchesはtrueを返す
    @Test
    public void matchAnswersTrueForAnyDontCareCriteria(){
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "牛乳は支給されますか？");
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
        criteria.add(criterion);

        boolean matches = profile.matches(criteria);
        assertTrue(matches);
    }
}