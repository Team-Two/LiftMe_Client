package com.liftme.liftmeclient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import android.content.res.Resources;
import android.os.Environment;

public class SDCard {

	public SDCard() {
		
	} // constructor - no args
	
	public Boolean writeToSD(String vXMLData, String vFileName, Resources vResourceVals){
		File root=null; 
		try { 
			// check for SDcard  
			root = Environment.getExternalStorageDirectory(); 

			//check sdcard permission 
			if (root.canWrite()){
				// create file directory
				File fileDir = new File(root.getAbsolutePath() + vResourceVals.getString(R.string.xmlDir));
				// check if directory already exists
				if (fileDir.exists() && fileDir.isDirectory()) {
					;
				} else {
					fileDir.mkdirs();
				} // end of if need to create dir
				// chain a buffer to a writer to a file and export the xml data
				File file= new File(fileDir, vFileName);
				FileWriter filewriter = new FileWriter(file); 
				BufferedWriter outBuffer = new BufferedWriter(filewriter); 
				outBuffer.write(vXMLData); 
				outBuffer.close(); 
				return true;
			} // end of if can write to sd card
		} catch (IOException e) { 
			return false;
		} // end of try-catch
		return true;
	} // method writeToSD
	
}
