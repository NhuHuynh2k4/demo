package com.poly.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHARES database table.
 * 
 */
@Entity
@Table(name="SHARES")
@NamedQuery(name="Share.findAll", query="SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SHARE")
	private int idShare;

	@Column(name="EMAILS")
	private String emails;

	@Column(name="SHARE_DATE")
	private String shareDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="ID_USER")
	private User user;

	//bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name="ID_VIDEO")
	private Video video;

	public Share() {
	}

	public int getIdShare() {
		return this.idShare;
	}

	public void setIdShare(int idShare) {
		this.idShare = idShare;
	}

	public String getEmails() {
		return this.emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getShareDate() {
		return this.shareDate;
	}

	public void setShareDate(String shareDate) {
		this.shareDate = shareDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}