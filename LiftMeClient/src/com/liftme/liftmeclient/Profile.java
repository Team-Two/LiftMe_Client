//261113 - MtpA -	Created class

package com.liftme.liftmeclient;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity {

	private Resources resourceVals;
	private ProfileInfo profile;
	final TextView profileName = (TextView) findViewById(R.id.proName);
	final TextView distTravelled = (TextView) findViewById(R.id.distance);
	final TextView lifterCount = (TextView) findViewById(R.id.lifter);
	final TextView lifteeCount = (TextView) findViewById(R.id.liftee);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp_profile_title);
		Toast.makeText(getBaseContext(),"Looking at the profile", Toast.LENGTH_LONG).show();
		getProfileInfo();
		final ListView feedbacksView = (ListView) findViewById(R.id.listPro);
		final FeedbacksAdapter feedbacksAdapter = new FeedbacksAdapter(this, R.layout.temp_feedbacks);
		feedbacksView.setAdapter(feedbacksAdapter);

		
		// Populate the list, through the adapter

	} // method onCreate

	@Override
	protected void onResume() {
		super.onResume();
	} // method onResume

	private void getProfileInfo() {
		resourceVals = getResources();
		XMLImport importFile = new XMLImport();
		try {
			String xmlData = importFile.readSDXMLData(resourceVals.getString(R.string.registerXmlDir), resourceVals.getString(R.string.profileXmlFileName));				
			XMLImport parserXML = new XMLImport();
			profile = (parserXML.importProfileInfo(xmlData));
			if (profile == null) {
				Toast.makeText(getBaseContext(),"Nothing in prevTrips ArrayList", Toast.LENGTH_LONG).show();				
			}
			else{
				profileName.setText(profile.getName());
				distTravelled.setText("Total Distance Travelled: "+ profile.getDtravelled());
				lifterCount.setText("Number Of Times Being Lifter: "+ profile.getLifterCount());
				lifteeCount.setText("Number Of Times Being Liftee: "+ profile.getLifteeCount());
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
		public TextView fb_nameDate;
		public TextView fb_comment;
		public TextView fb_like;
	}

	public final class FeedbacksAdapter extends ArrayAdapter<Feedback> {

		private final int feedbacksResource;

		public FeedbacksAdapter(final Context context, final int vFeedbacksResource) {
			super(context, 0);
			this.feedbacksResource = vFeedbacksResource;
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			// We need to get the best view (re-used if possible) and then
			// retrieve its corresponding ViewHolder, which optimizes lookup efficiency
			final View view = getWorkingView(convertView);
			final ViewHolder viewHolder = getViewHolder(view);
			final Feedback feedbacks = getItem(position);
			viewHolder.fb_nameDate.setText(feedbacks.getName()+" "+feedbacks.getDate());
			viewHolder.fb_comment.setText("Time : " + feedbacks.getComment());
			viewHolder.fb_like.setText("Lifter : " + feedbacks.getLike());
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
				workingView = inflater.inflate(feedbacksResource, null);
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
				viewHolder.fb_nameDate = (TextView) workingView.findViewById(R.id.nameDate);
				viewHolder.fb_comment = (TextView) workingView.findViewById(R.id.comment);
				viewHolder.fb_like = (TextView) workingView.findViewById(R.id.likes);
				workingView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) tag;
			}
			return viewHolder;
		}
	}

}
