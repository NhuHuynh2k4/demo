package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.model.Favorite;
import com.poly.utils.JpaUtils;

public class FavoriteDAO {
	private EntityManager em = JpaUtils.getEntityManager();
	
	public List<Favorite> findAll() {		
		String jpql = "SELECT o FROM Favorite o where o.user.idUser=?0";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		//TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		List<Favorite> list = query.getResultList();
		System.out.println(list);
		return list;
	}
}
