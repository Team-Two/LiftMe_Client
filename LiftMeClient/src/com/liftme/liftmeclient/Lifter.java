//241113 - MtpA -	Created class
package com.liftme.liftmeclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Lifter extends Activity {
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifter_details);
        Toast.makeText(getBaseContext(),"Looking to give someone a lift", Toast.LENGTH_LONG).show();
	} // method onCreate
	
	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume
}
