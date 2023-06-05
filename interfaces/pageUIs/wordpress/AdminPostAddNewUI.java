package pageUIs.wordpress;

public class AdminPostAddNewUI {
	public static final String TITLE_POST_TEXTBOX = "css=h1.editor-post-title";
	public static final String PRE_BODY_POST_TEXTBOX = "css=p.block-editor-default-block-appender__content";
	public static final String BODY_POST_TEXTBOX = "css=p.block-editor-rich-text__editable";
	public static final String PUBLISH_BUTTON = "xpath=//div[@class='edit-post-header__settings']//button[text()='%s']";
	public static final String PRE_PUBLISH_BUTTON = "xpath=//div[@class='editor-post-publish-panel']//button[text()='Publish']";
	public static final String POST_PUBLISHED_BY_MESSAGE = "xpath=//div[@class='components-snackbar']/div[text()='%s']";
}
