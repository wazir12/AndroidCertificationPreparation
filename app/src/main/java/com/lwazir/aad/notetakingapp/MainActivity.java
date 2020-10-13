package com.lwazir.aad.notetakingapp;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private  NoteRecyclerAdapter recyclerAdapter;
    private LinearLayoutManager notesLinearLayoutManager;
    private RecyclerView recycler_view;
    private CourseRecyclerAdapter coursesRecyclerAdapter;
    private GridLayoutManager gridLayoutManager;

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
        //Initialize Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeDisplayContent();
    }
    @Override
    protected void onResume() {
        super.onResume();
        recyclerAdapter.notifyDataSetChanged();
        updateNavHeader();
      //  mAdapterNotes.notifyDataSetChanged();
    }

    private void updateNavHeader() {
        NavigationView view = (NavigationView) findViewById(R.id.nav_view);
        View headerView = view.getHeaderView(0);
        TextView user_name = headerView.findViewById(R.id.user_name);
        TextView email_id = headerView.findViewById(R.id.email_id);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
       String uName = pref.getString("display_name","");
       String emailId = pref.getString("email_address","");
        email_id.setText(emailId);
        user_name.setText(uName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        recycler_view = (RecyclerView) findViewById(R.id.list_notes);
        notesLinearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this,getResources().getInteger(R.integer.courses_grid_span));

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        recyclerAdapter =new NoteRecyclerAdapter(this, notes);
        displayNotes(recycler_view);
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        coursesRecyclerAdapter = new CourseRecyclerAdapter(this,courses);


    }
    private void displayCourses(){
        recycler_view.setLayoutManager(gridLayoutManager);
        recycler_view.setAdapter(coursesRecyclerAdapter);
        selectNavigationMenuItem(R.id.nav_courses);

    }

    private void displayNotes(RecyclerView recycler_notes) {
        recycler_notes.setLayoutManager(notesLinearLayoutManager);
        recycler_notes.setAdapter(recyclerAdapter);
        selectNavigationMenuItem(R.id.nav_notes);
    }

    private void selectNavigationMenuItem(int id) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(id).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_notes) {
            //handleSelection("Notes");
           displayNotes(recycler_view);
        } else if (id == R.id.nav_courses) {
            //handleSelection("Courses");
            displayCourses();
        } else if (id == R.id.nav_share) {
           handleSelection("Don't you think you've shared enough");
        } else if (id == R.id.nav_send) {
            handleSelection("Send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleSelection(String message) {
        View view = findViewById(R.id.list_notes);
        final Snackbar snackbar = Snackbar.make(view,message,Snackbar.LENGTH_LONG);
        snackbar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              snackbar.dismiss();
            }
        }).show();
    }



}