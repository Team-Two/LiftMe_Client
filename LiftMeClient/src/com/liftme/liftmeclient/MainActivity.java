//251113 - MtpA -	Refactored code writeUserXML to createUserXML
//201113 - MtpA -	**********TEMP CHANGE***********
//					Amended the provider button to read in confirmregister data
//					registerXMLDir and registerXMLFilename to the Values>Strings.xml
//					**********TEMP CHANGE***********

//191113 - MtpA -	Added try/catch around UserMarker builder in line with JUnit test updates
//111113 - MtpA -	Two ImageButtons added with corresponding icons.  Both buttons have a listener.
//					providerBtnListener added (see above) and that creates new activity for listing providers

//					Folder
//					drawable-hdpi : Added car.png & disk.png
//					layout : 	Changes to activity main (change layout to relative and add buttons)
//								Added provider_list.xml (display template)
//								Added provider_ratings.xml (display template)
//					values :	Added provider_data.xml (list you see on the screen)
//								Added rating_data.xml (list you see on the screen)
//								Strings.xml amended to add a reference to both button icons

//081113 - MtpA -	Refactor code.
//					Remove independent call to Criteria and include in Provider instance creation
//					Add call to get the unique Android ID and pass to UserMarker class
//					Remove method writeToSDCard and replace with instance of SDCard with the method
//					Remove redundant askToContinue method (was just in to see how can work)

package com.liftme.liftmeclient;

