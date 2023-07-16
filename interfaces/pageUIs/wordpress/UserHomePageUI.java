package pageUIs.wordpress;

public class UserHomePageUI {
    public static final String POST_TILE_TEXT = "xpath=//article//h2[@class='entry-title']/a[text()='%s']";
    public static final String POST_AUTHOR_BY_POST_TITLE = "xpath=//article//h2[@class='entry-title']/a[text()='%s']/parent::h2/following-sibling::div//a[text()='%s']";
    public static final String POST_DATE_BY_POST_TITLE = "xpath=//article//h2[@class='entry-title']/a[text()='%s']/parent::h2/following-sibling::div//time[text()='%s']";
    public static final String POST_BODY_BY_POST_TITLE = "xpath=//article//h2[@class='entry-title']/a[text()='%s']/ancestor::header/following-sibling::div/p[text()='%s']";
    public static final String SEARCH_TEXTBOX = "css=div.wp-block-search__inside-wrapper>input";
    public static final String SEARCH_BUTTON = "css=div.wp-block-search__inside-wrapper>button";
}
