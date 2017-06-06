package com.example.myexpandablesplitline;

/**
 * Created by admin on 2017/6/5.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;

class SafeCerifyExpandableListView extends BaseExpandableListAdapter {

    private HashMap<Integer, String> mGroupInfoMap = new HashMap<>();
    private HashMap<Integer, String> mChildInfoMap = new HashMap<>();
    private Context c;

    class ItemHolder {

        TextView childNameView;
        TextView childValueView;
    }

    public SafeCerifyExpandableListView(HashMap<Integer, String> mGroupInfoMap,
                                        HashMap<Integer, String> mChildInfoMap ,
                                        Context c) {
        this.mGroupInfoMap = mGroupInfoMap;
        this.mChildInfoMap = mChildInfoMap;
        this.c= c;

    }

    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return mGroupInfoMap.size();
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) { //参数groupPosition表示第几个一级列表
        return mChildInfoMap.get(groupPosition).length();
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {
        return mGroupInfoMap.get(groupPosition);
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildInfoMap.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //固定item的ID
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(c).inflate(R.layout.activity_safecertify_first_item, null);
        }
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group);
        tv_group.setText(mGroupInfoMap.get(groupPosition));

        return convertView;
    }

    //填充二级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ItemHolder holder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(c).inflate(R.layout.activity_safecentify_second_item, null);

            holder = new ItemHolder();
            holder.childNameView = (TextView) convertView.findViewById(R.id.tv_child);
            holder.childValueView = (TextView) convertView.findViewById(R.id.tv_childdata);

            convertView.setTag(holder);

        } else {
            holder = (ItemHolder) convertView.getTag();
        }

        holder.childNameView.setText(mChildInfoMap.get(groupPosition));
        holder.childValueView.setText(mChildInfoMap.get(groupPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}