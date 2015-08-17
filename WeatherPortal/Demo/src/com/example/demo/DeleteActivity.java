package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteActivity extends Activity implements
OnItemSelectedListener {
	
	// Spinner element
    Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		// Spinner element
        spinner = (Spinner) findViewById(R.id.spinner1);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
		loadData();
	}
	
	/**
     * Function to load the spinner data from SQLite database
     * */
    private void loadData() {
    	// database handler
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
 
        // Spinner Drop down elements
        List<String> label = new ArrayList<String>();
        label.add("Select");
        label = db.getAllPlaces();
 
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, label);
        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
    
    
    private void DeleteData(String place) {
        // database handler
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.deleteContact(place);
    }
	
	@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();    
        
     // Showing selected spinner item
        if(item!="Select a place"){
        	DeleteData(item);
            Toast.makeText(parent.getContext(), item +" Deleted ", Toast.LENGTH_LONG).show();
            loadData();
        }
        else if(item=="Select a place")
            Toast.makeText(parent.getContext(), " Please Select a place. ", Toast.LENGTH_LONG).show();
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
