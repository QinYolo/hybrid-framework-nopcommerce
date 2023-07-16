package pageUIs.jquery.database;

public class HomePageUI {
    public static final String PAGINATION_BY_PAGE_NUMBER = "xpath=//ul[@class='qgrd-pagination-ul']//a[text()='%s']";
    public static final String PAGINATION_PAGE_ACTIVED_BY_PAGE_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
    public static final String HEADER_TEXTBOX_VALUE_DISPLAYED_BY_LABEL = "xpath=//tr[not(@style)]/td[@data-key='%s' and text()='%s']";
    public static final String TOTAL_PAGINATION = "css=li.qgrd-pagination-page";
    public static final String PAGINATION_PAGE_BY_INDEX = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";

    public static final String LOAD_BUTTON = "css=button#load";
    public static final String COLUMN_INDEX_BY_NAME = "xpath=//tr/th[text()='%s']/preceding-sibling::th";
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tr[%s]/td[%s]/input";
    public static final String DROP_DOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tr[%s]/td[%s]//select";
    public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tr[%s]/td[%s]//input[@type='checkbox']";
    public static final String ICON_NAME_BY_ROW_INDEX = "xpath=//tr[%s]//button[@title='%s']";
}
