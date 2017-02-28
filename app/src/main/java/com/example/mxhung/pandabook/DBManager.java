package com.example.mxhung.pandabook;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.mxhung.pandabook.model.Book;
import com.example.mxhung.pandabook.model.Chapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by MXHung on 9/26/2016.
 */
public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Book.sqlite";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_MENU_BOOK = "MENUBOOK";
    public static final String TABLE_CHAPTER = "CHAPTER";

    public static final String COLUMN_ID_BOOK = "ID_BOOK";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_DETAIL = "DETAIL";


    private static final String DB_PATH_SUFFIX = "/databases/";

    static Context context;

    //	String pathDatabase = "";
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
//		pathDatabase = context.getFilesDir().getParent() + "/databases/" + DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = getDatabasePath();

        // if the path doesn't exist first, create it
        File f = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    private static String getDatabasePath() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
//		boolean deleted = dbFile.delete();
//		Log.d("delete", String.valueOf(deleted));
//		File db= context.getDatabasePath(DATABASE_NAME);
//		boolean delete = dbFile.exists();
//		Log.d("delete", String.valueOf(delete));
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public ArrayList<Book> getBook() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM " + DBManager.TABLE_MENU_BOOK;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = new Book();
            book.setId_book(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_ID_BOOK))));
            book.setName(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_NAME)));
            list.add(book);
            cursor.moveToNext();
        }
        Log.d("ketqua", String.valueOf(list));
        return list;
    }


    public ArrayList<Chapter> getChapterId(int id_book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM " + DBManager.TABLE_CHAPTER + " WHERE " + DBManager.COLUMN_ID_BOOK + " = " + id_book;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Chapter chapter = new Chapter();
            chapter.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_ID))));
            chapter.setId_book(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_ID_BOOK))));
            chapter.setName(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_NAME)));
            chapter.setDetail(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_DETAIL)));

            list.add(chapter);
            cursor.moveToNext();
        }
        Log.d("ketqua", String.valueOf(list));
        return list;
    }

    public Chapter getChapterDetail(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Chapter chapter = new Chapter();
        String sql = "SELECT * FROM " + DBManager.TABLE_CHAPTER + " WHERE " + DBManager.COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            chapter.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_ID))));
            chapter.setId_book(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_ID_BOOK))));
            chapter.setName(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_NAME)));
            chapter.setDetail(cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_DETAIL)));

        }
//        Log.d("ketqua", String.valueOf(diaDanh));
        return chapter;
    }

}
