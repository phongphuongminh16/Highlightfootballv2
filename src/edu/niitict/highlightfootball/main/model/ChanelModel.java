package edu.niitict.highlightfootball.main.model;

public class ChanelModel {
	private String iconLogo, id;// them vao id de post len server
	private String nameVideo, nameTournament, time, view, tag, isYoutube, link;

	public ChanelModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsYoutube() {
		return isYoutube;
	}

	public void setIsYoutube(String isYoutube) {
		this.isYoutube = isYoutube;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public ChanelModel(String iconLogo, String nameVideo,
			String nameTournament, String time, String view, String tag,
			String isYoutbe, String link) {
		super();
		this.iconLogo = iconLogo;
		this.nameVideo = nameVideo;
		this.nameTournament = nameTournament;
		this.time = time;
		this.view = view;
		this.tag = tag;
		this.isYoutube = isYoutbe;
		this.link = link;
	}

	public String getIconLogo() {
		return iconLogo;
	}

	public void setIconLogo(String iconLogo) {
		this.iconLogo = iconLogo;
	}

	public String getNameVideo() {
		return nameVideo;
	}

	public void setNameVideo(String nameVideo) {
		this.nameVideo = nameVideo;
	}

	public String getNameTournament() {
		return nameTournament;
	}

	public void setNameTournament(String nameTournament) {
		this.nameTournament = nameTournament;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}