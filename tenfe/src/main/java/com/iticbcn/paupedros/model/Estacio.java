package com.iticbcn.paupedros.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Estacio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nom", nullable = false, unique = true)
  private String nom;

  @ManyToMany(mappedBy = "estacions")
  private Set<Trajecte> trajectes = new HashSet<>();

  public Estacio() {
  }

  public Estacio(String nom) {
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

  public Set<Trajecte> getTrajectes() {
    return trajectes;
  }

  public void setTrajectes(Set<Trajecte> trajectes) {
    this.trajectes = trajectes;
  }

  @Override
  public String toString() {
    return "Estacio {" +
        "id=" + id +
        ", nom='" + nom + '\'' +
        ", nombre de trajectes = [no inicialitzats]" +
        '}';
  }
}