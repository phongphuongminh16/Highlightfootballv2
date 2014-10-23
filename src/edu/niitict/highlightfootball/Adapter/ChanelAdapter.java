package edu.niitict.highlightfootball.Adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import edu.niitict.highlightfootball.R;
import edu.niitict.highlightfootball.app.AppController;
import edu.niitict.highlightfootball.main.model.ChanelModel;

@SuppressLint("InflateParams")
public class ChanelAdapter extends BaseAdapter {
	private List<ChanelModel> arrChanel;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ImageLoader imageLoader = AppController.getInstance()
			.getImageLoader();

	public ChanelAdapter(Context context, List<ChanelModel> list) {
		// TODO Auto-generated constructor stub
		this.arrChanel = list;
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	// cu co phan tu nao thi se tao ra item do
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = convertView;
		if (mLayoutInflater == null)
			mLayoutInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (view == null) {
			view = mLayoutInflater.inflate(R.layout.chanel_item, null);
		}
		// lay phan tu mang tuong ung de truyen cho cac thanh phan cau tao len
		// item
		ChanelModel chanelModel = arrChanel.get(position);

		NetworkImageView imgLogo = (NetworkImageView) view
				.findViewById(R.id.img_logo);
		TextView tvNamevideo = (TextView) view.findViewById(R.id.tvNamevideo);
		TextView tvNametuornament = (TextView) view
				.findViewById(R.id.tvNametournament);

		TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
		TextView tvView = (TextView) view.findViewById(R.id.tvViewvideo);
		TextView tvTag = (TextView) view.findViewById(R.id.tvTagvideo);

		// truyen gia tri cua cac thuoc tinh cho cac thanh phan cau tao len item
		imgLogo.setImageUrl(chanelModel.getIconLogo(), imageLoader);
		tvNamevideo.setText(chanelModel.getNameVideo());
		tvNametuornament.setText(chanelModel.getNameTournament());
		tvTime.setText(chanelModel.getTime());
		tvView.setText(chanelModel.getView());
		tvTag.setText(chanelModel.getTag());

		return view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrChanel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrChanel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
