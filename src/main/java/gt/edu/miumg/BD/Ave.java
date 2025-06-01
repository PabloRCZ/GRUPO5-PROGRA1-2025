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
@Table(name = "ave")
public class Ave implements Serializable {
    
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "especie", length = 100)
    private String especie;
    
    @Column(name = "habitat", length = 100)
    private String habitat;
    
    @Column(name = "puede_volar", length = 2)
    private String puedeVolar;
    
    // Constructor vacío
    public Ave() {
    }
    
    // Constructor con parámetros
    public Ave(Long id, String nombre, String especie, String habitat, String puedeVolar) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.habitat = habitat;
        this.puedeVolar = puedeVolar;
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
    
    public String getPuedeVolar() {
        return puedeVolar;
    }
    
    public void setPuedeVolar(String puedeVolar) {
        this.puedeVolar = puedeVolar;
    }
    
    @Override
    public String toString() {
        return "Ave{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", habitat='" + habitat + '\'' +
                ", puedeVolar='" + puedeVolar + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ave ave = (Ave) o;
        return id != null && id.equals(ave.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
