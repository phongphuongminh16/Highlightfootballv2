package edu.niitict.highlightfootball.main;

/**
 * 
 * QuangMinh them vao chuc cap nhat view
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import edu.niitict.highlightfootball.R;
import edu.niitict.highlightfootball.Utils.MakeJsonArrayRequest;

public class Now_videoyoutube extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {
	public static final String TAG = Now_videoview.class.getSimpleName();

	private YouTubePlayer YPlayer;
	private static final String youtubeDeveloperkey = "AIzaSyAbPfz4sPqvwyLO3yXQtT34UiE9hX_AlqQ";
	private static final int RECOVERY_DIALOG_REQUES = 1;
	private String linkyoutube;
	private TextView textname;
	private String videoId;// minh them vao video id de post len server biet dc
							// video dc xem chua?

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.now_videoyoutube);

		Intent intent = getIntent();
		Bundle link = intent.getExtras();
		String chuoi = link.getString("link");
		String name = link.getString("name");
		String type = link.getString("type");

		// quang minhlay video id
		videoId = link.getString("id");

		// Tach chuoi lay id youtube
		String[] tokens = chuoi.split("=");
		for (String token : tokens) {
			linkyoutube = token;
			Log.d("ok", linkyoutube);
		}

		textname = (TextView) findViewById(R.id.text_name);
		textname.setText(name + " - " + type);
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(youtubeDeveloperkey, this);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// cap nhat so luong xem

		MakeJsonArrayRequest.makeRequestUpdateView(videoId);
	}

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider,
			YouTubeInitializationResult erroReason) {

		if (erroReason.isUserRecoverableError()) {
			erroReason.getErrorDialog(this, RECOVERY_DIALOG_REQUES).show();

		} else {
			String erroMesage = String.format("Loi khi mo ung dung Youtube",
					erroReason.toString());
			Toast.makeText(getApplication(), erroMesage, Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RECOVERY_DIALOG_REQUES) {

			getYouTubePlayerProvider().initialize(youtubeDeveloperkey, this);
		}
	}

	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}

	@Override
	public void onInitializationSuccess(Provider provider,
			YouTubePlayer player, boolean wasRestore) {
		// TODO Auto-generated method stub
		player.setPlayerStateChangeListener(playStateChangeListener);
		player.setPlaybackEventListener(playbackEventlistener);
		if (!wasRestore) {
			YPlayer = player;
			YPlayer.cueVideo(linkyoutube);
		}

	}

	private PlaybackEventListener playbackEventlistener = new PlaybackEventListener() {

		@Override
		public void onStopped() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSeekTo(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPlaying() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPaused() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onBuffering(boolean arg0) {
			// TODO Auto-generated method stub

		}
	};
	private PlayerStateChangeListener playStateChangeListener = new PlayerStateChangeListener() {

		@Override
		public void onVideoStarted() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onVideoEnded() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLoading() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLoaded(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onError(ErrorReason arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAdStarted() {
			// TODO Auto-generated method stub

		}
	};

}
