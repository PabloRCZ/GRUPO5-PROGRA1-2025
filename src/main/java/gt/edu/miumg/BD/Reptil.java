/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.BD;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "reptil")
public class Reptil {
    
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "especie", length = 100)
    private String especie;
    
    @Column(name = "habitat", length = 100)
    private String habitat;
    
    @Column(name = "es_venenoso", length = 2)
    private String esVenenoso;
    
    // Constructor vacío
    public Reptil() {
    }
    
    // Constructor con parámetros
    public Reptil(Long id, String nombre, String especie, String habitat, String esVenenoso) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.habitat = habitat;
        this.esVenenoso = esVenenoso;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEspecie() {
        return especie;
    }
    
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public String getHabitat() {
        return habitat;
    }
    
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    
    public String getEsVenenoso() {
        return esVenenoso;
    }
    
    public void setEsVenenoso(String esVenenoso) {
        this.esVenenoso = esVenenoso;
    }
    
    @Override
    public String toString() {
        return "Reptil{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", habitat='" + habitat + '\'' +
                ", esVenenoso='" + esVenenoso + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reptil reptil = (Reptil) o;
        return id != null && id.equals(reptil.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}