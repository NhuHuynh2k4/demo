package com.poly.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USER")
	private String idUser;

	@Column(name="ADMIN_USER")
	private boolean adminUser;

	@Column(name="EMAIL_USER")
	private String emailUser;

	@Column(name="FULLNAME_USER")
	private String fullnameUser;

	@Column(name="PASS_USER")
	private String passUser;

	//bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy="user")
	private List<Favorite> favorites;

	public User(String idUser, boolean adminUser, String emailUser, String fullnameUser) {
		super();
		this.idUser = idUser;
		this.adminUser = adminUser;
		this.emailUser = emailUser;
		this.fullnameUser = fullnameUser;
	}

	//bi-directional many-to-one association to Share
	@OneToMany(mappedBy="user")
	private List<Share> shares;

	public User() {
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(boolean adminUser) {
		this.adminUser = adminUser;
	}

	public String getEmailUser() {
		return this.emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getFullnameUser() {
		return this.fullnameUser;
	}

	public void setFullnameUser(String fullnameUser) {
		this.fullnameUser = fullnameUser;
	}

	public String getPassUser() {
		return this.passUser;
	}

	public void setPassUser(String passUser) {
		this.passUser = passUser;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setUser(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setUser(null);

		return favorite;
	}

	public List<Share> getShares() {
		return this.shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public Share addShare(Share share) {
		getShares().add(share);
		share.setUser(this);

		return share;
	}

	public Share removeShare(Share share) {
		getShares().remove(share);
		share.setUser(null);

		return share;
	}

}