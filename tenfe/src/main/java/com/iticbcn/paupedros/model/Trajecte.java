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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Trajecte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String origen;
  private String desti;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "tren_id")
  private Tren tren;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "trajecte_estacio", joinColumns = @JoinColumn(name = "trajecte_id"), inverseJoinColumns = @JoinColumn(name = "estacio_id"))
  private Set<Estacio> estacions = new HashSet<>();

  public Trajecte() {
  }

  public Trajecte(String origen, String desti) {
    this.origen = origen;
    this.desti = desti;
  }

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrigen() {
    return origen;
  }

  public void setOrigen(String origen) {
    this.origen = origen;
  }

  public String getDesti() {
    return desti;
  }

  public void setDesti(String desti) {
    this.desti = desti;
  }

  public Tren getTren() {
    return tren;
  }

  public void setTren(Tren tren) {
    this.tren = tren;
  }

  public Set<Estacio> getEstacions() {
    return estacions;
  }

  public void setEstacions(Set<Estacio> estacions) {
    this.estacions = estacions;
  }

  @Override
  public String toString() {
    return "Trajecte{" +
        "id=" + id +
        ", origen='" + origen + '\'' +
        ", desti='" + desti + '\'' +
        ", tren=" + (tren != null ? tren.getId() : "null") +
        ", estacions=" + estacions +
        '}';
  }
}