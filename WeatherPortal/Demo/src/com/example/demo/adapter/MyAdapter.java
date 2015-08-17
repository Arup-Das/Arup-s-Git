package com.example.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.demo.DetailFragment;
import com.example.demo.ForcastFragment;
import com.example.demo.NormalFragment;
import com.example.demo.WebActivity;
 

public class MyAdapter extends FragmentPagerAdapter {
	 

	public MyAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Normal fragment activity
            return new NormalFragment(WebActivity.bundle1);
        case 1:
            // Forecast fragment activity
        	return new ForcastFragment(WebActivity.bundle1,WebActivity.bundle2);
            
        case 2:
            // Detail fragment activity
        	return new DetailFragment(WebActivity.bundle3,WebActivity.bundle1);
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}



