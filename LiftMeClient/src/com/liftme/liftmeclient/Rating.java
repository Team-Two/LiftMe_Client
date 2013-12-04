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

	private ImageButton likeButton;
	private ImageButton dislikeButton;
	private Button submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratings);
		likeButton = (ImageButton) findViewById(R.id.tripLikeBtn);
		likeButton.setOnClickListener(new likeBtnListener());
		likeButton.setBackgroundColor(Color.parseColor("#ffffff"));
		dislikeButton = (ImageButton) findViewById(R.id.tripDislikeBtn);
		dislikeButton.setOnClickListener(new dislikeBtnListener());
		dislikeButton.setBackgroundColor(Color.parseColor("#ffffff"));
		submitButton = (Button) findViewById(R.id.submitButton);
		submitButton.setOnClickListener(new submitBtnListener());
	} // method onCreate
	
	class likeBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			likeButton.setBackgroundColor(Color.parseColor("#808080"));
			dislikeButton.setBackgroundColor(Color.parseColor("#ffffff"));
		}
	}
	
	class dislikeBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			dislikeButton.setBackgroundColor(Color.parseColor("#808080"));
			likeButton.setBackgroundColor(Color.parseColor("#ffffff"));
		}
	}

	class submitBtnListener implements View.OnClickListener {
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
