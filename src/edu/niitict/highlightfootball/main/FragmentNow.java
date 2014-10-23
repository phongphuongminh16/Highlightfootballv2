package edu.niitict.highlightfootball.main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
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
import edu.niitict.highlightfootball.adapter.Now_adapter;
import edu.niitict.highlightfootball.app.AppController;
import edu.niitict.highlightfootball.main.model.Now_model;

@SuppressLint("InflateParams")
public class FragmentNow extends Fragment {

	private ListView listNow;
	private Now_adapter myAdapter;
	private ArrayList<Now_model> mydata;
	private String[] manghome = new String[25];
	private String[] mangguest = new String[25];
	private String[] mangtime = new String[25];
	private String[] manglink = new String[25];
	private String[] mangname = new String[25];
	private String[] mangtype = new String[25];
	private String[] mangisYoutube = new String[25];
	private Bitmap[] mangiconleft = new Bitmap[25];
	private Bitmap[] mangiconright = new Bitmap[25];
	int gioihan;
	int dem = 0;
	// --------------------------------------
	// json array response url
	private String urlJsonArry = Const.SERVER_URL + Const.END_POINT_FAVORITE;
	private static String TAG = MainActivity.class.getSimpleName();

	// Progress dialog
	private ProgressDialog pDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.now_fragment, null);

		listNow = (ListView) view.findViewById(R.id.listView_Now);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		listNow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (mangisYoutube[position].equals("True")) {
					Intent intentYoutube = new Intent(getActivity(),
							Now_videoyoutube.class);
					Bundle link1 = new Bundle();
					link1.putString("link", manglink[position]);
					link1.putString("name", mangname[position]);
					link1.putString("type", mangtype[position]);
					intentYoutube.putExtras(link1);
					startActivity(intentYoutube);
				} else {
					Intent intentvideoview = new Intent(getActivity(),
							Now_videoview.class);
					Bundle link2 = new Bundle();
					link2.putString("link", manglink[position]);
					link2.putString("name", mangname[position]);
					link2.putString("type", mangtype[position]);
					intentvideoview.putExtras(link2);
					startActivity(intentvideoview);
				}
			}
		});

		return view;
	}

	private void makeJsonArrayRequest() {

		showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
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

								// String _id = person.getString("_id");
								// String favorite =
								// person.getString("favorite");

								JSONObject guest = person
										.getJSONObject("guest");
								// String _id_guest = guest.getString("_id");
								String logo_guest = guest.getString("logo");
								String name_guest = guest.getString("name");

								JSONObject home = person.getJSONObject("home");
								// String _id_home = home.getString("_id");
								String logo_home = home.getString("logo");
								String name_home = home.getString("name");

								JSONObject type = person.getJSONObject("type");
								// String _id_type = type.getString("_id");
								String name_type = type.getString("name");

								// String isView = person.getString("isView");
								String isYoutube = person.getString("isYoutbe");
								String link = person.getString("link");
								String name = person.getString("name");
								// String note = person.getString("note");
								// String tag = person.getString("tag");
								// String thumnail =
								// person.getString("thumnail");
								String time = person.getString("time");

								Bitmap icon_guest = getImageBitmap(logo_guest);
								Bitmap icon_home = getImageBitmap(logo_home);

								Now_model model = new Now_model(icon_home,
										icon_guest, name_home, name_guest, time);
								mydata.add(model);

								mangguest[i] = name_guest;
								manghome[i] = name_home;
								mangtime[i] = time;
								mangiconleft[i] = icon_home;
								mangiconright[i] = icon_guest;
								mangisYoutube[i] = isYoutube;
								manglink[i] = link;
								mangname[i] = name;
								mangtype[i] = name_type;

								myAdapter.notifyDataSetChanged();

								Log.d("ok", name_guest + "   " + name_home
										+ "   " + time);

							}

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getActivity(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}

						hidepDialog();

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

	private Bitmap getImageBitmap(String url) {
		Bitmap bm = null;

		try {
			URL aURL = new URL(url);
			URLConnection conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
		} catch (IOException e) {
			Log.e("TAG", "Error getting bitmap", e);
		}
		return bm;
	}

	@Override
	public void onResume() {
		dem++;

		if (dem <= 1) {
			// TODO Auto-generated method stub
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);

			mydata = new ArrayList<Now_model>();
			for (int i = 0; i < gioihan; i++) {
				Now_model model = new Now_model(mangiconleft[i],
						mangiconright[i], manghome[i], mangguest[i],
						mangtime[i]);
				mydata.add(model);
			}

			myAdapter = new Now_adapter(getActivity(), R.layout.now_item,
					mydata);
			listNow.setAdapter(myAdapter);
			mydata.clear();
			makeJsonArrayRequest();
		} else // Khong can load tren server nua.
		{
			mydata.clear();
			mydata = new ArrayList<Now_model>();
			for (int i = 0; i < gioihan; i++) {
				Now_model model = new Now_model(mangiconleft[i],
						mangiconright[i], manghome[i], mangguest[i],
						mangtime[i]);
				mydata.add(model);
				myAdapter = new Now_adapter(getActivity(), R.layout.now_item,
						mydata);
				listNow.setAdapter(myAdapter);
			}
		}
		super.onResume();
	}

}
