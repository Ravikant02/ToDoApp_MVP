package com.ravikant.todo_mvp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravikant on 25/2/16.
 */
public class GroupAdapter extends BaseExpandableListAdapter {
    private Context _context;

    public GroupAdapter(Context context) {
        this._context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        //final String childText = (String) getChild(groupPosition, childPosition);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;

        /*return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();*/
    }

    @Override
    public Object getGroup(int groupPosition) {

        return null;

        //return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return 0;

        //return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
