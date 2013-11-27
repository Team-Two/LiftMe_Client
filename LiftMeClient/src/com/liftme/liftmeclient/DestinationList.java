//261113 - MtpA -	Refactored to reflect destination list and to draw data from destinations.xml
//111113 - MtpA -	Class created.  List of providers taken from provider_data.xml in values folder
//					Initial proof of concept

package com.liftme.liftmeclient;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class DestinationList extends ListActivity {
	
	private String callingActivity;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// storing string resources into Array
		String[] destinationLines = getResources().getStringArray(R.array.destinations);
		String[] destinationValues = new String[3];
		String[] destinations = new String[destinationLines.length];

		int listPos = -1;
		for (String currLine : destinationLines) {
			destinationValues = currLine.split(":");
			int stringPos = -1;
			listPos++;
			for (String lineVal : destinationValues) {
				stringPos++;
				if (stringPos == 0) {
					destinations[listPos] = lineVal;
				} else {
					break;
				}
			}
		}
		// Binding resources Array to ListAdapter
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.destination_list, R.id.destination_list, destinations));

		ListView destinationList = getListView();
		// listening to single list item on click
		destinationList.setOnItemClickListener(new destinationListItemListener());
	}
	
	public class destinationListItemListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
			Intent currIntent = getIntent();
			Bundle currBundle = currIntent.getExtras();
			if (currBundle!=null) {
				callingActivity = (String) currBundle.get("Activity");
				Toast.makeText(getBaseContext(),">>>>" + callingActivity, Toast.LENGTH_LONG).show();			
			} else {
				callingActivity = "Liftee";
				Toast.makeText(getBaseContext(),"Nothing !", Toast.LENGTH_LONG).show();			
			}
			// selected item
			String destination = ((TextView) view).getText().toString();

			// Launching new Activity on selecting single List Item
			Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
			// sending data to new activity
			mainIntent.putExtra("destination", destination);
			mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(mainIntent);
		}
		
	}
}
