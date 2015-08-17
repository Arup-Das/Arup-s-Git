package com.example.demo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	
	private TextView data; 
	
	public static String placename = new String();
	
	EditText value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//data = (TextView)findViewById(R.id.editText2);
	}
	
	//Button Action
	public void go(View v){
		value = (EditText) findViewById(R.id.editText1);
		placename=value.getText().toString();
		
		 Intent i = new Intent(MainActivity.this, WebActivity.class);
	     startActivity(i);
		
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.full, menu);
		
		// Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.item1).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        
		return true;
	}
	
//closing the keyboard
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            Log.d("Activity", "Touch event "+event.getRawX()+","+event.getRawY()+" "+x+","+y+" rect "+w.getLeft()+","+w.getTop()+","+w.getRight()+","+w.getBottom()+" coords "+scrcoords[0]+","+scrcoords[1]);
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) { 

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
    return ret;
    }
	/**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.item2:
            // Add Location
            AddLocation();
            return true;
        case R.id.item3:
            // Delete Location
            DeleteLocation();
            return true;
        case R.id.item4:
            // Select Location
            SelectLocation();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	/**
     * Launching  activitys from actionbar
     * */
    
    private void AddLocation() {
        Intent i = new Intent(MainActivity.this, DataActivity.class);
        startActivity(i);
    }
    
    private void DeleteLocation() {
        Intent i = new Intent(MainActivity.this, DeleteActivity.class);
        startActivity(i);
    }
    
    private void SelectLocation() {
        Intent i = new Intent(MainActivity.this, SelectActivity.class);
        startActivity(i);
    }
}