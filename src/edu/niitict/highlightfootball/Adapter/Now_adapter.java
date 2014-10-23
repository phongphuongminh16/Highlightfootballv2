package edu.niitict.highlightfootball.Adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.niitict.highlightfootball.R;
import edu.niitict.highlightfootball.main.model.Now_model;

@SuppressLint("ViewHolder")
public class Now_adapter extends ArrayAdapter<Now_model> {

	private ArrayList<Now_model> arrdata;
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public Now_adapter(Context context, int textViewResourceId,
			ArrayList<Now_model> objects) {
		super(context, textViewResourceId, objects);
		this.arrdata = objects;
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View mView = convertView;
		mView = mLayoutInflater.inflate(R.layout.now_item, null);
		Now_model model = arrdata.get(position);

		TextView text_left = (TextView) mView.findViewById(R.id.text_left);
		TextView text_right = (TextView) mView.findViewById(R.id.text_right);
		TextView text_time = (TextView) mView.findViewById(R.id.text_time);
		ImageView icon_left = (ImageView) mView.findViewById(R.id.icon_left);
		ImageView icon_right = (ImageView) mView.findViewById(R.id.icon_right);

		text_left.setText(model.getText_left());
		text_right.setText(model.getText_right());
		text_time.setText(model.getText_time());
		icon_left.setImageBitmap(model.getIcon_left());
		icon_right.setImageBitmap(model.getIcon_right());
		return mView;
	}
}
