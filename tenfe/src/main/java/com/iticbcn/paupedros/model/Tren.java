package com.iticbcn.paupedros.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Tren {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String model;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "companyia_id", nullable = false)
  private Companyia companyia;

  @OneToMany(mappedBy = "tren", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Trajecte> trajectes = new HashSet<>();

  public Tren(){}

  public Tren(String model) {
    this.model = model;
  }

  public Tren(String model, Companyia comp) {
    this.model = model;
    this.companyia = comp;
  }

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Companyia getCompanyia() {
    return companyia;
  }

  public void setCompanyia(Companyia companyia) {
    this.companyia = companyia;
  }

  public Set<Trajecte> getTrajectes() {
    return trajectes;
  }

  public void setTrajectes(Set<Trajecte> trajectes) {
    this.trajectes = trajectes;
  }

  @Override
  public String toString() {
    return "Tren {" +
        "id=" + id +
        ", model='" + model + '\'' +
        ", companyia=" + (companyia != null ? companyia.getNom() : "Sense companyia") +
        '}';
  }

}
