package com.iticbcn.paupedros.model.dao;

import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Tren;

public class TrenDAO extends GenDAOImpl<Tren> {

  public TrenDAO(SessionFactory sessionFactory) {
    super(sessionFactory, Tren.class);
  }
}