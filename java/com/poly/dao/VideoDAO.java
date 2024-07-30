package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.model.Video;
import com.poly.utils.JpaUtils;

public class VideoDAO {
	private EntityManager em = JpaUtils.getEntityManager();

	public Video create(Video entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}

	}

	public Video update(Video entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public Video remove(String id) {
		try {
			em.getTransaction().begin();
			Video entity = this.findById(id);
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public List<Video> findAll() {		
		String jpql = "SELECT o FROM Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		//TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		List<Video> list = query.getResultList();
		System.out.println(list);
		return list;
	}
	
	public Video findById(String id) {
		Video entity = em.find(Video.class, id);
		return entity;
	}
}
