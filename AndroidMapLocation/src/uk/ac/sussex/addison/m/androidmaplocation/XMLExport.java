//101113 - MtpA -	Add method writeJourneyXML (Journey.XSD)
//					Refactor writeMarkerXML to deal with change in Location.XSD (project ext 2)
//081113 - MtpA -	Refactor getMarkerID to getDeviceID
//311013 - MtpA -	Changed ID data export from Integer to String to reflect XML changes

package uk.ac.sussex.addison.m.androidmaplocation;

import java.io.StringWriter;

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
	
} // class XMLExport
