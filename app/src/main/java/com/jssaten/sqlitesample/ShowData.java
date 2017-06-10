package com.jssaten.sqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jssaten.sqlitesample.adapter.MyAdapter;
import com.jssaten.sqlitesample.bean.Student;
import com.jssaten.sqlitesample.helper.DataProvider;

import java.util.List;

public class ShowData extends AppCompatActivity {

    List<Student> studentList;
    MyAdapter myAdapter;
    RecyclerView recyclerView;

    DataProvider dataProvider;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        //Linking
        recyclerView = (RecyclerView) findViewById(R.id.studentList);


        dataProvider = new DataProvider(this);

        studentList = dataProvider.getAllStudents();

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        myAdapter = new MyAdapter(studentList,this);

        recyclerView.setAdapter(myAdapter);




    }
}
