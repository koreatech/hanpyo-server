package com.github.hanpyo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class JpaTestSupport {

	private final EntityManagerFactory emf;

	public JpaTestSupport(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public <T> T save(T entity) {
		EntityManager em = entityManager();
		EntityTransaction trx = em.getTransaction();
		try {
			trx.begin();
			em.persist(entity);
			trx.commit();
			em.clear();
			return entity;
		} catch (Exception e) {
			trx.rollback();
		} finally {
			em.close();
		}
		return null;
	}

	public <T> Iterable<T> saveAll(Iterable<T> entities) {
		EntityManager em = entityManager();
		EntityTransaction trx = em.getTransaction();
		try {
			trx.begin();
			for (T e : entities) {
				em.persist(e);
			}
			trx.commit();
			em.clear();
			return entities;
		} catch (Exception e) {
			trx.rollback();
		} finally {
			em.close();
		}
		return null;
	}

	public void deleteAll(EntityPath<?> path) {
		EntityManager em = entityManager();
		JPAQueryFactory jpaQueryFactory = jpaQueryFactory(em);
		EntityTransaction trx = em.getTransaction();
		try {
			trx.begin();

			jpaQueryFactory.delete(path).execute();

			trx.commit();
			em.clear();
		} catch (Exception e) {
			trx.rollback();
		} finally {
			em.close();
		}
	}

	public void deleteAllFromAllTables() {
		EntityManager em = entityManager();
		JPAQueryFactory jpaQueryFactory = jpaQueryFactory(em);
		EntityTransaction trx = em.getTransaction();
		try {
			trx.begin();

			// 모든 테이블 삭제하는 쿼리 실행

			trx.commit();
			em.clear();
		} catch (Exception e) {
			trx.rollback();
		} finally {
			em.close();
		}
	}

	private EntityManager entityManager() {
		return emf.createEntityManager();
	}

	private JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
