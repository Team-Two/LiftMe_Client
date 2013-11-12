package uk.ac.sussex.addison.m.androidmaplocation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class firstPage extends Activity {
    private boolean success;
    private static MessageDigest md;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);
        
        success = false;
        final TextView myText = null;
        ImageView img = (ImageView) findViewById(R.id.imageButton);
        img.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		final EditText email =(EditText)findViewById(R.id.editText1);
        		final EditText username =(EditText)findViewById(R.id.editText2);
        		final EditText password =(EditText)findViewById(R.id.editText3);
        		String ema= email.getText().toString();
        		String uname = username.getText().toString();
        		String pass =  password.getText().toString();
        		
        		try {
					md = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                byte[] passBytes = pass.getBytes();
                md.reset();
                byte[] digested = md.digest(passBytes);
                StringBuffer sb = new StringBuffer();
                for(int i=0;i<digested.length;i++){
                    sb.append(Integer.toHexString(0xff & digested[i]));
                }
                
                /*sb is the encrypted version of the password*/
        		
        		/* Connecting to server and checking the information*/
        		
        		if(success==false){
        			
        			LinearLayout lView = (LinearLayout)findViewById(R.id.errorDisplay);
        		    myText.setText("Sign In Attempt is unsuccessful!");
        		    lView.addView(myText);
        		    
        		}else{
        			/*Go to main_activity*/
        		}
        		
        	}
        });
        
        
        
        
        
    }

}
