//281113 - MtpA -	Added method importPreviousTrips
//131113 - MtpA -	Initial class code : Reads in SD card file, creates a DOM, processes elements

package com.liftme.liftmeclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class XMLImport {

	public XMLImport() {
		
	} // constructor - no args
	
	public String readSDXMLData(String fileDir, String fileName) throws Exception {
		File root = null;
		// check for SDcard  
		root = Environment.getExternalStorageDirectory(); 

		//check sdcard permission 
		if (root.canRead()){
			// set up file reader
			File fullDir = new File(root.getAbsolutePath() + fileDir);
			File xmlFile = new File(fullDir, fileName);
			FileReader xmlReader = new FileReader(xmlFile);
			BufferedReader xmlBuffer = new BufferedReader(xmlReader);

			// set up line strings
			String xmlDoc = "";
			String xmlLine = "";

			// loop through file filling data
			while ((xmlLine = xmlBuffer.readLine()) != null) {
				xmlDoc = xmlDoc + xmlLine;
			}
			xmlBuffer.close();
			return xmlDoc;		
		} else {
			return "";
		}
	} // method readSDXMLData
	
	public Document getDomElement(String xml){
		Document domDoc = null;
		DocumentBuilderFactory domDocbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder domDocBuilder = domDocbf.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			domDoc = domDocBuilder.parse(is);

		} catch (ParserConfigurationException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (SAXException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (IOException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		}
		return domDoc;
    } // method getDomElement
	
	public String getXMLValue(Element item, String str) {     
	    NodeList docNode = item.getElementsByTagName(str);       
	    return this.getElementValue(docNode.item(0));
	} // method getXMLValue
	 
	private final String getElementValue( Node docElement ) {
		Node child;
		if( docElement != null){
			if (docElement.hasChildNodes()){
				for( child = docElement.getFirstChild(); child != null; child = child.getNextSibling() ){
					if( child.getNodeType() == Node.TEXT_NODE  ){
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	} //method getElementValue
	
	public ConfirmRegister importConfirmRegister(String xmlData) {
	    final String KEY_USER = "User"; // parent node
	    final String KEY_DEVICEID = "DeviceID";
	    final String KEY_LOGINOK = "LoginOK";
	    final String KEY_SESSIONID = "SessionID";
	    final String KEY_DATE = "Date";
	    final String KEY_TIME = "Time";
	    String vDeviceID = "";
	    boolean vLoginOK = false;
	    String vSessionID = "";
	    String vDate = "";
	    String vTime = "";

	    Document doc = getDomElement(xmlData); // getting DOM element
        NodeList nodeList = doc.getElementsByTagName(KEY_USER);
        // looping through all item nodes <item>
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element nodeElement = (Element) nodeList.item(i);
            // adding each child node to variable list
	        vDeviceID = getXMLValue(nodeElement, KEY_DEVICEID);
	        vLoginOK = Boolean.parseBoolean(getXMLValue(nodeElement, KEY_LOGINOK));
	        vSessionID = getXMLValue(nodeElement, KEY_SESSIONID);
	        vDate = getXMLValue(nodeElement, KEY_DATE);
	        vTime = getXMLValue(nodeElement, KEY_TIME);
        }
        return new ConfirmRegister.Builder(vDeviceID, vLoginOK, vSessionID, vDate, vTime).build();
	} // method ConfirmRegister
	
	public ArrayList<Trip> importPreviousTrips(String xmlData) {
	    final String KEY_TRIP = "Trip"; // parent node
	    final String KEY_DESTINATION = "Destination";
	    final String KEY_DATE = "Date";
	    final String KEY_TIME = "Time";
	    final String KEY_LIFTER = "Lifter";
	    
	    ArrayList<Trip> prevTrips = new ArrayList<Trip>();
	    String vDestination = "";
	    String vLifter = "";
	    String vDate = "";
	    String vTime = "";

	    Document doc = getDomElement(xmlData); // getting DOM element
        NodeList nodeList = doc.getElementsByTagName(KEY_TRIP);
        // looping through all item nodes <item>
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element nodeElement = (Element) nodeList.item(i);
            // adding each child node to variable list
	        vDestination = getXMLValue(nodeElement, KEY_DESTINATION);
	        vDate = getXMLValue(nodeElement, KEY_DATE);
	        vTime = getXMLValue(nodeElement, KEY_TIME);
	        vLifter = getXMLValue(nodeElement, KEY_LIFTER);
	        try {
		        prevTrips.add(new Trip.Builder(vDestination, vDate, vTime, vLifter).build());				
			} catch (DataValidationException dataEx) {
				prevTrips = null;				
			}
        }
        return prevTrips;
	} // method importPreviousTrips
	
	public ProfileInfo importProfileInfo(String xmlData) throws ParserConfigurationException, SAXException, IOException {
		
		final String USER_NAME = "Name"; // parent node
		final String DIST_TRAVELLED = "DistTravelled";
		final String LIFTER_COUNT = "LifterCount";
		final String LIFTEE_COUNT = "LifteeCount";
		final String ROOT = "ProfileInfo";

		final String SUB_ROOT = "Feedback";
		final String FB_NAME = "FBName";
		final String FB_DATE = "FBDate";
		final String FB_COMMENT = "FBComment";
		final String FB_LIKE = "FBLike";

		String name = "";
		String distTravelled = "";
		String lifterCount = "";
		String lifteeCount = "";

		String fbName = "";
		String fbDate = "";
		String fbComment = "";
		String fbLike = "";

		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();

		File fXmlFile = new File(xmlData);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName(ROOT);

		// looping through all item nodes <item>
		for (int i = 0; i < nodeList.getLength(); i++) {

			Node nNode = nodeList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				name=eElement.getElementsByTagName(USER_NAME).item(0).getTextContent();
				distTravelled=eElement.getElementsByTagName(DIST_TRAVELLED).item(0).getTextContent();
				lifterCount=eElement.getElementsByTagName(LIFTER_COUNT).item(0).getTextContent();
				lifteeCount=eElement.getElementsByTagName(LIFTEE_COUNT).item(0).getTextContent();

			}

		}

		NodeList nodeList2 = doc.getElementsByTagName(SUB_ROOT);

		for (int i = 0; i < nodeList2.getLength(); i++) {

			Node nNode = nodeList2.item(i);
			Element eElement = (Element) nNode;

			System.out.println("Name : " + eElement.getElementsByTagName(FB_NAME).item(0).getTextContent());
			System.out.println("Date : " + eElement.getElementsByTagName(FB_DATE).item(0).getTextContent());
			System.out.println("Comment: " + eElement.getElementsByTagName(FB_COMMENT).item(0).getTextContent());
			System.out.println("Like : " + eElement.getElementsByTagName(FB_LIKE).item(0).getTextContent());

		}

			feedbacks.add(new Feedback(fbName,fbDate,fbComment,fbLike));

		return new ProfileInfo(name, distTravelled, lifterCount, lifteeCount, feedbacks);
	} // method ProfileInfo
	
} // class XMLImport
