package com.freelance.android.searchinexpandablelistview.models;

import java.util.ArrayList;

public class ParentRow {

    private String name;
    private ArrayList<ChildRow> mChildRowList;

    public ParentRow(String name, ArrayList<ChildRow> mChildRowList) {
        this.name = name;
        this.mChildRowList = mChildRowList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildRow> getmChildRowList() {
        return mChildRowList;
    }

    public void setmChildRowList(ArrayList<ChildRow> mChildRowList) {
        this.mChildRowList = mChildRowList;
    }
}
