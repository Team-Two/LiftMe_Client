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

public class StatsFeedbacks extends Activity {

	private Resources resourceVals;
	private StatsInfo stats;
	private TextView profileName;
	private TextView distTravelled;
	private TextView lifterCount;
	private TextView lifteeCount;
	private TextView address;
	private TextView telNo;
	private TextView plateNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statsfeedbacks);
		profileName = (TextView) findViewById(R.id.name);
		distTravelled = (TextView) findViewById(R.id.distance);
		lifterCount = (TextView) findViewById(R.id.lifter);
		lifteeCount = (TextView) findViewById(R.id.liftee);
		address = (TextView) findViewById(R.id.address);
		telNo = (TextView) findViewById(R.id.telno);
		plateNo = (TextView) findViewById(R.id.plateno);
		Toast.makeText(getBaseContext(),"Looking at the Stats & Feedbacks", Toast.LENGTH_LONG).show();
		getProfileInfo();
		final ListView feedbacksView = (ListView) findViewById(R.id.listPro);
		final FeedbacksAdapter feedbacksAdapter = new FeedbacksAdapter(this, R.layout.temp_feedbacks);
		feedbacksView.setAdapter(feedbacksAdapter);
		for(Feedback currFeedback : stats.getComments()) {
			feedbacksAdapter.add(currFeedback);
		}

		
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
			String xmlData = importFile.readSDXMLData(resourceVals.getString(R.string.profileXmlFileName), resourceVals.getString(R.string.profileXmlFileName));				
			XMLImport parserXML = new XMLImport();
			stats = (parserXML.importStatsInfo(xmlData));
			if (stats == null) {
				Toast.makeText(getBaseContext(),"Nothing in profile ArrayList", Toast.LENGTH_LONG).show();				
			}
			else{
				profileName.setText(stats.getName());
				distTravelled.setText("Total Distance Travelled: "+ stats.getDtravelled());
				lifterCount.setText("Number Of Times Being Lifter: "+ stats.getLifterCount());
				lifteeCount.setText("Number Of Times Being Liftee: "+ stats.getLifteeCount());
				address.setText(stats.getAddress());
				telNo.setText(stats.getTelNo());
				plateNo.setText(stats.getPlateNo());
			}
		} catch (Exception e) {
			Toast.makeText(getBaseContext(),"All fell apart reading in stats & feedbacks", Toast.LENGTH_LONG).show();
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
		public TextView fb_dislike;
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
			viewHolder.fb_comment.setText("Comment : " + feedbacks.getComment());
			viewHolder.fb_like.setText(feedbacks.getLike());
			viewHolder.fb_dislike.setText(feedbacks.getDislike());
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
				viewHolder.fb_dislike = (TextView) workingView.findViewById(R.id.dislikes);
				workingView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) tag;
			}
			return viewHolder;
		}
	}

}
