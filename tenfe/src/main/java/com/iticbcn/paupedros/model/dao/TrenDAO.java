package com.iticbcn.paupedros.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Tren;

public class TrenDAO {
  private final SessionFactory sessionFactory;

  public TrenDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void crearTren(Tren inst) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.persist(inst);
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

  public Tren obtenirTren(Long TrenId) {
    Tren Tren = null;
    try (Session session = sessionFactory.openSession()) {
      Tren = session.find(Tren.class, TrenId);
    } catch (HibernateException e) {
      System.err.println("Error en Hibernate: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error inesperado: " + e.getMessage());
    }
    return Tren;
  }

  public void actualitzarTren(Tren inst) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.merge(inst);
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

  public void eliminarTren(Tren inst) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.remove(inst);
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