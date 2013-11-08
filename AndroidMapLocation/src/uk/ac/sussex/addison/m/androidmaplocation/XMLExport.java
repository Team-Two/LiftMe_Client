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
			serializer.attribute("", "ID", vCurrMarker.getDeviceID());
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
} // class XMLExport
