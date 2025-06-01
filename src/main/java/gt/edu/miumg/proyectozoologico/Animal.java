/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.proyectozoologico;

/**
 *
 * @author pabli
 */
public abstract class Animal {
    private int idAnimal; // el campo que solicito el ing.
    private String nombre;
    private int edad;
    private double peso;
    private double alimentoDiario;

    public Animal(int idAnimal, String nombre, int edad, double peso, double alimentoDiario) {
        this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.alimentoDiario = alimentoDiario;
    }

    // Getter y Setter para idAnimal
    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    // Método abstracto que implementarán las subclases
    public abstract String getTipoAlimento();

    // Método para calcular alimento recursivamente
    public double calcularAlimento(int dias) {
        // Caso base
        if (dias <= 0) {
            return 0;
        }
        // Caso recursivo
        return alimentoDiario + calcularAlimento(dias - 1);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAlimentoDiario() {
        return alimentoDiario;
    }

    public void setAlimentoDiario(double alimentoDiario) {
        this.alimentoDiario = alimentoDiario;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + " anos, Peso: " + peso + " kg, " +
               "Alimento diario: " + alimentoDiario + " libras de " + getTipoAlimento();
    }
    
    // Método para convertir a formato CSV
    public String toCSV() {
        return getClass().getSimpleName() + "," + nombre + "," + edad + "," + peso + "," + alimentoDiario;
    }
}
    

