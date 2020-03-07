package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {


    private static final String TABLE_NAME = "Contact";
    private static final String col0 = "ID";
    private static final String col1 = "Name";
    private static final String col2 = "Surname";
    private static final String col3 = "Phone";
    private static Database sInstance;

    public Database(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public static synchronized Database getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Database (context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String REQUETE_CREATION_BD = "create table " + TABLE_NAME + " (" + col0 + " integer primary key autoincrement, " +
                col1 + " text not null, " + col2 + " text not null, " + col3 + " text not null);";
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean AjouterContact(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,contact.Nom);
        contentValues.put(col2,contact.Prenom);
        contentValues.put(col3,contact.Numero);

        Log.d("Database","on ajoute du data"+contact.toString());
        long Result=db.insert(TABLE_NAME,null,contentValues);

        if(Result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getContact()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String Query= "SELECT * FROM Contact";
        Cursor data=db.rawQuery(Query,null);
        return  data;
    }

}

