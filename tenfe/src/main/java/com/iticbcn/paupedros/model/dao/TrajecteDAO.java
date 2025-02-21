package com.iticbcn.paupedros.model.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.iticbcn.paupedros.model.Trajecte;

public class TrajecteDAO extends GenDAOImpl<Trajecte> {

  public TrajecteDAO(SessionFactory sessionFactory) {
    super(sessionFactory,Trajecte.class);
  }

  public Map<String, Long> countTrajectesPerTren() {
    try (Session session = getSessionFactory().openSession()) {
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
}