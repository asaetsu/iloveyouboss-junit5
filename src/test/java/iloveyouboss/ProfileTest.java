package iloveyouboss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by asaetsu on 2016/12/18.
 */
class ProfileTest {

    private Profile profile;
    private Criteria criteria;

    private Question questionReimbursesTuition;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNoRelocation;

    private Question questionOnsiteDaycare;
    private Answer answerNoOnsiteDaycare;
    private Answer answerHasOnsiteDaycare;

    @BeforeEach
    public void createProfile() {
        profile = new Profile("Bull Hockey, Inc.");
    }

    @BeforeEach
    public void createCriteria() {
        criteria = new Criteria();
    }


    @BeforeEach
    public void createQuestionsAndAnswers() {
        questionIsThereRelocation =
                new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation =
                new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNoRelocation =
                new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition =
                new BooleanQuestion(1, "Reimburses tuition?");
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, Bool.TRUE);
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);

        questionOnsiteDaycare =
                new BooleanQuestion(1, "Onsite daycare?");
        answerHasOnsiteDaycare =
                new Answer(questionOnsiteDaycare, Bool.TRUE);
        answerNoOnsiteDaycare =
                new Answer(questionOnsiteDaycare, Bool.FALSE);
    }

    // 必須の条件にマッチしない場合、matchesはfalseを返す
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(
                new Criterion(answerReimbursesTuition, Weight.MustMatch));

        boolean matches = profile.matches(criteria);

        assertFalse(matches);
    }

    // 不問の条件があればmatchesはtrueを返す
    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(
                new Criterion(answerReimbursesTuition, Weight.DontCare));

        boolean matches = profile.matches(criteria);
        assertTrue(matches);
    }

    // 重要な条件がすべて合っていたら、matchesはtrueを返す
    @Test
    public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        boolean matches = profile.matches(criteria);

        assertTrue(matches);
    }

    // 重要な条件がすべて合っていなかったら、matchesはfalseを返す
    @Test
    public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerThereIsNoRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        boolean matches = profile.matches(criteria);

        assertFalse(matches);
    }

    // 条件にあっていなかったら、スコアは０になる
    @Test
    public void scoreIsZeroWhenThereAreNoMatches() {
        profile.add(answerThereIsNoRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        profile.matches(criteria);

        assertEquals(profile.score(), 0);
    }

    // 重要な条件が１つ合っていたら、スコアは重要に設定されているスコアと同じになる
    @Test
    public void scoreIsCriterionValueForSingleMatch() {
        profile.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        profile.matches(criteria);

        assertEquals(profile.score(), Weight.Important.getValue());
    }

    // 重要な条件と好ましい条件が合っていたら、スコアは重要と好ましいに設定されているスコアと同じになる
    @Test
    public void scoreAccumulatesCriterionValuesForMatches() {
        profile.add(answerThereIsRelocation);
        profile.add(answerReimbursesTuition);
        profile.add(answerNoOnsiteDaycare);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.WouldPrefer));
        criteria.add(new Criterion(answerHasOnsiteDaycare, Weight.VeryImportant));

        profile.matches(criteria);

        int expectedScore = Weight.Important.getValue() + Weight.WouldPrefer.getValue();
        assertEquals(profile.score(), expectedScore);
    }
}