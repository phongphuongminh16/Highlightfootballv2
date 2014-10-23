package edu.niitict.highlightfootball.main;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import edu.niitict.highlightfootball.R;

/**
 * 
 */
public class MainActivity extends Activity {
	private ActionBar actionBar;
	private FragmentNow fragmentNow;
	private FragmentChanels fragmentChanels;
	private MyTabsListenner tabNow;
	private MyTabsListenner tabChanels;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		setTab("Now", "Match");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);
		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_search:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * @Override public boolean enableHomeIconActionBack() { return false; }
	 */

	// Set Tab
	public void setTab(String tileNow, String titleChanels) {
		/* setup the action bar */
		fragmentNow = new FragmentNow();
		fragmentChanels = new FragmentChanels();
		tabNow = new MyTabsListenner(fragmentNow);
		tabChanels = new MyTabsListenner(fragmentChanels);
		ActionBar.Tab tabActionMatch = actionBar.newTab().setText(titleChanels)
				.setTabListener(tabChanels);
		actionBar.addTab(tabActionMatch, 0);
		actionBar.setSelectedNavigationItem(0);
		ActionBar.Tab tabActionNow = actionBar.newTab().setText(tileNow)
				.setTabListener(tabNow);
		actionBar.addTab(tabActionNow, 1);
		/* add tabMessage */

	}

	/* create class custom tabListener */
	class MyTabsListenner implements ActionBar.TabListener {
		public Fragment fragment;

		public MyTabsListenner(Fragment fragment) {
			super();
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.replace(R.id.frame_from_class_container, fragment);

		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.remove(fragment);
		}

	}
}
