package com.liftme.liftmeclient;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.TestCase;

public class TestUserMarker extends TestCase {

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testUserMarkerBuilder() {
		Calendar currDate = Calendar.getInstance();
		try {
			UserMarker testDevice = new UserMarker.Builder("fa001", 0, 0, currDate).build();
		} catch (DataValidationException buildEx) {
			fail("Device ID fail in UserMarker constructor");
		}
	}
	
	@Test
	public void testUserMarkerBuilderDeviceIDFail() {
		Calendar currDate = Calendar.getInstance();
		try {
			UserMarker testDevice = new UserMarker.Builder("", 0, 0, currDate).build();
			fail("Should have thrown exception");
		} catch (DataValidationException e) {
			;
		}
	}
		
}
