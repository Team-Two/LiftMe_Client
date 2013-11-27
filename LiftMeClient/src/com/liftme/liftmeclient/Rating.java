//271113 - MtpA -	Created class
package com.liftme.liftmeclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Rating extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statsfeedbacks);
        Toast.makeText(getBaseContext(),"Looking at the stats and feedback", Toast.LENGTH_LONG).show();
	} // method onCreate
	
	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume
}
