package com.liftme.liftmeclient;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gms.common.Scopes;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
//import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;

import com.google.android.gms.common.*;
import com.google.android.gms.common.GooglePlayServicesClient.*;
import com.google.android.gms.plus.PlusClient;

public class FirstPage extends Activity implements View.OnClickListener,
		ConnectionCallbacks, OnConnectionFailedListener {
	private boolean success;
	private static MessageDigest md;

	private static final String TAG = "FirstActivity (should really be named better!)";
	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

	private ProgressDialog mConnectionProgressDialog;
	private PlusClient mPlusClient;
	private ConnectionResult mConnectionResult;

	private AppComm comms;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_screen);

		mPlusClient = new PlusClient.Builder(this, this, this)
				.setActions("http://schemas.google.com/AddActivity",
						"http://schemas.google.com/BuyActivity")
				.setScopes(Scopes.PLUS_LOGIN) // Space separated list of scopes
				.build();
		// Progress bar to be displayed if the connection failure is not
		// resolved.
		mConnectionProgressDialog = new ProgressDialog(this);
		mConnectionProgressDialog.setMessage("Signing in...");

		findViewById(R.id.sign_in_button).setOnClickListener(this);

		/*
		 * success = false;
		 * final TextView myText = null;
		
		 * ImageView img = (ImageView) findViewById(R.id.imageButton);
		 * img.setOnClickListener(new OnClickListener(){ public void
		 * onClick(View v){ final EditText email
		 * =(EditText)findViewById(R.id.editText1); final EditText username
		 * =(EditText)findViewById(R.id.editText2); final EditText password
		 * =(EditText)findViewById(R.id.editText3); String ema=
		 * email.getText().toString(); String uname =
		 * username.getText().toString(); String pass =
		 * password.getText().toString();
		 * 
		 * try { md = MessageDigest.getInstance("MD5"); } catch
		 * (NoSuchAlgorithmException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } byte[] passBytes = pass.getBytes();
		 * md.reset(); byte[] digested = md.digest(passBytes); StringBuffer sb =
		 * new StringBuffer(); for(int i=0;i<digested.length;i++){
		 * sb.append(Integer.toHexString(0xff & digested[i])); }
		 */

		/* sb is the encrypted version of the password */

		/* Connecting to server and checking the information */

		/*
		 * if(success==false){
		 * 
		 * LinearLayout lView = (LinearLayout)findViewById(R.id.errorDisplay);
		 * //myText.setText("Sign In Attempt is unsuccessful!");
		 * //lView.addView(myText);
		 * 
		 * }else{ //Go to main_activity //Connection between the activities is
		 * missing }
		 * 
		 * } }); *
		 */
	}

	@Override
	protected void onStart() {
		super.onStart();
		mPlusClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mPlusClient.disconnect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (mConnectionProgressDialog.isShowing()) {
			// The user clicked the sign-in button already. Start to resolve
			// connection errors. Wait until onConnected() to dismiss the
			// connection dialog.
			if (result.hasResolution()) {
				try {
					result.startResolutionForResult(this,
							REQUEST_CODE_RESOLVE_ERR);
				} catch (SendIntentException e) {
					mPlusClient.connect();
				}
			}
		}
		// Save the result and resolve the connection failure upon a user click.
		mConnectionResult = result;
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == REQUEST_CODE_RESOLVE_ERR
				&& responseCode == RESULT_OK) {
			mConnectionResult = null;
			mPlusClient.connect();
		}
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.sign_in_button && !mPlusClient.isConnected()) {
			if (mConnectionResult == null) {
				mConnectionProgressDialog.show();
			} else {
				try {
					mConnectionResult.startResolutionForResult(this,
							REQUEST_CODE_RESOLVE_ERR);
				} catch (SendIntentException e) {
					// Try connecting again.
					mConnectionResult = null;
					mPlusClient.connect();
				}
			}
		}
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		mConnectionProgressDialog.dismiss();
		String accountName = mPlusClient.getAccountName();
		Toast.makeText(this, accountName + " is connected.", Toast.LENGTH_LONG)
				.show();
		// Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDisconnected() {
		Log.d(TAG, "disconnected");
	}
	
	
	private class MyAsyncTask extends AsyncTask<String, Integer, Double>{

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(Double result){
            //pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Code Sent", Toast.LENGTH_LONG).show();
        }
        protected void onProgressUpdate(Integer... progress){
            //pb.setProgress(progress[0]);
        }

        public void postData(String valueIWantToSend) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            //HttpPost httppost = new HttpPost("http://10.0.2.2/chotu/index.php");
            HttpPost httppost = new HttpPost("http://192.168.1.13/educlinic/Widget/AndroidApp");
            try {

                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("myHttpData", valueIWantToSend));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } 
            catch (ClientProtocolException e) 
            {
                // TODO Auto-generated catch block
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
            }
        }
	}

}
