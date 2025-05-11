/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.proyectozoologico;

/**
 *
 * @author pabli
 */
public class Ave extends Animal {
    private boolean vuela;

    public Ave(int idAnimal, String nombre, int edad, double peso, double alimentoDiario, boolean vuela) {
        super(idAnimal, nombre, edad, peso, alimentoDiario);
        this.vuela = vuela;
    }

    @Override
    public String getTipoAlimento() {
        return "semillas e insectos";
    }

    public boolean isVuela() {
        return vuela;
    }

    public void setVuela(boolean vuela) {
        this.vuela = vuela;
    }

    @Override
    public String toString() {
        return "Ave - " + super.toString() + ", Vuela: " + (vuela ? "Si" : "No");
    }
    
    @Override
    public String toCSV() {
        return super.toCSV() + "," + vuela;
    }
}