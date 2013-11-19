//171113 - MtpA -	Created class

package com.liftme.liftmeclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LiftMeDataValidator {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public LiftMeDataValidator() {
		
	} // constructor - no args
	
	public static boolean isDeviceIDValid(String vDeviceID) {
		if (vDeviceID.length() == 0) {
			return false;
		} else if (vDeviceID.length() > 64){
			return false;
		} else {
			return true;
		} // if length 0 or > 64 then the deviceID is invalid else return true (it is not empty)
	} // method isDeviceIDValid
	
	public static boolean isLatValid(double vLatitude) {
		if (vLatitude < -90.0 || vLatitude > 90.0) {
			return false;
		} else {
			return true;
		} // if lt -90 or gt 90 is not valid
	} // method isLatValid
	
	public static boolean isLongValid(double vLongditude) {
		if (vLongditude < -180.0 || vLongditude > 180.0) {
			return false;
		} else {
			return true;
		} // if lt -180 or gt 180 is not valid
	} // method isLongValid

	public static boolean isDateValid(String vDate, String vTime) {
		dateFormat.setLenient(false);
		try {
			Date validDate = dateFormat.parse(vDate + " " + vTime);
			Date currDate = new Date();
			if (validDate.compareTo(currDate) > 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception exDate) {
			return false;
		} // try to convert to a date, if can then OK otherwise it is invalid, then if sent date > now then invalid
	} // method checkValidDate

} // class LiftMeDataValidator
