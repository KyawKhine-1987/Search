package com.freelance.android.searchinexpandablelistview.models;

public class ChildRow {

    private int mIcon;
    private String mText;

    public ChildRow(int mIcon, String mText) {
        this.mIcon = mIcon;
        this.mText = mText;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
