package com.liftme.liftmeclient;

import junit.framework.TestCase;

public class TestLiftMeDataValidation extends TestCase {
	
	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testIsDeviceIDValid() {
		assertTrue("tc01 - Valid DeviceID - expect true", LiftMeDataValidator.isDeviceIDValid("ff0a"));
		assertFalse("tc02 - Pass empty deviceID expecting false", LiftMeDataValidator.isDeviceIDValid(""));
		assertFalse("tc03 - Invalid deviceID string > 64 expect false", LiftMeDataValidator.isDeviceIDValid("123456789011234567890112345678901123456789011234567890112345678901"));
	}

	public void testIsLatValid() {
		assertTrue("tc04 - Valid latitude - expect true", LiftMeDataValidator.isLatValid(0));
		assertTrue("tc05 - Valid latitude - expect true", LiftMeDataValidator.isLatValid(-90));
		assertFalse("tc06 - Invalid latitude - expect false", LiftMeDataValidator.isLatValid(-90.1));
		assertTrue("tc07 - Valid latitude - expect true", LiftMeDataValidator.isLatValid(-10.0567));
		assertTrue("tc08 - Valid latitude - expect true", LiftMeDataValidator.isLatValid(2.00000123));
		assertTrue("tc09 - Valid latitude - expect true", LiftMeDataValidator.isLatValid(89.99999));
		assertFalse("tc10 - Invalid latitude - expect false", LiftMeDataValidator.isLatValid(90000.1234));		
	}

	public void testIsLongValid() {
		assertTrue("tc11 - Valid longditude - expect true", LiftMeDataValidator.isLongValid(0));
		assertTrue("tc12 - Valid longditude - expect true", LiftMeDataValidator.isLongValid(-180));
		assertFalse("tc13 - Invalid longditude - expect false", LiftMeDataValidator.isLongValid(-180.1));
		assertTrue("tc14 - Valid longditude - expect true", LiftMeDataValidator.isLongValid(-10.0567));
		assertTrue("tc15 - Valid longditude - expect true", LiftMeDataValidator.isLongValid(2.00000123));
		assertTrue("tc16 - Valid longditude - expect true", LiftMeDataValidator.isLongValid(179.99999));
		assertFalse("tc17 - Invalid longditude - expect false", LiftMeDataValidator.isLongValid(90000.1234));		
	}

	public void testIsDateValid() {
		assertTrue("tc18 - Valid date - expect true", LiftMeDataValidator.isDateValid("2013-11-17", "20:30:00"));
		assertTrue("tc19 - Valid date - expect true", LiftMeDataValidator.isDateValid("1901-11-17", "20:30:00"));
		assertTrue("tc20 - Valid date - expect true", LiftMeDataValidator.isDateValid("2013-01-01", "00:00:00"));
		assertFalse("tc21 - Invalid date - expect false", LiftMeDataValidator.isDateValid("a", "b"));
		assertFalse("tc22 - Invalid date - expect false", LiftMeDataValidator.isDateValid("2013-02-30", "19:00:00"));
		assertFalse("tc23 - Invalid date - expect false", LiftMeDataValidator.isDateValid("2050-02-02", "19:00:00"));
		assertTrue("tc24 - Valid date - expect true", LiftMeDataValidator.isDateValid("2012-02-29", "10:00:00"));
		assertFalse("tc25 - Invalid date - expect false", LiftMeDataValidator.isDateValid("2013-00-01", "19:00:00"));
		assertFalse("tc26 - Invalid date - expect false", LiftMeDataValidator.isDateValid("2013-13-01", "19:00:00"));
		assertFalse("tc27 - Invalid date - expect false", LiftMeDataValidator.isDateValid("2013-01-32", "19:00:00"));
		assertFalse("tc28 - Invalid date - expect false", LiftMeDataValidator.isDateValid("2013-04-31", "19:00:00"));
		assertFalse("tc29 - Invalid date - expect false", LiftMeDataValidator.isDateValid("2013-01-00", "19:00:00"));
	}

}
