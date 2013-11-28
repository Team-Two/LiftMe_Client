//281113 - MtpA -	Create class
package com.liftme.liftmeclient;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PreviousTrip extends Activity {

	private Resources resourceVals;
	private ArrayList<Trip> prevTrips;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp_trips_title);
		getPreviousTrips();
		// Setup the list view
		final ListView prevTripsListView = (ListView) findViewById(R.id.list);
		final PreviousTripsAdapter prevTripsAdapter = new PreviousTripsAdapter(this, R.layout.temp_trips);
		prevTripsListView.setAdapter(prevTripsAdapter);
		// Populate the list, through the adapter
		for(Trip currTrip : prevTrips) {
			prevTripsAdapter.add(currTrip);
		}
	} // method onCreate
	
	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume

	private void getPreviousTrips() {
		resourceVals = getResources();
		XMLImport importFile = new XMLImport();
		try {
			String xmlData = importFile.readSDXMLData(resourceVals.getString(R.string.registerXmlDir), resourceVals.getString(R.string.tripsXmlFileName));				
			XMLImport parserXML = new XMLImport();
			prevTrips = (parserXML.importPreviousTrips(xmlData));
			if (prevTrips == null) {
				Toast.makeText(getBaseContext(),"Nothing in prevTrips ArrayList", Toast.LENGTH_LONG).show();				
			}
		} catch (Exception e) {
			Toast.makeText(getBaseContext(),"All fell apart reading in previous trips", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * ViewHolder allows us to avoid re-looking up view references
	 * Since views are recycled, these references will never change
	 */
	private static class ViewHolder {
		public TextView destinationView;
		public TextView dateView;
		public TextView timeView;
		public TextView lifterView;
	}
	
	public final class PreviousTripsAdapter extends ArrayAdapter<Trip> {

		private final int prevTripsResource;

		public PreviousTripsAdapter(final Context context, final int vPrevTripsResource) {
			super(context, 0);
			this.prevTripsResource = vPrevTripsResource;
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			// We need to get the best view (re-used if possible) and then
			// retrieve its corresponding ViewHolder, which optimizes lookup efficiency
			final View view = getWorkingView(convertView);
			final ViewHolder viewHolder = getViewHolder(view);
			final Trip prevTrip = getItem(position);
			viewHolder.destinationView.setText("Destination : " + prevTrip.getTripDest());
			viewHolder.dateView.setText("Date : " + prevTrip.getTripDate());
			viewHolder.timeView.setText("Time : " + prevTrip.getTripTime());
			viewHolder.lifterView.setText("Lifter : " + prevTrip.getTripLifter());
			return view;
		}

		private View getWorkingView(final View convertView) {
			// The workingView is basically just the convertView re-used if possible
			// or inflated new if not possible
			View workingView = null;
			if(null == convertView) {
				final Context context = getContext();
				final LayoutInflater inflater = (LayoutInflater)context.getSystemService
						(Context.LAYOUT_INFLATER_SERVICE);
				workingView = inflater.inflate(prevTripsResource, null);
			} else {
				workingView = convertView;
			}
			return workingView;
		}
		private ViewHolder getViewHolder(final View workingView) {
			// The viewHolder allows us to avoid re-looking up view references
			// Since views are recycled, these references will never change
			final Object tag = workingView.getTag();
			ViewHolder viewHolder = null;
			if(null == tag || !(tag instanceof ViewHolder)) {
				viewHolder = new ViewHolder();
				viewHolder.destinationView = (TextView) workingView.findViewById(R.id.tripdestination);
				viewHolder.dateView = (TextView) workingView.findViewById(R.id.tripdate);
				viewHolder.timeView = (TextView) workingView.findViewById(R.id.triptime);
				viewHolder.lifterView = (TextView) workingView.findViewById(R.id.triplifter);
				workingView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) tag;
			}
			return viewHolder;
		}
	}
}