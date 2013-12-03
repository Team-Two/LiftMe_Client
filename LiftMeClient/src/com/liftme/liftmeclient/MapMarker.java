//031213 - MtpA -	Added all code to create MapMarker
//251113 - MtpA -	Create class (empty shell)
package com.liftme.liftmeclient;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapMarker {

	private final double latitude;
	private final double longditude;
	private final float markerColour;
	private final String locName;
	private final MarkerOptions locMarker;
	
	public MapMarker(double vLat, double vLong, float vColour, String vLocName) {
		this.latitude = vLat;
		this.longditude = vLong;
		this.markerColour = vColour;
		this.locName = vLocName;
		LatLng currPos = new LatLng(latitude, longditude);
		locMarker = new MarkerOptions();
		locMarker.position(currPos);
		locMarker.snippet("Lat: " + latitude + " Lng: "+ longditude);
		locMarker.icon(BitmapDescriptorFactory.defaultMarker(vColour)).title(locName);
	} // MapMarker constructor

	public double getLatitude() {
		return latitude;
	}

	public double getLongditude() {
		return longditude;
	}

	public float getMarkerColour() {
		return markerColour;
	}

	public String getLocName() {
		return locName;
	}

	public MarkerOptions getLocMarker() {
		return locMarker;
	}
}
