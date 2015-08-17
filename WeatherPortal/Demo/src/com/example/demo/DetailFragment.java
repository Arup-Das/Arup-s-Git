package com.example.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class DetailFragment extends Fragment {
	
	String sunrise , sunset , chill , visibility , humidity , uv , wind , pressure, code, place;
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

	public DetailFragment(Bundle s,Bundle c) {
		// TODO Auto-generated constructor stub
		sunrise = s.getString("SUNRISE");
		sunset = s.getString("SUNSET");
		visibility = s.getString("VISIBILITY");
		humidity = s.getString("HUMIDITY");
		pressure = s.getString("PRESSURE");
		wind = s.getString("WIND");
		place = c.getString("PLACE1");
		code = c.getString("CODE1");
		chill= s.getString("CHILL");
		chill=chill+"°";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_detail, container, false);
		TextView tv;
		tv = (TextView) v.findViewById(R.id.textView5);
		tv.setText(place);
		tv = (TextView) v.findViewById(R.id.sr);
		tv.setText(sunrise);
		
		tv = (TextView) v.findViewById(R.id.sd);
		tv.setText(sunset);
		
		tv = (TextView) v.findViewById(R.id.vis);
		tv.setText(visibility);
		
		tv = (TextView) v.findViewById(R.id.ch);
		tv.setText(chill);
		
		tv = (TextView) v.findViewById(R.id.hum);
		tv.setText(humidity);
		
		tv = (TextView) v.findViewById(R.id.wi);
		tv.setText(wind);
		
		tv = (TextView) v.findViewById(R.id.pre);
		tv.setText(pressure);
		
		// Animation
				Animation anim1,anim2;
				// load the animation
				anim1 = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
				ImageView img1,img2;
		        img1 = (ImageView) v.findViewById(R.id.rotor1);
		        img2 = (ImageView) v.findViewById(R.id.rotor2);
		        // start the animation
		        img1.startAnimation(anim1);
		        anim2 = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
		        img2.startAnimation(anim2);
		        
		 //Setting  Icon
				Integer x = Integer.valueOf(code);
				ImageView imageview = (ImageView) v.findViewById(R.id.imageView1);
				imageview.setImageResource(imageArray[x]);
				if(code.equals("3200")) imageview.setImageResource(R.drawable.na);
		
		return v;
	}

}
