package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper{
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Place";
 
    // Contacts table name
    private static final String TABLE_PLACE = "SavedPlace";
 
    // Contacts Table Columns names
    private static final String KEY_PLACE = "place";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_PLACE_TABLE = "CREATE TABLE " + TABLE_PLACE + "("
                +  KEY_PLACE + " TEXT PRIMARY KEY" + ")";
        db.execSQL(CREATE_PLACE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
 
        // Create tables again
        onCreate(db);
	}
	
	/**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new place
    void addPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_PLACE, place.getPlace()); // Place Name
 
        // Inserting Row
        db.insert(TABLE_PLACE, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    Place getContact(String place) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	place="'"+place+"'";
    	
   	 String selectQuery = "SELECT  * FROM "+ TABLE_PLACE +" WHERE "+ KEY_PLACE + " = "+ place;
   	 
   	 Cursor cursor = db.rawQuery(selectQuery, null);
        
   	 if (cursor != null)
            cursor.moveToFirst();
 
   	 Place contact = new Place();
   	 contact.setPlace(cursor.getString(0));
        // return contact
        return contact;
    }
     
    // Getting All Contacts
    public List<String> getAllPlaces() {
        List<String> placesList = new ArrayList<String>();
        placesList.add("Select a place");
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLACE;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Place places = new Place();
                places.setPlace(cursor.getString(0));
                // Adding contact to list
                placesList.add(places.getPlace());
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return placesList;
    }
 
    // Deleting single contact
    public void deleteContact(String place) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	place="'"+place+"'";
   	 String deleteQuery = "DELETE FROM "+ TABLE_PLACE +" WHERE "+ KEY_PLACE + " = "+ place;
   	 db.execSQL(deleteQuery);
     db.close();
    }

}

