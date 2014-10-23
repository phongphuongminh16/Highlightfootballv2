package edu.niitict.highlightfootball.main;

/**
 * @author QuangMinh
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import edu.niitict.highlightfootball.R;
import edu.niitict.highlightfootball.Utils.Const;
import edu.niitict.highlightfootball.adapter.ChanelAdapter;
import edu.niitict.highlightfootball.app.AppController;
import edu.niitict.highlightfootball.main.model.ChanelModel;

public class SearchResultsActivity extends Activity {

	private TextView txtQuery;
	private ListView lvResult;
	private ChanelAdapter adapter;
	private List<ChanelModel> arrChanel;
	private static final String urlJson = Const.SERVER_URL
			+ Const.END_POINT_SEARCH;
	private String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);

		// get the action bar
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Search");

		// Enabling Back navigation on Action Bar icon
		actionBar.setDisplayHomeAsUpEnabled(true);

		txtQuery = (TextView) findViewById(R.id.txtQuery);
		lvResult = (ListView) findViewById(R.id.lvSearchResut);
		arrChanel = new ArrayList<ChanelModel>();
		pDialog = new ProgressDialog(SearchResultsActivity.this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);
		handleIntent(getIntent());

		// set onclickitem lvResult
		lvResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				if (arrChanel.get(position).getIsYoutube().equals("True")) {
					Intent intentYoutube = new Intent(
							SearchResultsActivity.this, Now_videoyoutube.class);
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
					Intent intentvideoview = new Intent(
							SearchResultsActivity.this, Now_videoview.class);
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

	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	/**
	 * Handling intent data
	 */
	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			makeJsonArrayRequest(query);

		}

	}

	private void makeJsonArrayRequest(final String query) {
		showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(urlJson + query,
				new Response.Listener<JSONArray>() {
					// private Object ivCurent;

					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {

							for (int i = 0; i < response.length(); i++) {

								JSONObject person = (JSONObject) response
										.get(i);

								String _id = person.getString("_id");
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

								ChanelModel chanelModel = new ChanelModel(
										thumnail, name, name_type, time,
										isView, tag, isYoutube, link);
								chanelModel.setId(_id);
								arrChanel.add(chanelModel);

							}

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(SearchResultsActivity.this,
									"Error: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
						adapter = new ChanelAdapter(SearchResultsActivity.this,
								arrChanel);
						lvResult.setAdapter(adapter);
						hidepDialog();
						// Set title for result
						txtQuery.setText(getString(R.string.activity_SearchResult_title_1)
								+ arrChanel.size()
								+ getString(R.string.activity_SearchResult_title_2)
								+ " '" + query + "'");
						Log.d(TAG, arrChanel.size() + "");

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());

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
