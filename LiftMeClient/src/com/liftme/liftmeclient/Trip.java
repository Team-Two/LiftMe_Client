//281113 - MtpA -	Created class

package com.liftme.liftmeclient;


public class Trip {

	private final String tripDest;
	private final String tripDate;
	private final String tripTime;
	private final String tripLifter;
	
	public static class Builder {
		// Required parameters
		private final String tripDest;
		private final String tripDate;
		private final String tripTime;
		private final String tripLifter;

		// Optional parameters

		public Builder(String vTripDest, String vTripDate, String vTripTime, String vTripLifter) {
			this.tripDest = vTripDest;
			this.tripDate = vTripDate;
			this.tripTime = vTripTime;
			this.tripLifter = vTripLifter;
		} // Builder constructor - no args

		public Trip build() throws DataValidationException {
			if (checkValidFields()) {
				return new Trip(this);
			} else {
				throw new DataValidationException();
			}
		} // method build
		
		private boolean checkValidFields() {
			return true;
		} // method checkValidFields
		
	} // class Builder

	private Trip(Builder builder) {
		tripDest = builder.tripDest;
		tripDate = builder.tripDate;
		tripTime = builder.tripTime;
		tripLifter = builder.tripLifter;
	} // builder constructor

	public String getTripDest() {
		return tripDest;
	}

	public String getTripDate() {
		return tripDate;
	}

	public String getTripTime() {
		return tripTime;
	}

	public String getTripLifter() {
		return tripLifter;
	}
	
}
