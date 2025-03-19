/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.proyectozoologico;

/**
 *
 * @author pabli
 */
// Subclase Mamifero
public class Mamifero extends Animal {
    private String habitat;

    public Mamifero(String nombre, int edad, double peso, double alimentoDiario, String habitat) {
        super(nombre, edad, peso, alimentoDiario);
        this.habitat = habitat;
    }

    @Override
    public String getTipoAlimento() {
        return "carne y plantas";
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @Override
    public String toString() {
        return "Mamifero - " + super.toString() + ", Habitat: " + habitat;
    }
    
    @Override
    public String toCSV() {
        return super.toCSV() + "," + habitat;
    }
}
