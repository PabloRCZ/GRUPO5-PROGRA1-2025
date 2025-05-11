/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.proyectozoologico;

/**
 *
 * @author pabli
 */
public class Reptil extends Animal {
    private boolean venenoso;

    public Reptil(int idAnimal, String nombre, int edad, double peso, double alimentoDiario, boolean venenoso) {
        super(idAnimal, nombre, edad, peso, alimentoDiario);
        this.venenoso = venenoso;
    }

    @Override
    public String getTipoAlimento() {
        return "pequeños vertebrados";
    }

    public boolean isVenenoso() {
        return venenoso;
    }

    public void setVenenoso(boolean venenoso) {
        this.venenoso = venenoso;
    }

    @Override
    public String toString() {
        return "Reptil - " + super.toString() + ", Venenoso: " + (venenoso ? "Si" : "No");
    }
    
    @Override
    public String toCSV() {
        return super.toCSV() + "," + venenoso;
    }
}