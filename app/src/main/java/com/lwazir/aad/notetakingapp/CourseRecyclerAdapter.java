package com.lwazir.aad.notetakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.CustomViewHolder> {
    private final LayoutInflater layoutInflator;
    private final Context mContext;
    List<CourseInfo> mCourses;
    public CourseRecyclerAdapter(Context context, List<CourseInfo> courses) {
        mContext = context;
        layoutInflator = LayoutInflater.from(context);
        mCourses = courses;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = layoutInflator.inflate(R.layout.item_course_list, parent , false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, @SuppressLint("RecyclerView") int i) {
            CourseInfo course =  mCourses.get(i);
            customViewHolder.courseTextView.setText(course.getTitle());
            //customViewHolder.titleTxtView.setText(note.getTitle());
            customViewHolder.mCurrentPosition = i;
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public final TextView courseTextView;
        public int mCurrentPosition;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTextView = (TextView)  itemView.findViewById(R.id.text_course);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view,mCourses.get(mCurrentPosition).getTitle(),Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }
}
