//241113 - MtpA -	Created class
package com.liftme.liftmeclient;

import com.liftme.liftmeclient.MainActivity.destBtnListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Lifter extends Activity {
	  
	private Button destinationButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifter_details);
        Toast.makeText(getBaseContext(),"Looking to give someone a lift", Toast.LENGTH_LONG).show();
        
        destinationButton = (Button) findViewById(R.id.destinationsButton);
		destinationButton.setOnClickListener(new destBtnListener());
	} // method onCreate
	
	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume
	
	class destBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent goIntent = new Intent(getApplicationContext(), DestinationList.class);
			goIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(goIntent);
		}
	}
}
