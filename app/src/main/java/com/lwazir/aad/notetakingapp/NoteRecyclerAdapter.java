package com.lwazir.aad.notetakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwazir.aad.notetakingapp.SQLiteDB.NoteDatabase.NoteInfoEntry;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.CustomViewHolder> {
    private final LayoutInflater layoutInflator;
    private final Context mContext;
   // List<NoteInfo> mNotes;
    private Cursor mCursor;
    private int mCoursePos;
    private int mNoteTitlePos;
    private int mIdPos;

    public NoteRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        layoutInflator = LayoutInflater.from(context);
        mCursor = cursor;

        populateColumnPositions();
    }

    public void changeCursor(Cursor cursor){
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        populateColumnPositions();
        notifyDataSetChanged();
    }
    private void populateColumnPositions() {
        if(mCursor == null)
            return;

        //get Column Indexes from cursor
        mCoursePos = mCursor.getColumnIndex(NoteInfoEntry.COLUMN_COURSE_ID);
        mNoteTitlePos = mCursor.getColumnIndex(NoteInfoEntry.COLUMN_NOTE_TITLE);
        mIdPos = mCursor.getColumnIndex(NoteInfoEntry._ID);

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = layoutInflator.inflate(R.layout.item_note_list, parent , false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int i) {
           // NoteInfo note =  mNotes.get(i);
        mCursor.moveToPosition(i);
        String course = mCursor.getString(mCoursePos);
        String noteTitle = mCursor.getString(mNoteTitlePos);
        int id = mCursor.getInt(mIdPos);

        holder.courseTextView.setText(course);
        holder.titleTxtView.setText(noteTitle);
        holder.mId = id;
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public final TextView courseTextView;
        public final TextView titleTxtView;
        public int mId;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTextView = (TextView)  itemView.findViewById(R.id.text_course);
            titleTxtView = (TextView) itemView.findViewById(R.id.text_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,NoteActivity.class);
                    intent.putExtra(NoteActivity.NOTE_ID,mId);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
