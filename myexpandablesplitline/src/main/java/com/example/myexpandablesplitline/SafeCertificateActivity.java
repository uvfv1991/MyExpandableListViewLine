package com.example.myexpandablesplitline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by admin on 2017/2/25.
 */

public class SafeCertificateActivity extends AppCompatActivity {

    private class CertifyInfo {

        private String cerify_name;
        private String cerify_value;

        public CertifyInfo(String name, String value) {
            cerify_name = name;
            cerify_value = value;
        }

        public String getCerifyName() {
            return cerify_name;
        }

        public String getCerifyValue() {
            return cerify_value;
        }
    }

    private HashMap<Integer, String> mGroupInfoMap = new HashMap<>();
    private HashMap<Integer, String> mChildInfoMap = new HashMap<>();
    private ExpandableListView expandableListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_certify_layout);

        initData();
        initView();

    }

    private void initGroupData() {

        mGroupInfoMap.clear();
        mGroupInfoMap.put(0, getString(R.string.tv_safe_certify_user));
        mGroupInfoMap.put(1, getString(R.string.tv_safe_certify_user));
        mGroupInfoMap.put(2, getString(R.string.tv_safe_certify_user));
        mGroupInfoMap.put(3, getString(R.string.tv_safe_certify_user));
        mGroupInfoMap.put(4, getString(R.string.tv_safe_certify_user));
    }

    private void initChildData() {


        mChildInfoMap.put(0, getString(R.string.tv_safe_certify_secoend_user));

        mChildInfoMap.put(1, getString(R.string.tv_safe_certify_secoend_user));

        mChildInfoMap.put(2, getString(R.string.tv_safe_certify_secoend_user));

        mChildInfoMap.put(3, getString(R.string.tv_safe_certify_secoend_user));

        mChildInfoMap.put(4, getString(R.string.tv_safe_certify_secoend_user));

        mChildInfoMap.put(5, getString(R.string.tv_safe_certify_secoend_user));
    }

    private void initData() {
        initGroupData();
        initChildData();
    }

    private void initView() {

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setGroupIndicator(null);
        expandableListView.setDivider(null);

        //expandableListView.setAdapter(new SafeCerifyExpandableListView(
        // mGroupInfoMap,mChildInfoMap,this));
        expandableListView.setAdapter(new MyExpandableSpliteLine(new SafeCerifyExpandableListView(
                mGroupInfoMap, mChildInfoMap, this)));

    }


    public void onBackClick(View view) {
        back();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }

        return true;
    }

    private void back() {
        finish();
    }
}
