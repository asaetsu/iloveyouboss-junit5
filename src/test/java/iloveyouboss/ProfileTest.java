package iloveyouboss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by asaetsu on 2017/03/18.
 */
public class ProfileTest {

    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;
    private BooleanQuestion questionReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;
    private Answer answerReimbursesTuition;
    private Criteria criteria;

    @BeforeEach
    public void createCriteria() {
        criteria = new Criteria();
    }

    @BeforeEach
    public void createProfile() {
        profile = new Profile();
    }

    @BeforeEach
    public void createQuestionsAndAnswers() {
        questionIsThereRelocation =
                new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation =
                new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation =
                new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, Bool.TRUE);
    }

    // 該当する回答がプロフィールに含まれる場合にはマッチングは成功する
    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerThereIsRelocation);
        Criterion criterion =
                new Criterion(answerThereIsRelocation, Weight.Important);
        boolean result = profile.matches(criterion);

        assertThat(result).isTrue();
    }

    // 該当する回答がない場合にマッチングは失敗する
    @Test
    public void doesNotMatchWhenNoMatchingAnswer() {
        profile.add(answerThereIsNotRelocation);
        Criterion criterion =
                new Criterion(answerThereIsRelocation, Weight.Important);
        boolean result = profile.matches(criterion);

        assertThat(result).isFalse();
    }

    // 複数の回答が含まれる場合にマッチングは成功する
    @Test
    public void matchesWhenContainsMultipleAnswers() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion =
                new Criterion(answerThereIsRelocation, Weight.Important);
        boolean result = profile.matches(criterion);

        assertThat(result).isTrue();
    }

    @Test
    public void matchesWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));
        assertThat(profile.matches(criteria)).isTrue();
    }

    @Test
    public void doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.MustMatch));
        assertThat(profile.matches(criteria)).isFalse();
    }

    @Test
    public void matchesWhenCriterionIsDontCare() {
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion =
                new Criterion(answerReimbursesTuition, Weight.DontCare);
        assertThat(profile.matches(criterion)).isTrue();
    }

    @Test
    public void scoreIsZeroWhenThereAreNoMatches() {
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        ProfileMatch match = profile.match(criteria);
        assertThat(match.getScore()).isEqualTo(0);
    }
}
