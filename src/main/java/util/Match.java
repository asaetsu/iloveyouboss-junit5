package util;

/**
 * Created by asaetsu on 2017/03/12.
 */
public class Match {
    public final String searchString;
    public final String surroundingContext;
    public final String searchTitle;

    public Match(String searchTitle, String searchString, String surroundingContext) {
        this.searchTitle = searchTitle;
        this.searchString = searchString;
        this.surroundingContext = surroundingContext;
    }
}