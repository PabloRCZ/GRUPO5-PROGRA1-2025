/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.BD;


import gt.edu.miumg.BD.*;
import gt.edu.miumg.BD.exceptions.NonexistentEntityException;
import gt.edu.miumg.BD.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    
    private static Scanner scanner = new Scanner(System.in);
    private static AveJpaController aveController = new AveJpaController();
    private static MamiferoJpaController mamiferoController = new MamiferoJpaController();
    private static ReptilJpaController reptilController = new ReptilJpaController();
    
    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }
    
    public static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Opción 1");
            System.out.println("2. Opción 2");
            System.out.println("3. Gestión de Animales");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Opción 1");
                    break;
                case 2:
                    System.out.println("Has seleccionado la Opción 2");
                    break;
                case 3:
                    mostrarMenuAnimales();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }
    
    public static void mostrarMenuAnimales() {
        char opcion;
        do {
            System.out.println("\n=== FASE 3: GESTION DE ANIMALES ===");
            System.out.println("a: Trabajar con Mamifero");
            System.out.println("b: Trabajar con Ave");
            System.out.println("c: Trabajar con Reptil");
            System.out.println("d: Regresar al menu principal");
            System.out.print("Seleccione una opcion: ");
            
            opcion = scanner.nextLine().toLowerCase().charAt(0);
            
            switch (opcion) {
                case 'a':
                    mostrarMenuCRUD("Mamifero");
                    break;
                case 'b':
                    mostrarMenuCRUD("Ave");
                    break;
                case 'c':
                    mostrarMenuCRUD("Reptil");
                    break;
                case 'd':
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 'd');
    }
    
    public static void mostrarMenuCRUD(String tipoAnimal) {
        char opcion;
        do {
            System.out.println("\n=== GESTION DE " + tipoAnimal.toUpperCase() + " ===");
            System.out.println("C: Insertar");
            System.out.println("R: Consultar");
            System.out.println("U: Actualizar");
            System.out.println("D: Eliminar");
            System.out.println("V: Volver al menu de animales");
            System.out.print("Seleccione una opcion: ");
            
            opcion = scanner.nextLine().toLowerCase().charAt(0);
            
            switch (opcion) {
                case 'c':
                    insertarAnimal(tipoAnimal);
                    break;
                case 'r':
                    consultarAnimales(tipoAnimal);
                    break;
                case 'u':
                    actualizarAnimal(tipoAnimal);
                    break;
                case 'd':
                    eliminarAnimal(tipoAnimal);
                    break;
                case 'v':
                    System.out.println("Regresando al menu de animales...");
                    break;
                default:
                    System.out.println("Opción invalida. Intente de nuevo.");
            }
        } while (opcion != 'v');
    }
    
    // MÉTODOS PARA INSERTAR
    public static void insertarAnimal(String tipoAnimal) {
        try {
            System.out.println("\n=== INSERTAR " + tipoAnimal.toUpperCase() + " ===");
            
            System.out.print("Ingrese el ID: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer
            
            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Ingrese la especie: ");
            String especie = scanner.nextLine();
            
            System.out.print("Ingrese el habitat: ");
            String habitat = scanner.nextLine();
            
            switch (tipoAnimal) {
                case "Mamifero":
                    System.out.print("Ingrese el tipo de pelo: ");
                    String tipoPelo = scanner.nextLine();
                    Mamifero mamifero = new Mamifero(id, nombre, especie, habitat, tipoPelo);
                    mamiferoController.create(mamifero);
                    break;
                    
                case "Ave":
                    System.out.print("¿Puede volar? (SI/NO): ");
                    String puedeVolar = scanner.nextLine().toUpperCase();
                    Ave ave = new Ave(id, nombre, especie, habitat, puedeVolar);
                    aveController.create(ave);
                    break;
                    
                case "Reptil":
                    System.out.print("¿Es venenoso? (SI/NO): ");
                    String esVenenoso = scanner.nextLine().toUpperCase();
                    Reptil reptil = new Reptil(id, nombre, especie, habitat, esVenenoso);
                    reptilController.create(reptil);
                    break;
            }
            
            System.out.println("✓ " + tipoAnimal + " insertado exitosamente!");
            
        } catch (PreexistingEntityException e) {
            System.out.println("✗ Error: Ya existe un " + tipoAnimal.toLowerCase() + " con ese ID.");
        } catch (Exception e) {
            System.out.println("✗ Error al insertar " + tipoAnimal.toLowerCase() + ": " + e.getMessage());
        }
    }
    
    // MÉTODOS PARA CONSULTAR
    public static void consultarAnimales(String tipoAnimal) {
        try {
            System.out.println("\n=== CONSULTAR " + tipoAnimal.toUpperCase() + "S ===");
            
            switch (tipoAnimal) {
                case "Mamifero":
                    List<Mamifero> mamiferos = mamiferoController.findMamiferoEntities();
                    if (mamiferos.isEmpty()) {
                        System.out.println("No hay mamiferos registrados.");
                    } else {
                        System.out.println("Lista de Mamiferos:");
                        for (Mamifero m : mamiferos) {
                            System.out.println(m.toString());
                        }
                    }
                    break;
                    
                case "Ave":
                    List<Ave> aves = aveController.findAveEntities();
                    if (aves.isEmpty()) {
                        System.out.println("No hay aves registradas.");
                    } else {
                        System.out.println("Lista de Aves:");
                        for (Ave a : aves) {
                            System.out.println(a.toString());
                        }
                    }
                    break;
                    
                case "Reptil":
                    List<Reptil> reptiles = reptilController.findReptilEntities();
                    if (reptiles.isEmpty()) {
                        System.out.println("No hay reptiles registrados.");
                    } else {
                        System.out.println("Lista de Reptiles:");
                        for (Reptil r : reptiles) {
                            System.out.println(r.toString());
                        }
                    }
                    break;
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error al consultar " + tipoAnimal.toLowerCase() + "s: " + e.getMessage());
        }
    }
    
    // MÉTODOS PARA ACTUALIZAR
    public static void actualizarAnimal(String tipoAnimal) {
        try {
            System.out.println("\n=== ACTUALIZAR " + tipoAnimal.toUpperCase() + " ===");
            
            System.out.print("Ingrese el ID del " + tipoAnimal.toLowerCase() + " a actualizar: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer
            
            Object animal = null;
            switch (tipoAnimal) {
                case "Mamifero":
                    animal = mamiferoController.findMamifero(id);
                    break;
                case "Ave":
                    animal = aveController.findAve(id);
                    break;
                case "Reptil":
                    animal = reptilController.findReptil(id);
                    break;
            }
            
            if (animal == null) {
                System.out.println("✗ No se encontró un " + tipoAnimal.toLowerCase() + " con ID: " + id);
                return;
            }
            
            System.out.println("Datos actuales: " + animal.toString());
            System.out.println("Ingrese los nuevos datos:");
            
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Nueva especie: ");
            String especie = scanner.nextLine();
            
            System.out.print("Nuevo habitat: ");
            String habitat = scanner.nextLine();
            
            switch (tipoAnimal) {
                case "Mamifero":
                    System.out.print("Nuevo tipo de pelo: ");
                    String tipoPelo = scanner.nextLine();
                    Mamifero mamifero = new Mamifero(id, nombre, especie, habitat, tipoPelo);
                    mamiferoController.edit(mamifero);
                    break;
                    
                case "Ave":
                    System.out.print("¿Puede volar? (SI/NO): ");
                    String puedeVolar = scanner.nextLine().toUpperCase();
                    Ave ave = new Ave(id, nombre, especie, habitat, puedeVolar);
                    aveController.edit(ave);
                    break;
                    
                case "Reptil":
                    System.out.print("¿Es venenoso? (SI/NO): ");
                    String esVenenoso = scanner.nextLine().toUpperCase();
                    Reptil reptil = new Reptil(id, nombre, especie, habitat, esVenenoso);
                    reptilController.edit(reptil);
                    break;
            }
            
            System.out.println("✓ " + tipoAnimal + " actualizado exitosamente!");
            
        } catch (NonexistentEntityException e) {
            System.out.println("✗ Error: El " + tipoAnimal.toLowerCase() + " ya no existe.");
        } catch (Exception e) {
            System.out.println("✗ Error al actualizar " + tipoAnimal.toLowerCase() + ": " + e.getMessage());
        }
    }
    
    // MÉTODOS PARA ELIMINAR
    public static void eliminarAnimal(String tipoAnimal) {
        try {
            System.out.println("\n=== ELIMINAR " + tipoAnimal.toUpperCase() + " ===");
            
            System.out.print("Ingrese el ID del " + tipoAnimal.toLowerCase() + " a eliminar: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer
            
            Object animal = null;
            switch (tipoAnimal) {
                case "Mamifero":
                    animal = mamiferoController.findMamifero(id);
                    break;
                case "Ave":
                    animal = aveController.findAve(id);
                    break;
                case "Reptil":
                    animal = reptilController.findReptil(id);
                    break;
            }
            
            if (animal == null) {
                System.out.println("✗ No se encontró un " + tipoAnimal.toLowerCase() + " con ID: " + id);
                return;
            }
            
            System.out.println("Datos del " + tipoAnimal.toLowerCase() + " a eliminar:");
            System.out.println(animal.toString());
            
            System.out.print("¿Esta seguro de eliminar este registro? (S/N): ");
            String confirmacion = scanner.nextLine().toUpperCase();
            
            if (confirmacion.equals("S") || confirmacion.equals("SI")) {
                switch (tipoAnimal) {
                    case "Mamifero":
                        mamiferoController.destroy(id);
                        break;
                    case "Ave":
                        aveController.destroy(id);
                        break;
                    case "Reptil":
                        reptilController.destroy(id);
                        break;
                }
                System.out.println("✓ " + tipoAnimal + " eliminado exitosamente!");
            } else {
                System.out.println("Operacion cancelada.");
            }
            
        } catch (NonexistentEntityException e) {
            System.out.println("✗ Error: El " + tipoAnimal.toLowerCase() + " ya no existe.");
        } catch (Exception e) {
            System.out.println("✗ Error al eliminar " + tipoAnimal.toLowerCase() + ": " + e.getMessage());
        }
    }
}