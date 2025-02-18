package com.iticbcn.paupedros.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Companyia;

public class CompanyiaDAO {
  private final SessionFactory sessionFactory;

  public CompanyiaDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void crearCompanyia(Companyia comp) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.persist(comp);
        sess.getTransaction().commit();
      } catch (HibernateException e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error en Hibernate: " + e.getMessage());
        }
      } catch (Exception e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error inesperado: " + e.getMessage());
        }
      }
    }
  }

  public Companyia obtenirCompanyia(Long companyiaId) {
    Companyia companyia = null;
    try (Session session = sessionFactory.openSession()) {
      companyia = session.find(Companyia.class, companyiaId);
    } catch (HibernateException e) {
      System.err.println("Error en Hibernate: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error inesperado: " + e.getMessage());
    }
    return companyia;
  }

  public void actualitzarCompanyia(Companyia comp) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.merge(comp);
        sess.getTransaction().commit();
      } catch (HibernateException e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error en Hibernate: " + e.getMessage());
        }
      } catch (Exception e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error inesperado: " + e.getMessage());
        }
      }
    }
  }

  public void eliminarCompanyia(Companyia comp) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.remove(comp);
        sess.getTransaction().commit();
      } catch (HibernateException e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error en Hibernate: " + e.getMessage());
        }
      } catch (Exception e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error inesperado: " + e.getMessage());
        }
      }
    }
  }
}