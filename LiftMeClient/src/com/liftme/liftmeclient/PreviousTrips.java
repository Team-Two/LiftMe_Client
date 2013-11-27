package com.liftme.liftmeclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class PreviousTrips extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.previous_trips);
        Toast.makeText(getBaseContext(),"Looking at the previous trips", Toast.LENGTH_LONG).show();
	} // method onCreate
	
	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume
	
}