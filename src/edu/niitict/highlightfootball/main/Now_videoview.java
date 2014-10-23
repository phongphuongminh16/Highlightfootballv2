package edu.niitict.highlightfootball.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import edu.niitict.highlightfootball.R;

public class Now_videoview extends Activity {

	VideoView videonow;
	TextView textname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.now_videoview);

		Intent intent = getIntent();
		Bundle link = intent.getExtras();
		String linkvideoview = link.getString("link");
		String name = link.getString("name");
		String type = link.getString("type");

		videonow = (VideoView) findViewById(R.id.now_video);
		videonow.setVideoPath(linkvideoview);
		textname = (TextView) findViewById(R.id.text_name);
		textname.setText(name+" - "+type);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		Uri video = Uri.parse(linkvideoview);
		videonow.setMediaController(new MediaController(this));
		videonow.setVideoURI(video);
		videonow.start();
		videonow.requestFocus();

	}
}
