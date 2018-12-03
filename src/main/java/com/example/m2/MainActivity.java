package com.example.m2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.m2.database.FlowDatabase;

import java.util.List;

import javax.xml.transform.dom.DOMResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FlowLayout flowLayout;
    private Button mSearch;
    private EditText mEdit;
    private FlowDatabase mFlowDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit = findViewById(R.id.edit_keys);
        mSearch = findViewById(R.id.btn_search);
        flowLayout = findViewById(R.id.flow_history_layout);
        mSearch.setOnClickListener(this);

        //新建一个数据库
        mFlowDB = new FlowDatabase(this);
        //查询搜索历史
        List<String> data = mFlowDB.query();
        for (int i=0;i<data.size();i++){

            //流失布局添加历史
            flowLayout.addTextView(data.get(i));
        }
    }

    @Override
    public void onClick(View v) {
        //这个是点击搜索按钮
            if (v.getId()==R.id.btn_search){
                String keys = mEdit.getText().toString();
                //数据库插入搜索历史
                mFlowDB.insert(keys);

                //流失布局添加搜索内容
                flowLayout.addTextView(keys);
            }else {
                //移除所有的view
                flowLayout.removeAllViews();
                //数据库清空所有数据
                mFlowDB.delete();
            }
    }
}
