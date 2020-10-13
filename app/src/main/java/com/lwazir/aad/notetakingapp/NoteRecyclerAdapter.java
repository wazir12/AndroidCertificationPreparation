package com.lwazir.aad.notetakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.CustomViewHolder> {
    private final LayoutInflater layoutInflator;
    private final Context mContext;
    List<NoteInfo> mNotes;
    public NoteRecyclerAdapter(Context context, List<NoteInfo> notes) {
        mContext = context;
        layoutInflator = LayoutInflater.from(context);
        mNotes = notes;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = layoutInflator.inflate(R.layout.item_note_list, parent , false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, @SuppressLint("RecyclerView") int i) {
            NoteInfo note =  mNotes.get(i);
            customViewHolder.courseTextView.setText(note.getCourse().getTitle());
            customViewHolder.titleTxtView.setText(note.getTitle());
            customViewHolder.mCurrentPosition = i;
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public final TextView courseTextView;
        public final TextView titleTxtView;
        public int mCurrentPosition;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTextView = (TextView)  itemView.findViewById(R.id.text_course);
            titleTxtView = (TextView) itemView.findViewById(R.id.text_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,NoteActivity.class);
                    intent.putExtra(NoteActivity.NOTE_POSITION,mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
