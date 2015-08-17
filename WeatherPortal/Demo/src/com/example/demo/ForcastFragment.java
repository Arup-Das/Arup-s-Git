package com.example.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class ForcastFragment extends Fragment {

	String place,temperature,condition,code;
	String d11,d12,d13,d14,d21,d22,d23,d24,d31,d32,d33,d34,
	d41,d42,d43,d44,d51,d52,d53,d54;
	
	int[] imageArray = { R.drawable.i0, R.drawable.i1,R.drawable.i2, R.drawable.i3,
			 			 R.drawable.i4, R.drawable.i5,R.drawable.i6, R.drawable.i7,
			 			 R.drawable.i8, R.drawable.i9,R.drawable.i10, R.drawable.i11,
			 			 R.drawable.i12, R.drawable.i13,R.drawable.i14, R.drawable.i15,
			 			 R.drawable.i16, R.drawable.i17,R.drawable.i18, R.drawable.i19,
			 			 R.drawable.i20, R.drawable.i21,R.drawable.i22, R.drawable.i23,
			 			 R.drawable.i24, R.drawable.i25,R.drawable.i26, R.drawable.i27,
			 			 R.drawable.i28, R.drawable.i29,R.drawable.i30, R.drawable.i31,
			 			 R.drawable.i32, R.drawable.i33,R.drawable.i34, R.drawable.i35,
			 			 R.drawable.i36, R.drawable.i37,R.drawable.i38, R.drawable.i39,
			 			 R.drawable.i40, R.drawable.i41,R.drawable.i42, R.drawable.i43,
			 			 R.drawable.i44, R.drawable.i45,R.drawable.i46, R.drawable.i47
			 			};


	public ForcastFragment(Bundle b1,Bundle b2) {
		// Required empty public constructor
		Bundle a = new Bundle();
		Bundle b = new Bundle();
		a=b1;
		b=b2;
		
		place = a.getString("PLACE1");
		temperature = a.getString("TEMPARATURE1");
		condition = a.getString("CONDITION1");
		code = a.getString("CODE1");
		//DAY1
    	d11=b.getString("D1");
    	d12=b.getString("D1C");
    	d13=b.getString("D1H");
    	d13=d13+"°";
    	d14=b.getString("D1L");
    	d14=d14+"°";
    	
    	//Day2
    	d21=b.getString("D2");
    	d22=b.getString("D2C");
    	d23=b.getString("D2H");
    	d23=d23+"°";
    	d24=b.getString("D2L");
    	d24=d24+"°";
    	
    	//Day3
    	d31=b.getString("D3");
    	d31=getday(d31);
    	d32=b.getString("D3C");
    	d33=b.getString("D3H");
    	d33=d33+"°";
    	d34=b.getString("D3L");
    	d34=d34+"°";
    	
    	//Day4
    	d41=b.getString("D4");
    	d41=getday(d41);
    	d42=b.getString("D4C");
    	d43=b.getString("D4H");
    	d43=d43+"°";
    	d44=b.getString("D4L");
    	d44=d44+"°";
    	
    	//Day5
    	d51=b.getString("D5");
    	d51=getday(d51);
    	d52=b.getString("D5C");
    	d53=b.getString("D5H");
    	d53=d53+"°";
    	d54=b.getString("D5L");
    	d54=d54+"°";
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		
		
		View v = inflater.inflate(R.layout.fragment_forcast, container, false);
		
		
		//In the Relative layout
		TextView title = (TextView) v.findViewById(R.id.degree);
		title.setText(place);
		TextView con = (TextView) v.findViewById(R.id.fcon);
		con.setText(condition);
		TextView temp= (TextView) v.findViewById(R.id.ftemp);
		temp.setText(temperature);
		//Setting  Icon
		Integer x = Integer.valueOf(code);
		ImageView imageview = (ImageView) v.findViewById(R.id.fimage);
		imageview.setImageResource(imageArray[x]);
		if(code.equals("3200")) imageview.setImageResource(R.drawable.na);

		
		
		//In Grid layout
		TextView day1h = (TextView) v.findViewById(R.id.d1h);
		day1h.setText(d13);
		TextView day1l = (TextView) v.findViewById(R.id.d1l);
		day1l.setText(d14);
		//icon
		x = Integer.valueOf(d12);
		ImageView imageview1 = (ImageView) v.findViewById(R.id.d1i);
		imageview1.setImageResource(imageArray[x]);
		if(d12.equals("3200")) imageview.setImageResource(R.drawable.na);
		
		
		TextView day2h = (TextView) v.findViewById(R.id.d2h);
		day2h.setText(d23);
		TextView day2l = (TextView) v.findViewById(R.id.d2l);
		day2l.setText(d24);
		//icon
		x = Integer.valueOf(d22);
		ImageView imageview2 = (ImageView) v.findViewById(R.id.d2i);
		imageview2.setImageResource(imageArray[x]);
		if(d22.equals("3200")) imageview.setImageResource(R.drawable.na);
		
		
		TextView day3d = (TextView) v.findViewById(R.id.d3);
		day3d.setText(d31);
		TextView day3h = (TextView) v.findViewById(R.id.d3h);
		day3h.setText(d33);
		TextView day3l = (TextView) v.findViewById(R.id.d3l);
		day3l.setText(d34);
		//icon
		x = Integer.valueOf(d32);
		ImageView imageview3 = (ImageView) v.findViewById(R.id.d3i);
		imageview3.setImageResource(imageArray[x]);
		if(d32.equals("3200")) imageview.setImageResource(R.drawable.na);
		
		
		TextView day4d = (TextView) v.findViewById(R.id.d4);
		day4d.setText(d41);
		TextView day4h = (TextView) v.findViewById(R.id.d4h);
		day4h.setText(d43);
		TextView day4l = (TextView) v.findViewById(R.id.d4l);
		day4l.setText(d44);
		//icon
		x = Integer.valueOf(d42);
		ImageView imageview4 = (ImageView) v.findViewById(R.id.d4i);
		imageview4.setImageResource(imageArray[x]);
		if(d42.equals("3200")) imageview.setImageResource(R.drawable.na);
		
		
		TextView day5d = (TextView) v.findViewById(R.id.d5);
		day5d.setText(d51);
		TextView day5h = (TextView) v.findViewById(R.id.d5h);
		day5h.setText(d53);
		TextView day5l = (TextView) v.findViewById(R.id.d5l);
		day5l.setText(d54);
		//icon
		x = Integer.valueOf(d52);
		ImageView imageview5 = (ImageView) v.findViewById(R.id.d5i);
		imageview5.setImageResource(imageArray[x]);
		if(d52.equals("3200")) imageview.setImageResource(R.drawable.na);
		
		
		return v;
	}
	
	String getday (String s) {
		String day = s;
		if(day.equals("Wed")) day="Wednes";
		else if(day.equals("Tue")) day="Tues";
		else if(day.equals("Thu")) day="Thurs";
		else if(day.equals("Sat")) day="Satur";
    	day=day+"day";
		return day;
	}

}