import java.util.Calendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivity extends Activity implements LocationListener {

	// Google Map
	private GoogleMap googleMap;
	private Location location;
	private LocationManager locationManager;
	private ImageButton profileButton;
	private ImageButton ratingsButton;
	private ImageButton tripsButton;
	private ImageButton saveButton;
	private ImageButton refreshButton;
	private ImageButton goButton;
	private MarkerOptions myMarker;
	private String provider;
	private UserMarker currMarker;
	private Calendar currDate = Calendar.getInstance();
	private Resources resourceVals;
	private double lat = 0;
	private double lng = 0;

	//user ID Window
	Button  btnClosePopup;
	PopupWindow UserIDWindow;
	EditText UserID_Edit; 
	String UserID;
	//user ID Window

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Launching new Activity on selecting single List Item
		Intent i = new Intent(getApplicationContext(), FirstPage.class);
		//sending data to new activity
		startActivity(i);

		// show buttons
		saveButton = (ImageButton) findViewById(R.id.saveBtn);
		saveButton.setOnClickListener(new saveBtnListener());
		refreshButton = (ImageButton) findViewById(R.id.refreshBtn);
		saveButton.setOnClickListener(new refreshBtnListener());
		/*providerButton = (ImageButton) findViewById(R.id.providerBtn);
		providerButton.setOnClickListener(new providerBtnListener());*/

		if (initilizeMap()) {
			onLocationChanged(location);			
		} else {
			Toast.makeText(getBaseContext(),"Waiting for current location", Toast.LENGTH_LONG).show();
		} // try and refresh maps
		locationManager.requestLocationUpdates(provider, 20000, 10, this);

		//open pop-up window for user ID
		this.putIDWindow();
	} // method onCreate

	class saveBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			if (myMarker != null) {
				// create export instance and location for output
				XMLExport xmlData = new XMLExport();
				resourceVals = getResources();
				String fileName = resourceVals.getString(R.string.xmlLocFileName) + currMarker.getMarkerDate() + currMarker.getMarkerTime() + ".xml";

				// attempt create on XML output
				SDCard deviceSDCard = new SDCard();
				if (deviceSDCard.writeToSD(xmlData.createMarkerXml(currMarker), fileName, resourceVals)) {
			        Toast.makeText(getBaseContext(),"Location saved: " + fileName, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getBaseContext(),"Error in writing to SD card", Toast.LENGTH_LONG).show();
				} // end if can create xml output
			} else {
				Toast.makeText(getBaseContext(),"We haven't got a marker yet", Toast.LENGTH_LONG).show();
			} // end if got a marker
		} // method onClick

	} // class buttonListener

	class refreshBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			
			onResume();
		}
	}

	/*class providerBtnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// mtpa temp
			// Launching new Activity on selecting single List Item
			//			Intent i = new Intent(getApplicationContext(), AndroidProviderList.class);
			// sending data to new activity
			//			startActivity(i);

			resourceVals = getResources();
			XMLImport importFile = new XMLImport();
			ConfirmRegister regData;
			try {
				String xmlData = importFile.readSDXMLData(resourceVals.getString(R.string.registerXmlDir), resourceVals.getString(R.string.registerXmlFileName));				
				XMLImport parserXML = new XMLImport();
				regData = parserXML.importConfirmRegister(xmlData);
				Toast.makeText(getBaseContext(),"Registered > " + regData.getDeviceID() + "<>" + regData.getSessionID(), Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(getBaseContext(),"All fell apart reading in destinations", Toast.LENGTH_LONG).show();
			}

			// mtpa temp
		} // method onClick
	} // class providerBtnListener

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private boolean initilizeMap() {
		if (googleMap == null) {
			// put the map on the screen
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				return false;
			} else {
				// Enabling MyLocation Layer of Google Map
				googleMap.setMyLocationEnabled(true);

				// Getting LocationManager object from System Service LOCATION_SERVICE
				locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
				if (locationManager == null) {
					return false;
				} // if can't get a locationmanager

				// Getting the name of the best provider
				provider = locationManager.getBestProvider(new Criteria(), true);
				if (provider == "") {
					return false;
				}

				// Getting Current Location
				location = locationManager.getLastKnownLocation(provider);
				if (location == null) {
					return false;
				}
				return true;
			} // if - can display map
		} // if - can find map
		else {
			return false;
		}
	} // method - initializeMap

	@Override
	protected void onResume() {
		super.onResume();
		if (initilizeMap()) {
			Toast.makeText(getBaseContext(),"Resume location : " + Double.toString(lat).substring(0, 5) + "," + Double.toString(lng).substring(0, 5), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(),"Waiting on exact location", Toast.LENGTH_SHORT).show();
		} // try and refresh maps
	} // method onResume

	@Override
	public void onLocationChanged(Location location) {
		// get current location
		lat = (double) (location.getLatitude());
		lng = (double) (location.getLongitude());
		LatLng currPos = new LatLng(lat, lng);

		// set camera zoom level
		float cameraZoom = (googleMap.getMinZoomLevel() + googleMap.getMaxZoomLevel())/2;

		// create and display marker
		myMarker = new MarkerOptions();
		myMarker.position(currPos);
		myMarker.snippet("Lat:" + location.getLatitude() + "Lng:"+ location.getLongitude());
		myMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("ME");
		googleMap.addMarker(myMarker);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currPos, cameraZoom));

		// get deviceID
		String deviceId = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);

		// invoke instance of the current marker
		try {
			currMarker = new UserMarker.Builder(deviceId, lat, lng, currDate).build();
		} catch (DataValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(getBaseContext(),"Current location : " + Double.toString(lat).substring(0, 5) + "," + Double.toString(lng).substring(0, 5), Toast.LENGTH_LONG).show();
	} // onLocationChanged

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	//pop-up ID window
	public void putIDWindow()
	{
		try { 
			// get the instance of the LayoutInflater 
			LayoutInflater inflater = (LayoutInflater) MainActivity.this 
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			View layout = inflater.inflate(R.layout.id_window,(ViewGroup)findViewById(R.id.popup_element)); 

			//create pop-up 
			UserIDWindow = new PopupWindow(layout, 350, 350, true); 
			UserIDWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
			UserIDWindow.setFocusable(true);

			//class for ID EditBox
			UserID_Edit   = (EditText)layout.findViewById(R.id.UserID);

			//Listener for Enter in User ID Text Box
			UserID_Edit.setOnEditorActionListener(new OnEditorActionListener() {                     
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { 	    	    	
					try {  	 	    	    		
						UserID =  UserID_Edit.getText().toString();
						if (UserID.length() !=0)  UserIDWindow.dismiss();	 
						return true;		 				     
					}
					catch (Exception e) { 		 					 
						e.printStackTrace(); 
						return false;
					} 
				}
			});

			btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);

			//Listener for OK Button
			btnClosePopup.setOnClickListener(new View.OnClickListener(){ 
				@Override	
				public void onClick(View v) { 	 				
					try {  
						UserID =  UserID_Edit.getText().toString();
						if (UserID.length() !=0) UserIDWindow.dismiss();	
					}
					catch (Exception e) { 	 			 
						e.printStackTrace();} 		 
				}
			});	 	    	
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}	
} // class MainActivity
