//261113 - MtpA -	Created class

package com.liftme.liftmeclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Profile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
        Toast.makeText(getBaseContext(),"Looking at the profile", Toast.LENGTH_LONG).show();
	} // method onCreate
	
	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume
	
}
