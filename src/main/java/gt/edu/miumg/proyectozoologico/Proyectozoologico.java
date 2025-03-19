/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gt.edu.miumg.proyectozoologico;

/**
 *
 * @author pabli
 */
//librerias usadas esto para funcionamiento de estas
import java.io.FileWriter;  // Para escribir en archivos
import java.io.IOException;  // Para manejar excepciones de operaciones I/O
import java.util.ArrayList;  // Para crear y manejar listas dinámicas
import java.util.Scanner;    // Para capturar entrada del usuario

public class Proyectozoologico {
    // Lista de animales 
    private static ArrayList<Animal> animales = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            try {
                System.out.println("\n===== SISTEMA DE GESTION ZOOLOGICO LA AURORA =====");
                System.out.println("1. Zoo");
                System.out.println("2. Fase II");
                System.out.println("3. Fase III");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opcion: ");
                
                int opcionPrincipal = Integer.parseInt(scanner.nextLine());
                
                switch (opcionPrincipal) {
                    case 1:
                        menuZoo(scanner);
                        break;
                    case 2:
                        System.out.println("Funcionalidad Fase II no implementada aun.");
                        break;
                    case 3:
                        System.out.println("Funcionalidad Fase III no implementada aun.");
                        break;
                    case 4:
                        salir = true;
                        System.out.println("\n Gracias por usar el Sistema de Gestion del Zoologico La Aurora ");
                        System.out.println("Que tenga un excelente dia");
                        break;
                    default:
                        System.out.println("Opcion no valida. Por favor intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero. Intente de nuevo.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    // Método para agregar un animal a la lista
    private static void agregarAnimal(Animal animal) {
        animales.add(animal);
    }
    
    // Método para verificar si ya existe un tipo de animal
    private static boolean tieneTipoAnimal(Class<?> tipo) {
        for (Animal animal : animales) {
            if (tipo.isInstance(animal)) {
                return true;
            }
        }
        return false;
    }
    
    // Método para guardar los datos en un archivo CSV
    private static void guardarCSV(String nombreArchivo) throws IOException {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            // Escribir encabezado
            escritor.write("Tipo,Nombre,Edad,Peso,Alimento_Diario,Caracteristica\n");
            
            // Escribir datos de cada animal
            for (Animal animal : animales) {
                escritor.write(animal.toCSV() + "\n");
            }
            
            System.out.println("Datos guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
            throw e;
        }
    }
    
    private static void menuZoo(Scanner scanner) {
        boolean volver = false;
        
        while (!volver) {
            try {
                System.out.println("\n===== MENU ZOO =====");
                System.out.println("1. Agregar nuevo animal");
                System.out.println("2. Ver todos los animales del Zoo");
                System.out.println("3. Exportar datos a CSV");
                System.out.println("4. Volver al menu principal");
                System.out.print("Seleccione una opcion: ");
                
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        agregarAnimalMenu(scanner);
                        break;
                    case 2:
                        mostrarAnimales();
                        break;
                    case 3:
                        exportarDatos(scanner);
                        break;
                    case 4:
                        volver = true;
                        break;
                    default:
                        System.out.println("Opcion no valida. Por favor intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero. Intente de nuevo.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
    
    private static void agregarAnimalMenu(Scanner scanner) {
        System.out.println("\n===== AGREGAR ANIMAL =====");
        System.out.println("Seleccione el tipo de animal:");
        System.out.println("1. Mamifero");
        System.out.println("2. Ave");
        System.out.println("3. Reptil");
        System.out.print("Opcion: ");
        
        try {
            int tipo = Integer.parseInt(scanner.nextLine());
            
            // Verificar si ya existe un animal de este tipo
            boolean yaExiste = false;
            switch (tipo) {
                case 1:
                    yaExiste = tieneTipoAnimal(Mamifero.class);
                    break;
                case 2:
                    yaExiste = tieneTipoAnimal(Ave.class);
                    break;
                case 3:
                    yaExiste = tieneTipoAnimal(Reptil.class);
                    break;
                default:
                    System.out.println("Tipo de animal no valido.");
                    return;
            }
            
            if (yaExiste) {
                System.out.println("Ya existe un animal de este tipo en el zoologico.");
                return;
            }
            
            // Datos comunes para todos los animales
            System.out.print("Nombre del animal : ");
            String nombre = scanner.nextLine();
            
            System.out.print("Edad (anos): ");
            int edad = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Peso (kg): ");
            double peso = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Alimento diario para el animal (libras): ");
            double alimentoDiario = Double.parseDouble(scanner.nextLine());
            
            Animal nuevoAnimal = null;
            
            // Datos específicos segun el tipo
            switch (tipo) {
                case 1: // Mamifero
                    System.out.print("Habitat (terreno en el que vive ): ");
                    String habitat = scanner.nextLine();
                    nuevoAnimal = new Mamifero(nombre, edad, peso, alimentoDiario, habitat);
                    break;
                    
                case 2: // Ave
                    System.out.print("¿Puede volar? (s/n): ");
                    boolean vuela = scanner.nextLine().toLowerCase().startsWith("s");
                    nuevoAnimal = new Ave(nombre, edad, peso, alimentoDiario, vuela);
                    break;
                    
                case 3: // Reptil
                    System.out.print("¿Es venenoso? (s/n): ");
                    boolean venenoso = scanner.nextLine().toLowerCase().startsWith("s");
                    nuevoAnimal = new Reptil(nombre, edad, peso, alimentoDiario, venenoso);
                    break;
            }
            
            if (nuevoAnimal != null) {
                agregarAnimal(nuevoAnimal);
                System.out.println("Animal agregado con exito.");
                
                // Calcular consumo de alimento
                System.out.print(" Calcular alimento para cuantos dias? ");
                int dias = Integer.parseInt(scanner.nextLine());
                double totalAlimento = nuevoAnimal.calcularAlimento(dias);
                System.out.println("El animal consumira " + totalAlimento + " libras de " + 
                                  nuevoAnimal.getTipoAlimento() + " en " + dias + " dias.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numerico valido.");
        } catch (Exception e) {
            System.out.println("Error al agregar el animal: " + e.getMessage());
        }
    }
    
    private static void mostrarAnimales() {
        System.out.println("\n===== ANIMALES DEL ZOOLOGICO =====");
        
        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados en el zoologico.");
            return;
        }
        
        for (Animal animal : animales) {
            System.out.println(animal);
        }
    }
    
    private static void exportarDatos(Scanner scanner) {
        if (animales.isEmpty()) {
            System.out.println("No hay datos para exportar.");
            return;
        }
        
        System.out.print("Nombre del archivo (sin extensión): ");
        String nombreArchivo = scanner.nextLine();
        
        if (nombreArchivo.isEmpty()) {
            nombreArchivo = "animales_zoo";
        }
        
        try {
            guardarCSV(nombreArchivo + ".csv");
        } catch (IOException e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }
    }
}
