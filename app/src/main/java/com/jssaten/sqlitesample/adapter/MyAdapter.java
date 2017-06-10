package com.jssaten.sqlitesample.adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jssaten.sqlitesample.R;
import com.jssaten.sqlitesample.bean.Student;

import java.util.List;

/**
 * Created by ashwani on 9/6/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<Student> studentList;
    Context context;

    public MyAdapter(List<Student> students, Context context){
        this.studentList = students;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_student,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student student = studentList.get(position);
        String name = student.getName();
        String grad = student.getGraduation();
        String sec = student.getSection();
        String id = Integer.toString(student.getEnrollment());
        String marks = Float.toString(student.getMarks());

        holder.tvid.setText(id);
        holder.tvname.setText(name);
        holder.tvsection.setText(sec);
        holder.tvmarks.setText(marks);
        holder.tvgrad.setText(grad);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvid,tvname,tvgrad,tvsection,tvmarks;

        public MyViewHolder(View itemView){
            super(itemView);
            //Linking components
            tvid = (TextView)itemView.findViewById(R.id.tvid);
            tvgrad = (TextView)itemView.findViewById(R.id.tvgraduation);
            tvmarks = (TextView)itemView.findViewById(R.id.tvmarks);
            tvname = (TextView)itemView.findViewById(R.id.tvname);
            tvsection = (TextView)itemView.findViewById(R.id.tvsection);


        }

    }
}
