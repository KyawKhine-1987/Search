package com.freelance.android.searchinexpandablelistview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freelance.android.searchinexpandablelistview.R;
import com.freelance.android.searchinexpandablelistview.models.ChildRow;
import com.freelance.android.searchinexpandablelistview.models.ParentRow;

import java.util.ArrayList;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private static final String LOG_TAG = ExpandableListAdapter.class.getName();

    private Context mContext;
    private ArrayList<ParentRow> mParentRowList;
    private ArrayList<ParentRow> mOriginalList;

    public ExpandableListAdapter(Context context, ArrayList<ParentRow> mOriginalList) {
        Log.i(LOG_TAG, "TEST: ExpandableListAdapter() constructor is called...");

        this.mContext = context;

        this.mParentRowList = new ArrayList<>();
        this.mParentRowList.addAll(mOriginalList);

        this.mOriginalList = new ArrayList<>();
        this.mOriginalList.addAll(mOriginalList);
    }

    @Override
    public int getGroupCount() {
        Log.i(LOG_TAG, "TEST: getGroupCount() is called...");

        return mParentRowList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        Log.i(LOG_TAG, "TEST: getChildrenCount() is called...");

        return mParentRowList.get(i).getmChildRowList().size();
    }

    @Override
    public Object getGroup(int i) {
        Log.i(LOG_TAG, "TEST: getGroup() is called...");

        return mParentRowList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        Log.i(LOG_TAG, "TEST: getChild() is called...");

        return mParentRowList.get(i).getmChildRowList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        Log.i(LOG_TAG, "TEST: getGroupId() is called...");

        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        Log.i(LOG_TAG, "TEST: getChildId() is called...");

        return i1;
    }

    @Override
    public boolean hasStableIds() {
        Log.i(LOG_TAG, "TEST: hasStableIds() is called...");

        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        Log.i(LOG_TAG, "TEST: getGroupView() is called...");

        ParentRow mParentRow = (ParentRow) getGroup(i);

        if (view == null) {
            LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mLayoutInflater.inflate(R.layout.parent_row, null);
        }

        TextView tvHeading = view.findViewById(R.id.tv_ParentText);
        tvHeading.setText(mParentRow.getName().trim());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Log.i(LOG_TAG, "TEST: getChildView() is called...");

        ChildRow mChildRow = (ChildRow) getChild(i, i1);

        if (view == null) {
            LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mLayoutInflater.inflate(R.layout.child_row, null);
        }

        ImageView iv_ChildIcon = view.findViewById(R.id.iv_ChildIcon);
        iv_ChildIcon.setImageResource(R.drawable.ic_search_pink);

        final TextView tvChildTest = view.findViewById(R.id.tv_ChildText);
        tvChildTest.setText(mChildRow.getmText().trim());

        final View finalView = view;
        tvChildTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "TEST: This is View onClick() is called...");

                Toast.makeText(finalView.getContext(), tvChildTest.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        Log.i(LOG_TAG, "TEST: isChildSelectable() is called...");

        return true;
    }

    public void filterData(String query) {
        Log.i(LOG_TAG, "TEST: filterData() is called...");

        query = query.toLowerCase();
        mParentRowList.clear();

        if (query.isEmpty()) {
            mParentRowList.addAll(mOriginalList);
        } else {

            for (ParentRow mParentRow : mOriginalList) {

                ArrayList<ChildRow> mChildList = mParentRow.getmChildRowList();
                ArrayList<ChildRow> mNewList = new ArrayList<ChildRow>();

                for (ChildRow childRow : mChildList) {
                    if (childRow.getmText().toLowerCase().contains(query)) {
                        mNewList.add(childRow);
                    }
                }

                if (mNewList.size() > 0) {
                    ParentRow nParentRow = new ParentRow(mParentRow.getName(), mNewList);
                    mParentRowList.add(nParentRow);
                }
            }
        }
        notifyDataSetChanged();
    }
}
