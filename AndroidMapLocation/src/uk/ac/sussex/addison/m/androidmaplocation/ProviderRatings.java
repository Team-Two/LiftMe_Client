//111113 - MtpA -	Class created.  Takes a data from rating_data.xml
//					Initial proof of concept

package uk.ac.sussex.addison.m.androidmaplocation;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProviderRatings extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		// storing string resources into Array
		String[] ratings = getResources().getStringArray(R.array.ratings);

		// Binding resources Array to ListAdapter
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.provider_rating, R.id.provider_rating_label, ratings));

		ListView ratingList = getListView();
    }
}
