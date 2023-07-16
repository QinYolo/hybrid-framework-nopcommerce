package pageUIs.wordpress;

public class UserPostDetailUI {
    public static final String POST_TITLE_TEXT = "xpath=//article//h1[text()='%s']";
    public static final String POST_AUTHOR_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/following-sibling::div//a[text()='%s']";
    public static final String POST_DATE_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/following-sibling::div//time[text()='%s']";
    public static final String POST_BODY_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/parent::header/following-sibling::div/p[text()='%s']";
}
