package com.example.myexpandablesplitline;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Toast;


/**
 * Created by admin on 2017/6/5.
 */

public class MyExpandableSpliteLine extends BaseExpandableListAdapter {

    private static String TAG="MyExpandableSpliteLine";

    private BaseExpandableListAdapter elv;
    private int groupline;
    private int childline;
    private Context c;

    public MyExpandableSpliteLine(Context c) {
        this.c = c;
    }

    public MyExpandableSpliteLine(BaseExpandableListAdapter expandableListView) {
        this(expandableListView, R.layout.group_divider, R.layout.child_divider);
    }

    public MyExpandableSpliteLine(BaseExpandableListAdapter expandableListView, int groupline, int childline) {
        this.elv = expandableListView;
        this.groupline = groupline;
        this.childline = childline;
    }


    @Override
    public int getChildTypeCount() {

        return elv.getChildTypeCount() + 1;


    }

    @Override
    public int getGroupTypeCount() {

        return elv.getGroupTypeCount() + 1;
    }


    @Override
    public int getGroupType(int groupPosition) {

        return isView(groupPosition) ? elv.getGroupType(groupPosition / 2) : getGroupTypeCount() - 1;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {

        return isView(groupPosition) && !isView(childPosition) ? elv.getChildType(groupPosition / 2, childPosition / 2) : getChildTypeCount() - 1;
    }

    private boolean isView(int index) {

        return index % 2 == 0;
    }

    @Override
    public int getGroupCount() {

        int groupCount =elv.getGroupCount();

        return groupCount * 2 - 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {


        if (isView(groupPosition)) {
            int childCount = elv.getChildrenCount(groupPosition % 2);
            return childCount * 2;
        } else
            return 0;

    }

    @Override
    public Object getGroup(int groupPosition) {

        if (isView(groupPosition))
            return elv.getGroup(groupPosition / 2);
        else
            return groupPosition;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (isView(groupPosition))
            return elv.getChild(groupPosition / 2, childPosition / 2);
        else
            return "";
    }

    @Override
    public long getGroupId(int groupPosition) {

        return getCombinedGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return elv.hasStableIds();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (isView(groupPosition))
            return elv.getGroupView(groupPosition/2, isExpanded, convertView, parent);

        if (convertView == null && getGroupType(groupPosition) == getGroupTypeCount() - 1)
            return LayoutInflater.from(parent.getContext()).inflate(this.groupline, parent, false);
        else
           // return elv.getGroupView(groupPosition , isExpanded, convertView, parent);
            return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (isView(groupPosition) && !isView(childPosition))
            return elv.getChildView(groupPosition/2, childPosition/2, isLastChild, convertView, parent);

        if (convertView == null && getChildType(groupPosition, childPosition) == getChildTypeCount() - 1)
            return LayoutInflater.from(parent.getContext()).inflate(childline, parent, false);
        else

            return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        Log.i(TAG, " isChildSelectable");
        return elv.isChildSelectable(groupPosition, childPosition);
    }
}
