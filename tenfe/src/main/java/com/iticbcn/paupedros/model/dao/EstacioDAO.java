package com.iticbcn.paupedros.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Estacio;

public class EstacioDAO {
  private final SessionFactory sessionFactory;

  public EstacioDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void crearEstacio(Estacio inst) {
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

  public Estacio obtenirEstacio(Long EstacioId) {
    Estacio estacio = null;
    try (Session session = sessionFactory.openSession()) {
      estacio = session.find(Estacio.class, EstacioId);
    } catch (HibernateException e) {
      System.err.println("Error en Hibernate: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error inesperado: " + e.getMessage());
    }
    return estacio;
  }

  public void actualitzarEstacio(Estacio inst) {
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

  public void eliminarEstacio(Estacio inst) {
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