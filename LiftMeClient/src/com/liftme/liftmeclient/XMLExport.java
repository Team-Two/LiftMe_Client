//201113 - MtpA -	Add method writeUserXML (User.XSD) - overloaded String and Calendar methods
//101113 - MtpA -	Add method writeJourneyXML (Journey.XSD)
//					Refactor writeMarkerXML to deal with change in Location.XSD (project ext 2)
//081113 - MtpA -	Refactor getMarkerID to getDeviceID
//311013 - MtpA -	Changed ID data export from Integer to String to reflect XML changes

package com.liftme.liftmeclient;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class XMLExport {

	public String writeMarkerXml(UserMarker vCurrMarker){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "Location");
			serializer.startTag("", "Marker");
			serializer.attribute("", "deviceid", vCurrMarker.getDeviceID());
			serializer.startTag("", "Latitude");
			serializer.text(Double.toString(vCurrMarker.getMarkerLat()));
			serializer.endTag("", "Latitude");
			serializer.startTag("", "Longditude");
			serializer.text(Double.toString(vCurrMarker.getMarkerLong()));
			serializer.endTag("", "Longditude");
			serializer.startTag("", "Date");
			serializer.text(vCurrMarker.getMarkerDate());
			serializer.endTag("", "Date");
			serializer.startTag("", "Time");
			serializer.text(vCurrMarker.getMarkerTime());
			serializer.endTag("", "Time");
			serializer.endTag("", "Marker");
			serializer.endTag("", "Location");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	} // method writeMarkerXml
	
	public String writeJourneyXml(Journey vJourney){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "Journey");
			serializer.startTag("", "NewJourney");
			serializer.attribute("", "deviceid", vJourney.getDeviceID());
			serializer.startTag("", "SessionID");
			serializer.text(vJourney.getSessionID());
			serializer.endTag("", "SessionID");
			serializer.startTag("", "StartLatitude");
			serializer.text(Double.toString(vJourney.getStartLatitude()));
			serializer.endTag("", "StartLatitude");
			serializer.startTag("", "StartLongditude");
			serializer.text(Double.toString(vJourney.getStartLongditude()));
			serializer.endTag("", "StartLongditude");
			serializer.startTag("", "EndLatitude");
			serializer.text(Double.toString(vJourney.getEndLatitude()));
			serializer.endTag("", "EndLatitude");
			serializer.startTag("", "EndLongditude");
			serializer.text(Double.toString(vJourney.getEndLongditude()));
			serializer.endTag("", "EndLongditude");
			serializer.startTag("", "RouteName");
			serializer.text(vJourney.getRouteName());
			serializer.endTag("", "RouteName");
			serializer.startTag("", "Date");
			serializer.text(vJourney.getJourneyDate());
			serializer.endTag("", "Date");
			serializer.startTag("", "Time");
			serializer.text(vJourney.getJourneyTime());
			serializer.endTag("", "Time");
			serializer.endTag("", "NewJourney");
			serializer.endTag("", "Journey");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	} // method writeJourneyXml
	
	public String writeUserXml(LoginObjects vLoginObjects, String vDeviceID, String vDate, String vTime) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "RegisterUser");
			serializer.startTag("", "User");
			serializer.startTag("", "Name");
			serializer.text(vLoginObjects.getUsername());
			serializer.endTag("", "Name");
			serializer.startTag("", "Email");
			serializer.text(vLoginObjects.getEmail());
			serializer.endTag("", "Email");
			serializer.startTag("", "DeviceID");
			serializer.text(vDeviceID);
			serializer.endTag("", "DeviceID");
			serializer.startTag("", "Password");
			serializer.text(vLoginObjects.getPassword());
			serializer.endTag("", "Password");
			serializer.startTag("", "Date");
			serializer.text(vDate);
			serializer.endTag("", "Date");
			serializer.startTag("", "Time");
			serializer.text(vTime);
			serializer.endTag("", "Time");
			serializer.endTag("", "User");
			serializer.endTag("", "RegisterUser");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	} // method writeUserXml
	
	public String writeUserXml(LoginObjects vLoginObjects, String vDeviceID, Calendar vCalDate) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String vDate = dateFormat.format(vCalDate.getTime()).substring(0, 10);
		String vTime = dateFormat.format(vCalDate.getTime()).substring(11);
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "RegisterUser");
			serializer.startTag("", "User");
			serializer.startTag("", "Name");
			serializer.text(vLoginObjects.getUsername());
			serializer.endTag("", "Name");
			serializer.startTag("", "Email");
			serializer.text(vLoginObjects.getEmail());
			serializer.endTag("", "Email");
			serializer.startTag("", "DeviceID");
			serializer.text(vDeviceID);
			serializer.endTag("", "DeviceID");
			serializer.startTag("", "Password");
			serializer.text(vLoginObjects.getPassword());
			serializer.endTag("", "Password");
			serializer.startTag("", "Date");
			serializer.text(vDate);
			serializer.endTag("", "Date");
			serializer.startTag("", "Time");
			serializer.text(vTime);
			serializer.endTag("", "Time");
			serializer.endTag("", "User");
			serializer.endTag("", "RegisterUser");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	} // method writeUserXml (overloaded - calendar argument)

} // class XMLExport
