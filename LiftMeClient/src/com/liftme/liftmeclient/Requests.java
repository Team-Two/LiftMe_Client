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

public class Requests extends Activity {

	private Resources resourceVals;
	private ArrayList<TripRequest> requests;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_list_title);
		getRequests();
		// Setup the list view
		final ListView prevTripsListView = (ListView) findViewById(R.id.list);
		final RequestsAdapter prevTripsAdapter = new RequestsAdapter(this, R.layout.request_list);
		prevTripsListView.setAdapter(prevTripsAdapter);
		// Populate the list, through the adapter
		for(TripRequest currTrip : requests) {
			prevTripsAdapter.add(currTrip);
		}
	} // method onCreate
	
	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume

	private void getRequests() {
		resourceVals = getResources();
		XMLImport importFile = new XMLImport();
		try {
			String xmlData = importFile.readSDXMLData(resourceVals.getString(R.string.requestsXmlDir), resourceVals.getString(R.string.requestsXmlFileName));				
			XMLImport parserXML = new XMLImport();
			requests = (parserXML.importRequests(xmlData));
			if (requests == null) {
				Toast.makeText(getBaseContext(),"Nothing in requests ArrayList", Toast.LENGTH_LONG).show();				
			}
		} catch (Exception e) {
			Toast.makeText(getBaseContext(),"All fell apart reading in requests", Toast.LENGTH_LONG).show();
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
		public TextView lifteeView;
		public TextView statusView;
	}
	
	public final class RequestsAdapter extends ArrayAdapter<TripRequest> {

		private final int prevTripsResource;

		public RequestsAdapter(final Context context, final int vPrevTripsResource) {
			super(context, 0);
			this.prevTripsResource = vPrevTripsResource;
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			// We need to get the best view (re-used if possible) and then
			// retrieve its corresponding ViewHolder, which optimizes lookup efficiency
			final View view = getWorkingView(convertView);
			final ViewHolder viewHolder = getViewHolder(view);
			final TripRequest prevTrip = getItem(position);
			viewHolder.destinationView.setText(prevTrip.getDestination());
			viewHolder.dateView.setText(prevTrip.getDate());
			viewHolder.timeView.setText(prevTrip.getTime());
			viewHolder.lifteeView.setText(prevTrip.getLiftee());
			
			if(prevTrip.getStatus()!="Requested"){
				viewHolder.statusView.setText(prevTrip.getStatus());
			}
			else{
				viewHolder.statusView.setText(" ");
			}
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
				viewHolder.destinationView = (TextView) workingView.findViewById(R.id.tripdest);
				viewHolder.dateView = (TextView) workingView.findViewById(R.id.tripdate);
				viewHolder.timeView = (TextView) workingView.findViewById(R.id.triptime);
				viewHolder.lifteeView = (TextView) workingView.findViewById(R.id.username);
				
				if(viewHolder.statusView.getText()!=" "){
					workingView.findViewById(R.id.btnAccept).setVisibility(View.INVISIBLE);
					workingView.findViewById(R.id.btnDecline).setVisibility(View.INVISIBLE);
				}
				workingView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) tag;
			}
			return viewHolder;
		}
	}
}