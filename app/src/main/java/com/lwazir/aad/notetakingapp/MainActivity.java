package com.lwazir.aad.notetakingapp;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  NoteRecyclerAdapter recyclerAdapter;

    //private ArrayAdapter<NoteInfo> mAdapterNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }
    @Override
    protected void onResume() {
        super.onResume();
        recyclerAdapter.notifyDataSetChanged();
      //  mAdapterNotes.notifyDataSetChanged();
    }
    private void initializeDisplayContent() {
        //final ListView listNotes = (ListView) findViewById(R.id.list_notes);

        //List<NoteInfo> notes = DataManager.getInstance().getNotes();
        //mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        //listNotes.setAdapter(mAdapterNotes);

        //listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
           // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   Intent intent = new Intent(MainActivity.this, NoteActivity.class);
//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
               // intent.putExtra(NoteActivity.NOTE_POSITION, position);
                //startActivity(intent);
           // }
        //});

        final RecyclerView recycler_notes = (RecyclerView) findViewById(R.id.list_notes);
        LinearLayoutManager notesLinearLayoutManager = new LinearLayoutManager(this);
        recycler_notes.setLayoutManager(notesLinearLayoutManager);
        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        recyclerAdapter =new NoteRecyclerAdapter(this, notes);
        recycler_notes.setAdapter(recyclerAdapter);


    }
}