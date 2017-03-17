/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
 ***/
package util;

import org.assertj.core.api.AbstractAssert;

public class MatchesAssert extends AbstractAssert<MatchesAssert, Match[]> {

    public MatchesAssert(Match[] actual) {
        super(actual, MatchesAssert.class);
    }

    public MatchesAssert containsMatches(Match[] expected) {
        isNotNull();

        if (actual.length != expected.length) {
            failWithMessage("The size of matches must be <%d>, but was <%d>", expected.length, actual.length);
        }

        for (int i = 0; i < expected.length; i++) {
            if (!equals(expected[i], actual[i])) {
                failWithMessage("At index <%d>, expected Match to be <%s>, but was <%s>", i, expected[i].toString(), actual[i].toString());
            }
        }
        return this;
    }

    private boolean equals(Match expected, Match actual) {
        return expected.searchString.equals(actual.searchString)
                && expected.surroundingContext.equals(actual.surroundingContext);
    }
}
