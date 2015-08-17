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
public class NormalFragment extends Fragment {
	
	String place="New" , temperature , code, condition , humidity , high , low ;
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


	public NormalFragment(Bundle s) {
		// Required empty public constructor
		Bundle b = new Bundle();
		b=s;
		place = b.getString("PLACE1");
		temperature = b.getString("TEMPARATURE1");
		condition = b.getString("CONDITION1");
		high = b.getString("HIGH1");
		high=high+"°";
		low = b.getString("LOW1");
		low=low+"°";
		code = b.getString("CODE1");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		
		View v = inflater.inflate(R.layout.fragment_normal, container, false);
		TextView title = (TextView) v.findViewById(R.id.place);
		title.setText(place);
		TextView con = (TextView) v.findViewById(R.id.con1);
		con.setText(condition);
		TextView hight = (TextView) v.findViewById(R.id.high1);
		hight.setText(high);
		TextView lowt = (TextView) v.findViewById(R.id.low1);
		lowt.setText(low);
		TextView tempt = (TextView) v.findViewById(R.id.temp);
		tempt.setText(temperature);
		
		//Setting  Icon
		Integer x = Integer.valueOf(code);
		ImageView imageview = (ImageView) v.findViewById(R.id.fimage);
		imageview.setImageResource(imageArray[x]);
		if(code.equals("3200")) imageview.setImageResource(R.drawable.na);
		return v;
	}

}
