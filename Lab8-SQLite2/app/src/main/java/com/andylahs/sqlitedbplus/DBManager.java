package com.andylahs.sqlitedbplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {

  private DatabaseHelper dbHelper;

  private Context context;

  private SQLiteDatabase database;

  public DBManager(Context c) {
    context = c;
  }

  public DBManager open() {
    try {
      dbHelper = new DatabaseHelper(context);
      database = dbHelper.getWritableDatabase();
    } catch (SQLiteCantOpenDatabaseException e) {
      Log.e("DBManager: ", "can't open database!!!", e);
    }
    return this;
  }

  public void close() {
    try {
      dbHelper.close();
    } catch (SQLException e) {
      Log.e("DBManager: ", "", e);
    }
  }

  public void insert(String name, String desc) {
    ContentValues contentValue = new ContentValues();
    contentValue.put(DatabaseHelper.SUBJECT, name);
    contentValue.put(DatabaseHelper.DESC, desc);

    try {
      database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
      dbHelper.close();
    } catch (SQLException e) {
      Log.e("DBManager: ", "", e);
    }
  }

  public Cursor fetch(Boolean b) {
    String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC };
    Cursor cursor = null;
    try {
      if (b) {
        cursor = database.query(DatabaseHelper.TABLE_NAME, columns, "description='done'", null, null, null, null);
      } else {
        cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
      }
      if (cursor != null) {
        cursor.moveToFirst();
      }
    } catch (SQLException e) {
      Log.e("DBManager: ", "", e);
    }
    return cursor;
  }


  public int update(long _id, String name, String desc) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(DatabaseHelper.SUBJECT, name);
    contentValues.put(DatabaseHelper.DESC, desc);
    int i = 0;
    try {
      i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
    } catch (SQLException e) {
      Log.e("DBManager: ", "", e);
    }
    return i;
  }

  public void delete(long _id) {
    try {
      database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    } catch (SQLException e) {
      Log.e("DBManager: ", "", e);
    }
  }

}
