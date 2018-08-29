package com.freelance.android.searchinexpandablelistview;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.freelance.android.searchinexpandablelistview.adapter.ExpandableListAdapter;
import com.freelance.android.searchinexpandablelistview.models.ChildRow;
import com.freelance.android.searchinexpandablelistview.models.ParentRow;

import java.util.ArrayList;
/*import android.widget.SearchView;*/

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private static final String LOG_TAG = MainActivity.class.getName();

    private SearchManager mSearchManager;
    private SearchView mSearchView;
    private ExpandableListAdapter mListAdapter;
    private ExpandableListView mListView;
    private ArrayList<ParentRow> mParentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreate() is called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mParentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();

        // The app will crash if display list isn't called here.
        displayList();

        // This expands the list of continents.
        expandAll();

    }

    private void expandAll() {
        Log.i(LOG_TAG, "TEST: expandAll() is called...");

        int count = mListAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            mListView.expandGroup(i);
        }
    }

    private void displayList() {
        Log.i(LOG_TAG, "TEST: displayList() is called...");

        loadData();

        mListView = this.findViewById(R.id.ExpandableListView_Search);
        mListAdapter = new ExpandableListAdapter(MainActivity.this, mParentList);
        mListView.setAdapter(mListAdapter);
    }

    private void loadData() {
        Log.i(LOG_TAG, "TEST: loadData() is called...");

        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;

        childRows.add(new ChildRow(R.mipmap.ic_launcher,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        childRows.add(new ChildRow(R.mipmap.ic_launcher,
                "Sit Fido, sit."));

        parentRow = new ParentRow("First Group", childRows);
        mParentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow(R.mipmap.ic_launcher,
                "Fido is the name of my dog."));
        childRows.add(new ChildRow(R.mipmap.ic_launcher,
                "Add two plus two."));

        parentRow = new ParentRow("Second Group", childRows);
        mParentList.add(parentRow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(LOG_TAG, "TEST: onCreateOptionsMenu() is called...");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /**
         * solved error name is getActionView() is deprecated.
         * Must changed from import android.support.v7.widget.SearchView; to import android.widget.SearchView;
         * It will works for yr app.
         */
        searchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setSearchableInfo(mSearchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
        mSearchView.requestFocus();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG_TAG, "TEST: onOptionsItemSelected() is called...");

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onClose() {
        Log.i(LOG_TAG, "TEST: onClose() is called...");

        mListAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.i(LOG_TAG, "TEST: onQueryTextSubmit() is called...");

        mListAdapter.filterData(s);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.i(LOG_TAG, "TEST: onQueryTextChange() is called...");

        mListAdapter.filterData(s);
        expandAll();
        return false;
    }
}
