package edu.niitict.highlightfootball.main.model;

import android.graphics.Bitmap;

public class Now_model {

	private Bitmap icon_left, icon_right;
	private String text_left, text_right, text_time;

	public Now_model(Bitmap icon_left, Bitmap icon_right, String text_left,
			String text_right, String text_time) {
		super();
		this.icon_left = icon_left;
		this.icon_right = icon_right;
		this.text_left = text_left;
		this.text_right = text_right;
		this.text_time = text_time;
	}

	public Bitmap getIcon_left() {
		return icon_left;
	}

	public void setIcon_left(Bitmap icon_left) {
		this.icon_left = icon_left;
	}

	public Bitmap getIcon_right() {
		return icon_right;
	}

	public void setIcon_right(Bitmap icon_right) {
		this.icon_right = icon_right;
	}

	public String getText_left() {
		return text_left;
	}

	public void setText_left(String text_left) {
		this.text_left = text_left;
	}

	public String getText_right() {
		return text_right;
	}

	public void setText_right(String text_right) {
		this.text_right = text_right;
	}

	public String getText_time() {
		return text_time;
	}

	public void setText_time(String text_time) {
		this.text_time = text_time;
	}

}
