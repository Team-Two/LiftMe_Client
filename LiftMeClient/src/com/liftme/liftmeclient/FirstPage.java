//261113 - MtpA -	Changed signin button from ImageButton to Button
//241113 - MtpA -	Amended with IP to Mike's laptop and swapping of activity to lifter/liftee option
//					Added temp code to create the user login XML data (mtpa temp)

package com.liftme.liftmeclient;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusClient;
//import android.media.Image;

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
		//mtpa temp
		Button img = (Button) findViewById(R.id.signInButton);
		img.setOnClickListener(new btnConfirmListener());
		//mtpa temp
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
	class btnConfirmListener implements View.OnClickListener {

		private String userXML = "";
		private Calendar calToday = Calendar.getInstance();
		private String deviceId = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);

		@Override
		public void onClick(View v) {
//				MyAsyncTask mat = new MyAsyncTask();
//				mat.execute("hello");
//mtpa temp - create user registration details
			final EditText email = (EditText)findViewById(R.id.editText1); 
			final EditText username = (EditText)findViewById(R.id.editText2); 
			final EditText password = (EditText)findViewById(R.id.editText3); 
			String userEmail = email.getText().toString(); 
			String userName = username.getText().toString(); 
			String userPass = password.getText().toString();
			if (userEmail.length() > 0 && userName.length() > 0 && userPass.length() > 0) {
				LoginObjects loginDetails = new LoginObjects(userEmail, userName, userPass);
				XMLExport loginXML = new XMLExport();
				userXML = loginXML.createUserXml(loginDetails, deviceId, calToday);
		        Toast.makeText(getBaseContext(),userXML, Toast.LENGTH_LONG).show();
			}
//				mat.execute(userXML);
//mtpa temp
			Intent intent = new Intent(FirstPage.this, UserOption.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		} // method onClick

	} // class buttonListener

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
			postData("hello");
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
			HttpPost httppost = new HttpPost("http://10.0.69.157:8080/com.liftme/registerauser.html");
			try {

				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				//mtpa temp - establish connection with temp data
				nameValuePairs.add(new BasicNameValuePair("name", valueIWantToSend));
				nameValuePairs.add(new BasicNameValuePair("email", valueIWantToSend));
				nameValuePairs.add(new BasicNameValuePair("password", valueIWantToSend));
				//mtpa temp - establish connection with temp data
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				//mtpa temp
				//                Thread.currentThread().setContextClassLoader(new ClassLoader() {
				//                    @Override
				//                    public Enumeration<URL> getResources(String resName) throws IOException {
				//                        Log.i("Debug", "Stack trace of who uses " +
				//                                "Thread.currentThread().getContextClassLoader()." +
				//                                "getResources(String resName):", new Exception());
				//                        return super.getResources(resName);
				//                    }
				//                });
				//mtpa temp
				Log.i("asdf","before");
				HttpResponse response = httpclient.execute(httppost);
				Log.i("asdf",response.toString());
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
