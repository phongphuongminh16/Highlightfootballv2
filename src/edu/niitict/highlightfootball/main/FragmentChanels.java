package edu.niitict.highlightfootball.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import edu.niitict.highlightfootball.R;
import edu.niitict.highlightfootball.Utils.Const;
import edu.niitict.highlightfootball.adapter.ChanelAdapter;
import edu.niitict.highlightfootball.app.AppController;
import edu.niitict.highlightfootball.main.model.ChanelModel;

@SuppressLint("InflateParams")
public class FragmentChanels extends Fragment {

	private ListView lvChanel;
	private List<ChanelModel> arrChanel;
	private ChanelAdapter adapter;

	// private Bitmap[] arriconLogo = new Bitmap[25];
	int gioihan;
	int dem = 0;

	private static final String urlJson = Const.SERVER_URL
			+ Const.END_POINT_FAVORITE;
	private String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog pDialog;

	public FragmentChanels() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View view = inflater.inflate(R.layout.chanels_fragment, null);

		pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

		lvChanel = (ListView) view.findViewById(R.id.lv_chanels);
		arrChanel = new ArrayList<ChanelModel>();

		adapter = new ChanelAdapter(getActivity(), arrChanel);

		lvChanel.setAdapter(adapter);

		// request server
		makeJsonArrayRequest();

		lvChanel.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				if (arrChanel.get(position).getIsYoutube().equals("True")) {
					Intent intentYoutube = new Intent(getActivity(),
							Now_videoyoutube.class);
					Bundle link1 = new Bundle();
					link1.putString("id", arrChanel.get(position).getId());
					link1.putString("link", arrChanel.get(position).getLink());
					link1.putString("name", arrChanel.get(position)
							.getNameVideo());
					link1.putString("type", arrChanel.get(position)
							.getNameTournament());
					intentYoutube.putExtras(link1);
					startActivity(intentYoutube);
				} else {
					Intent intentvideoview = new Intent(getActivity(),
							Now_videoview.class);
					Bundle link2 = new Bundle();
					link2.putString("id", arrChanel.get(position).getId());
					link2.putString("link", arrChanel.get(position).getLink());
					link2.putString("name", arrChanel.get(position)
							.getNameVideo());
					link2.putString("type", arrChanel.get(position)
							.getNameTournament());
					intentvideoview.putExtras(link2);
					startActivity(intentvideoview);
				}
			}

		});

		return view;
	}

	private void makeJsonArrayRequest() {
		showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(urlJson,
				new Response.Listener<JSONArray>() {
					// private Object ivCurent;

					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
							gioihan = response.length();
							for (int i = 0; i < response.length(); i++) {

								JSONObject person = (JSONObject) response
										.get(i);

								String _id = person.getString("_id");
								// String favorite =
								// person.getString("favorite");
								//
								// JSONObject guest = person
								// .getJSONObject("guest");
								// String _id_guest = guest.getString("_id");
								// String logo_guest = guest.getString("logo");
								// String name_guest = guest.getString("name");

								// JSONObject home =
								// person.getJSONObject("home");
								// String _id_home = home.getString("_id");
								// String logo_home = home.getString("logo");
								// String name_home = home.getString("name");

								JSONObject type = person.getJSONObject("type");
								// String _id_type = type.getString("_id");
								String name_type = type.getString("name");

								String isView = person.getString("isView");
								String isYoutube = person.getString("isYoutbe");
								String link = person.getString("link");
								String name = person.getString("name");
								// String note = person.getString("note");
								String tag = person.getString("tag");
								String thumnail = person.getString("thumnail");
								String time = person.getString("time");

								// Minh cho them id de post len server biet la
								// video dc xem chua
								ChanelModel chanelModel = new ChanelModel(
										thumnail, name, name_type, time,
										isView, tag, isYoutube, link);
								chanelModel.setId(_id);
								arrChanel.add(chanelModel);

							}

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getActivity(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
						adapter = new ChanelAdapter(getActivity(), arrChanel);
						lvChanel.setAdapter(adapter);
						hidepDialog();
						Log.d("Chanels", arrChanel.size() + "");

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(getActivity(), error.getMessage(),
								Toast.LENGTH_SHORT).show();
						hidepDialog();
					}
				});

		// Adding request to request queue
		// AppController.getInstance().addToRequestQueue(req);
		AppController.getInstance().addToRequestQueue(req);
		// test

	}

	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidepDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}
}
