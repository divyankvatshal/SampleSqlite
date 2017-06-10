package com.jssaten.sqlitesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jssaten.sqlitesample.adapter.MyAdapter;
import com.jssaten.sqlitesample.bean.Student;
import com.jssaten.sqlitesample.helper.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> mySpinnerItems = new ArrayList<>();
    Spinner spinner;
    EditText enroll;
    EditText name;
    EditText marks;
    EditText graduation;
    Button add;
    Button show;

    DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking components
        spinner = (Spinner) findViewById(R.id.spinner);
        enroll = (EditText) findViewById(R.id.etenroll);
        name = (EditText) findViewById(R.id.studentName);
        marks = (EditText) findViewById(R.id.marks);
        graduation = (EditText) findViewById(R.id.graduation);
        add = (Button) findViewById(R.id.btnAdd);
        show = (Button)findViewById(R.id.btnshow);
        //populating spinner items

        mySpinnerItems.add("A");
        mySpinnerItems.add("B");
        mySpinnerItems.add("C");

        //attaching list to spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mySpinnerItems);
        //setting adapter
        spinner.setAdapter(adapter);

        //initialising dataprovider

        dataProvider = new DataProvider(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enrollment = enroll.getText().toString();
                String studentName = name.getText().toString();
                String studentMarks = marks.getText().toString();
                String grad = graduation.getText().toString();
                String section = (String)spinner.getSelectedItem();

              //  dataProvider.insertData(studentName,Integer.parseInt(enrollment),section,Float.parseFloat(studentMarks),grad);

                //Second method;

                Student student =  new Student();
                student.setName(studentName);
                //For bypassing the primary constraint
                student.setEnrollment(Integer.parseInt(enrollment) + 1);
                student.setMarks(Float.parseFloat(studentMarks));
                student.setSection(section);
                student.setGraduation(grad);
                dataProvider.insertData(student);

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShowData.class));
            }
        });




    }

}
