package pageUIs.wordpress;

public class AdminPostSearchUI {
    public static final String ADD_NEW_BUTTON = "xpath=//div[@class='wrap']/a[text()='Add New']";
    public static final String POST_SEARCH_TEXTBOX = "css=#post-search-input";
    public static final String SEARCH_POST_BUTTON = "css=#search-submit";
    public static final String INDEX_COLUMN_BY_ID = "xpath=//table[contains(@class,'table-view-list posts')]//th[@id='%s']/preceding-sibling::*";
    public static final String TABLE_ROW_VALUE_BY_INDEX = "xpath=//table[contains(@class,'table-view-list posts')]/tbody/tr/*[%s]//a[text()='%s']";
    public static final String CHECKBOX_BUTTON_BY_TITLE = "xpath=//a[text()='%s']/ancestor::td/preceding-sibling::th/input";
    public static final String ACTION_SELECT_DROPDOWN_TOP = "css=div.top>div.bulkactions>select";
    public static final String APPLY_ACTION_BUTTON = "css=div.top>div.bulkactions>input.action";
    public static final String POST_STATUS_UPDATE_MESSAGE = "xpath=//div[@id='message']/p[contains(text(),'%s')]";
    public static final String NO_POST_FOUND_MESSAGE = "xpath=//tbody//td[text()='No posts found.']";
}
