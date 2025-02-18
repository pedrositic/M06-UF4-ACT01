package com.iticbcn.paupedros.model.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.iticbcn.paupedros.model.Trajecte;

public class TrajecteDAO {
  private final SessionFactory sessionFactory;

  public TrajecteDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void crearTrajecte(Trajecte inst) {
    if (inst == null) {
      System.err.println("L'objecte Trajecte és nul. No es pot crear.");
      return;
    }

    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        // Fem merge de l'objecte per tal de persistir-lo a la base de dades
        sess.merge(inst);
        sess.getTransaction().commit();
        System.out.println("Trajecte creat amb èxit.");
      } catch (HibernateException e) {
        // Rollback en cas d'error de Hibernate
        if (sess.getTransaction() != null && sess.getTransaction().isActive()) {
          sess.getTransaction().rollback();
        }
        System.err.println("Error en Hibernate: " + e.getMessage());
        e.printStackTrace(); // Mostra la traça completa de l'error per depuració
      } catch (Exception e) {
        // Rollback en cas d'altres errors inesperats
        if (sess.getTransaction() != null && sess.getTransaction().isActive()) {
          sess.getTransaction().rollback();
        }
        System.err.println("Error inesperat: " + e.getMessage());
        e.printStackTrace(); // Mostra la traça completa de l'error per depuració
      }
    } catch (Exception e) {
      // Captura errors relacionats amb l'obertura de la sessió
      System.err.println("Error en obrir la sessió: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public Trajecte obtenirTrajecte(Long TrajecteId) {
    System.out.println("Obtenir trajecte amb id: " + TrajecteId);
    Trajecte Trajecte = null;
    try (Session session = sessionFactory.openSession()) {
      Trajecte = session.find(Trajecte.class, TrajecteId);
    } catch (HibernateException e) {
      System.err.println("Error en Hibernate: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error inesperado: " + e.getMessage());
    }
    return Trajecte;
  }

  public List<Trajecte> obtenirTotsElsTrajectes() {
    try (Session session = sessionFactory.openSession()) {
      Query<Trajecte> query = session.createQuery("FROM Trajecte", Trajecte.class);
      return query.list();
    } catch (Exception e) {
      System.err.println("Error en obtenir tots els trajectes: " + e.getMessage());
      e.printStackTrace();
      return Collections.emptyList(); // Retorna una llista buida en cas d'error
    }
  }

  public void actualitzarTrajecte(Trajecte inst) {
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

  public void eliminarTrajecte(Trajecte inst) {
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

  public Map<String, Long> countTrajectesPerTren() {
    try (Session session = sessionFactory.openSession()) {
      String hql = "SELECT t.tren.model, COUNT(t.id) FROM Trajecte t GROUP BY t.tren.model";
      Query<Object[]> query = session.createQuery(hql, Object[].class);
      List<Object[]> results = query.list();

      Map<String, Long> resultMap = new HashMap<>();
      for (Object[] result : results) {
        String trenModel = (String) result[0];
        Long count = (Long) result[1];
        resultMap.put(trenModel, count);
      }
      return resultMap;
    } catch (Exception e) {
      System.err.println("Error en executar countTrajectesPerTren: " + e.getMessage());
      e.printStackTrace();
      return Collections.emptyMap(); // Retorna un mapa buit en cas d'error
    }
  }

  public void createTrajecte(Trajecte traj) {
    try(Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.persist(traj);
      session.getTransaction().commit();
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}