package com.lwazir.aad.notetakingapp.SQLiteDB;

public final class NoteDatabase {
    private NoteDatabase() {
    }
    public static final class CourseInfoEntry{
        public static final String  TABLE_NAME = "course_info";
        public static final String  COLUMN_COURSE_ID = "course_id";
        public static final String  COLUMN_COURSE_TITLE = "course_title";

        public static final String  CREATE_TABLE_QUERY =
                "CREATE TABLE "+TABLE_NAME+"( "
                +COLUMN_COURSE_ID+", "
                        +COLUMN_COURSE_TITLE+")";




    }

    public static final class NoteInfoEntry{
        public static final String TABLE_NAME = "note_info";
        public static final String COLUMN_NOTE_ID = "note_id";
        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_TEXT = "note_text";

        public static final String CREATE_TABLE_QUERY =
                "CREATE TABLE "+TABLE_NAME+"( "
                        +COLUMN_NOTE_ID+", "
                        +COLUMN_NOTE_TITLE+", "
                        +COLUMN_NOTE_TEXT
                        +")";

    }
}
