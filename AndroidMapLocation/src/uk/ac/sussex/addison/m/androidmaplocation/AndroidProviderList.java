//111113 - MtpA -	Class created.  List of providers taken from provider_data.xml in values folder
//					Initial proof of concept

package uk.ac.sussex.addison.m.androidmaplocation;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
public class AndroidProviderList extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// storing string resources into Array
		String[] providers = getResources().getStringArray(R.array.providers);

		// Binding resources Array to ListAdapter
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.provider_list, R.id.provider_label, providers));

		ListView providerList = getListView();
		// listening to single list item on click
		providerList.setOnItemClickListener(new providerListener());
	}
	
	public class providerListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
			// selected item
			String provider = ((TextView) view).getText().toString();

			// Launching new Activity on selecting single List Item
			Intent i = new Intent(getApplicationContext(), ProviderRatings.class);
			// sending data to new activity
			i.putExtra("provider", provider);
			startActivity(i);
		}
		
	}
}
