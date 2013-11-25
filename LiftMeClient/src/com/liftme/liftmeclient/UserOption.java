//241113 - MtpA -	Created class

package com.liftme.liftmeclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class UserOption extends Activity {

	private Button lifteeButton;
	private Button lifterButton;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option_page);

		// show buttons
		lifteeButton = (Button) findViewById(R.id.lifteeButton);
		lifteeButton.setOnClickListener(new lifteeBtnListener());
		lifterButton = (Button) findViewById(R.id.lifterButton);
		lifterButton.setOnClickListener(new lifterBtnListener());
	} // method onCreate
	
	class lifteeBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(UserOption.this, MainActivity.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		} // method onClick
	} // class lifteeBtnListener
	
	class lifterBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(UserOption.this, Lifter.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		} // method onClick
	} // class lifterBtnListener

	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume
}