package pageUIs.liveguru;

public class AdminPanelPageUI {
    public static final String CLOSE_BUTTON_POPUP = "css=div.message-popup-head>a";
    public static final String NUMBER_COLUMN_BY_NAME = "xpath=//span[text()='%s']/ancestor::th/preceding-sibling::th";
    public static final String TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX = "xpath=//tr[%s]/th[%s]//input";
    public static final String BUTTON_BY_NAME_ABOVE_DATA_GRID = "xpath=//td/button[@title='%s']";
    public static final String SEARCH_RESULT_BY_FULLNAME_AND_EMAIL = "xpath=//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]";
    public static final String ACCOUNT_CHECKBOX_BY_EMAIL = "xpath=//td[contains(text(),'%s')]/preceding-sibling::td/input";
    public static final String ACTIONS_DROPDOWN_MENU = "xpath=//label[text()='Actions']/following-sibling::select";
    public static final String SUBMIT_BUTTON = "xpath=//button[@title='Submit']";
    public static final String SUCCESS_MESSAGE = "css=li.success-msg span";
    public static final String LOADING_MASK = "css=div#loading-mask";
}
