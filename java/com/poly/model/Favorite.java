package com.poly.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FAVORITES database table.
 * 
 */
@Entity
@Table(name="FAVORITES")
@NamedQuery(name="Favorite.findAll", query="SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_FV")
	private int idFv;

	@Column(name="LIKE_DATE")
	private String likeDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="ID_USER")
	private User user;

	//bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name="ID_VIDEO")
	private Video video;

	public Favorite() {
	}

	public int getIdFv() {
		return this.idFv;
	}

	public void setIdFv(int idFv) {
		this.idFv = idFv;
	}

	public String getLikeDate() {
		return this.likeDate;
	}

	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
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