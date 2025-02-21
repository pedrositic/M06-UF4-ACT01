package com.iticbcn.paupedros.model.dao;

import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Estacio;

public class EstacioDAO extends GenDAOImpl<Estacio> {

  public EstacioDAO(SessionFactory sessionFactory) {
    super(sessionFactory,Estacio.class);
  }

  
}