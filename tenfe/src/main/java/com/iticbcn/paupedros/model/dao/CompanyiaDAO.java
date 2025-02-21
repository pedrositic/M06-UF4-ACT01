package com.iticbcn.paupedros.model.dao;

import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Companyia;

public class CompanyiaDAO extends GenDAOImpl<Companyia> {

  public CompanyiaDAO(SessionFactory sessionFactory)  {
    super(sessionFactory,Companyia.class);
  }

  
}