package sg.edu.rp.c346.id22016788.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simpleMovie.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_MOVIE = "Movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MOVIE_TITLE = "title";
    private static final String COLUMN_MOVIE_GENRE = "genre";
    private static final String COLUMN_MOVIE_YEAR = "year";
    private static final String COLUMN_MOVIE_RATING = "rating";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MOVIE_TITLE + " TEXT , "
                + COLUMN_MOVIE_GENRE + " TEXT , "
                + COLUMN_MOVIE_YEAR + " TEXT , "
                + COLUMN_MOVIE_RATING + " TEXT ) ";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_MOVIE_TITLE, "Data number " + i);
            values.put(COLUMN_MOVIE_GENRE, "Data number " + i);
            values.put(COLUMN_MOVIE_YEAR, "Data number " + i);
            values.put(COLUMN_MOVIE_RATING, "Data number " + i);

            db.insert(TABLE_MOVIE, null, values);
        }
        Log.i("info", "dummy records inserted");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + TABLE_MOVIE + " ADD COLUMN  module_name TEXT ");
        //onCreate(db);
    }

    public ArrayList<Movies> getAllMovie() {
        ArrayList<Movies> Movies = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_MOVIE_TITLE, COLUMN_MOVIE_GENRE, COLUMN_MOVIE_YEAR, COLUMN_MOVIE_RATING};

        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                String year = cursor.getString(3);
                String rating = cursor.getString(4);
                Movies movie = new Movies(id, title, genre, year, rating);
                Movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movies;
    }

    public long insertMovie(String title, String genre, String year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIE_TITLE, title);
        values.put(COLUMN_MOVIE_GENRE, genre);
        values.put(COLUMN_MOVIE_YEAR, year);
        values.put(COLUMN_MOVIE_RATING, rating);
        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }


    public int updateMovie(Movies data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIE_TITLE, data.getMovieTitle());
        values.put(COLUMN_MOVIE_GENRE, data.getGenre());
        values.put(COLUMN_MOVIE_YEAR, data.getMovieYear());
        values.put(COLUMN_MOVIE_RATING, data.getMovieRating());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIE, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Movies> getAllMovie(String keyword) {
        ArrayList<Movies> Movies = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_MOVIE_TITLE, COLUMN_MOVIE_GENRE, COLUMN_MOVIE_YEAR, COLUMN_MOVIE_RATING};
        String condition = COLUMN_MOVIE_TITLE + COLUMN_MOVIE_GENRE + COLUMN_MOVIE_YEAR + COLUMN_MOVIE_RATING + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_MOVIE, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                String year = cursor.getString(3);
                String rating = cursor.getString(4);
                Movies movie = new Movies(id, title, genre, year, rating);
                Movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movies;
    }
}

