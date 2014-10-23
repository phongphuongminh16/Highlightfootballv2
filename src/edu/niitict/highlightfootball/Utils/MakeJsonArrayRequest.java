package edu.niitict.highlightfootball.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * @author QuangMinh
 * 
 */

import edu.niitict.highlightfootball.app.AppController;

public class MakeJsonArrayRequest {
	public static final String TAG = MakeJsonArrayRequest.class.getSimpleName();

	// phuong thuc de post len server update luot xem

	public static void makeRequestUpdateView(String videoId) {
		JSONObject json = new JSONObject();
		try {
			json.put("id", videoId);
			Log.d(TAG, videoId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
				Const.SERVER_URL + Const.END_POINT_UPDATEVIEW, json,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stu
						Log.d(TAG, "success");

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.d(TAG, "error");
					}

				}) {

		};
		AppController.getInstance().addToRequestQueue(jsonObjReq, "video");
	}
}
