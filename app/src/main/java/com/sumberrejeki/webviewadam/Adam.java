package com.sumberrejeki.webviewadam;


public class Adam {
    private String mButtonTitle, mHtmlToLoad;

    public Adam() {
    }

    public Adam(String ButtonTitle, String HtmlToLoad) {
        this.mButtonTitle = ButtonTitle;
        this.mHtmlToLoad = HtmlToLoad;
    }

    public String getButtonTitle() {
        return mButtonTitle;
    }

    public String getHtmlToLoad() {
        return mHtmlToLoad;
    }
}
