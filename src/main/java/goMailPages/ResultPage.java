package goMailPages;

/**
 * Created by alex on 3/16/14.
 */
public class ResultPage {
    static final String NOT_FOUND_MSG = "По данному запросу ничего не найдено";
    static final String LINK_MSG = "перейти на сайт";

    static public String getNotFoundText() {
        return NOT_FOUND_MSG;
    }

    static public String getLinkSuggestedText() {
        return LINK_MSG;
    }
}
