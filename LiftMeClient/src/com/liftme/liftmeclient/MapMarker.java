//251113 - MtpA -	Create class (empty shell)
package com.liftme.liftmeclient;

import com.google.android.gms.maps.model.MarkerOptions;

public class MapMarker {

	private final MarkerOptions currMarker;

	public static class Builder {
		// Required parameters
		private final MarkerOptions currMarker;

		// Optional parameters

		public Builder(MarkerOptions vCurrMarker) {
			this.currMarker = vCurrMarker;
		} // Builder constructor - no args

		public MapMarker build() throws DataValidationException {
			if (checkValidFields()) {
				return new MapMarker(this);
			} else {
				throw new DataValidationException();
			}
		} // method build
		
		private boolean checkValidFields() {
			return false;
		} // method checkValidFields
		
	} // class Builder

	private MapMarker(Builder builder) {
		currMarker = builder.currMarker;
	} // builder constructor
}
