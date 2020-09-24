package com.technologygate.toters.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "toters.db";

    //========= User Table =====================
    private static final String USER_TABLE = "user_table";
    private static final String USER_ID = "_id";
    private static final String USER_NAME = "name";
    private static final String USER_TIMESTAMP = "timeStamp";
    private static final String USER_MESSAGE = "message";
    private static final String USER_PROFILE = "profile";

    //======== Message Table ======================
    private static final String MESSAGE_TABLE = "message_table";
    private static final String MESSAGE_ID = "_id";
    private static final String MESSAGE_CONTENT = "message_content";
    private static final String MESSAGE_TIMESTAMP = "message_timestamp";
    private static final String MESSAGE_RECEIVER = "message";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 20);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String userTable = "create table " + USER_TABLE + " ( " + USER_ID + " integer primary key, " +
                USER_NAME + " text, " + USER_TIMESTAMP + " text, " + USER_MESSAGE + " text, " +
                USER_PROFILE + " integer " + " );";

        String messagesTable = "create table " + MESSAGE_TABLE + " ( " + MESSAGE_ID + " integer primary key AUTOINCREMENT, " +
                MESSAGE_CONTENT + " text, " + MESSAGE_TIMESTAMP + " text, " + MESSAGE_RECEIVER + " integer , " +
                " FOREIGN KEY ( " + MESSAGE_RECEIVER + ")" + " REFERENCES " + USER_TABLE + " ( " + USER_ID + " ) );";

        db.execSQL(userTable);
        db.execSQL(messagesTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("drop table if exists " + USER_TABLE);
        db.execSQL("drop table if exists " + MESSAGE_TABLE);
        onCreate(db);

    }


    public void addUser(ArrayList<User> users) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + USER_TABLE);
        ContentValues contentValues = new ContentValues();
        for (User user : users) {

            contentValues.put(USER_ID, user.getId());
            contentValues.put(USER_NAME, user.getName());
            contentValues.put(USER_TIMESTAMP, user.getTimeStamp());
            contentValues.put(USER_MESSAGE, user.getLastMessage());
            contentValues.put(USER_PROFILE, user.getProfile());
            db.insert(USER_TABLE, null, contentValues);

        }

    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + USER_TABLE + " order by " + USER_TIMESTAMP + " desc", null);

        ArrayList<User> users = new ArrayList<>();
        while (c.moveToNext()) {


            int id = c.getInt(0);
            String name = c.getString(1);
            String timeStamp = c.getString(2);
            String message = c.getString(3);
            int profile = c.getInt(4);

            User user = new User(id, timeStamp, name, message, profile);
            users.add(user);
        }
        c.close();

        return users;
    }


    public void addMessage(String message, int userId, String timeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues addMessage = new ContentValues();

        addMessage.put(MESSAGE_CONTENT, message);
        addMessage.put(MESSAGE_TIMESTAMP, timeStamp);
        addMessage.put(MESSAGE_RECEIVER, userId);

        db.insert(MESSAGE_TABLE, null, addMessage);

        updateLastMessageInUserTable(userId, message, timeStamp);


    }

    public void receiveMessage(String message, int userId, String timeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues addMessage = new ContentValues();

        addMessage.put(MESSAGE_CONTENT, message);
        addMessage.put(MESSAGE_TIMESTAMP, timeStamp);
        addMessage.put(MESSAGE_RECEIVER, userId);

        db.insert(MESSAGE_TABLE, null, addMessage);


    }

    private void updateLastMessageInUserTable(int userId, String message, String timeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_MESSAGE, message);
        contentValues.put(USER_TIMESTAMP, timeStamp);
        db.update(USER_TABLE, contentValues, USER_ID + " = " + userId, null);

    }

    public ArrayList<Message> getMessages(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Message> messages = new ArrayList<>();
        Cursor c = db.rawQuery("select * from " + MESSAGE_TABLE + " where " + MESSAGE_RECEIVER + " = " + userId + ";", null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String content = c.getString(1);
            String timeStamp = c.getString(2);
            int receiver = c.getInt(3);

            Message user = new Message(id, receiver, timeStamp, content);
            messages.add(user);
        }

        c.close();

        return messages;
    }
}
