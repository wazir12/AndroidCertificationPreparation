package com.lwazir.aad.notetakingapp;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule(MainActivity.class);
    @Test
    public void NextThroughNotes(){
        //TODO: Open navigation Drawer
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        //TODO: Goto Note in Navigation View In Drawer Menu
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes));
        //TODO: Click on the first note is Notes Recycler View
        onView(withId(R.id.list_notes)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        int index = 0;
        for(index=0;index<notes.size();index++){
            NoteInfo note = notes.get(index);
            onView(withId(R.id.spinner_courses)).check(matches(withSpinnerText(note.getCourse().getTitle())));
            onView(withId(R.id.text_note_title)).check((ViewAssertion) withText(note.getTitle()));
            onView(withId(R.id.text_note_text)).check((ViewAssertion) withText(note.getText()));
            //onView(withId(R.id.action_next)).perform(click());
            if(index<notes.size()-1){
                onView(allOf(withId(R.id.action_next),isEnabled())).perform(click());
            }
            onView(withId(R.id.action_next)).check((ViewAssertion) not(isEnabled()));
            pressBack();
        }
    }

}