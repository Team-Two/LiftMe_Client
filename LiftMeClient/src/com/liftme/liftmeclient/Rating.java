//041213 - MtpA -	Amended to now be the ratings screen
//271113 - MtpA -	Created class
package com.liftme.liftmeclient;

import com.liftme.liftmeclient.MainActivity.ratingsBtnListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Rating extends Activity {

	private Button sendReviewButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trip_summary);
		sendReviewButton = (Button) findViewById(R.id.sendReviewButton);
		sendReviewButton.setOnClickListener(new sendBtnListener());
	} // method onCreate
	
	class sendBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Toast.makeText(getBaseContext(),"Submitting request", Toast.LENGTH_LONG).show();			
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume
}
