//201113 - MtpA -	Created class

package com.liftme.liftmeclient;

public class ConfirmRegister {

	private final String deviceID;
	private final boolean loginOK;
	private final String sessionID;
	private final String registerDate;
	private final String registerTime;

	public static class Builder {
		// Required parameters
		private String deviceID;
		private boolean loginOK;
		private String sessionID;
		private String registerDate;
		private String registerTime;

		// Optional parameters

		public Builder(String vDeviceID, boolean vLoginOK, String vSessionID, String vDate, String vTime) {
			this.deviceID = vDeviceID;
			this.loginOK = vLoginOK;
			this.sessionID = vSessionID;
			this.registerDate = vDate;
			this.registerTime = vTime;		
		} // Builder constructor - no args

		public ConfirmRegister build() {
			return new ConfirmRegister(this);
		} // method build
		
	} // class Builder

	private ConfirmRegister(Builder builder) {
		deviceID = builder.deviceID;
		loginOK = builder.loginOK;
		sessionID = builder.sessionID;
		registerDate = builder.registerDate;
		registerTime = builder.registerTime;
	} // builder constructor

	public String getDeviceID() {
		return deviceID;
	}

	public boolean isLoginOK() {
		return loginOK;
	}

	public String getSessionID() {
		return sessionID;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public String getRegisterTime() {
		return registerTime;
	}
	
} // class ConfirmRegister
