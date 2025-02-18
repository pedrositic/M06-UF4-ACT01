package com.iticbcn.paupedros.model;

import java.util.HashSet;
import java.util.Set;

public class Companyia {

  private Long id;

  private String nom;

  private Set<Tren> trens = new HashSet<>();

  public Companyia() {
  }

  public Companyia(String nom) {
    this.nom = nom;
  }

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Set<Tren> getTrens() {
    return trens;
  }

  public void setTrens(Set<Tren> trens) {
    this.trens = trens;
  }

  public void addTren(Tren tren) {
    trens.add(tren);
  }

  @Override
  public String toString() {
    return "Companyia {" +
        "id=" + id +
        ", nom='" + nom + '\'' +
        ", nombre de trens=" + trens.size() +
        '}';
  }
}