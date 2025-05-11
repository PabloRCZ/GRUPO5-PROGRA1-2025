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
import java.util.Scanner;    // Para capturar entrada del usuario

public class Proyectozoologico {
    // Array de animales (en lugar de ArrayList)
    private static Animal[] animales = new Animal[10];
    private static int contadorAnimales = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            try {
                System.out.println("\n===== SISTEMA DE GESTION ZOOLOGICO LA AURORA =====");
                System.out.println("1. Zoo");
                System.out.println("2. Fase II - Arreglos");
                System.out.println("3. Fase III");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opcion: ");
                
                int opcionPrincipal = Integer.parseInt(scanner.nextLine());
                
                switch (opcionPrincipal) {
                    case 1:
                        menuZoo(scanner);
                        break;
                    case 2:
                        menuFase2(scanner);
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
    
    // Método para agregar un animal al array
    private static void agregarAnimal(Animal animal) {
        if (contadorAnimales < animales.length) {
            animales[contadorAnimales] = animal;
            contadorAnimales++;
        } else {
            System.out.println("El array ya está lleno");
        }
    }
    
    // Método para verificar si ya existe un id de animal
    private static boolean existeIdAnimal(int id) {
        for (int i = 0; i < contadorAnimales; i++) {
            if (animales[i].getIdAnimal() == id) {
                return true;
            }
        }
        return false;
    }
    
    // Método para guardar los datos en un archivo CSV
    private static void guardarCSV(String nombreArchivo) throws IOException {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            // Escribir encabezado
            escritor.write("Tipo,ID,Nombre,Edad,Peso,Alimento_Diario,Caracteristica\n");
            
            // Escribir datos de cada animal
            for (int i = 0; i < contadorAnimales; i++) {
                escritor.write(animales[i].toCSV() + "\n");
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
    
    private static void menuFase2(Scanner scanner) {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n===== FASE II - ARREGLOS =====");
            System.out.println("a: Agregar Mamífero");
            System.out.println("b: Agregar Ave");
            System.out.println("c: Agregar Reptil");
            System.out.println("d: Ordenar Arreglo");
            System.out.println("e: Mostrar Arreglo");
            System.out.println("f: Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            
            String opcion = scanner.nextLine().toLowerCase();
            
            switch (opcion) {
                case "a":
                    agregarMamifero(scanner);
                    break;
                case "b":
                    agregarAve(scanner);
                    break;
                case "c":
                    agregarReptil(scanner);
                    break;
                case "d":
                    ordenarArreglo(scanner);
                    break;
                case "e":
                    mostrarArreglo();
                    break;
                case "f":
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor intente de nuevo.");
            }
        }
    }
    
    private static void agregarMamifero(Scanner scanner) {
        // Verificar si el array está lleno
        if (contadorAnimales >= animales.length) {
            System.out.println("El array ya está lleno");
            return;
        }
        
        try {
            // Solicitar ID
            int idAnimal = solicitarIdUnico(scanner);
            
            // Solicitar datos comunes
            System.out.print("Nombre del mamifero: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Edad (anos): ");
            int edad = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Peso (kg): ");
            double peso = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Alimento diario (libras): ");
            double alimentoDiario = Double.parseDouble(scanner.nextLine());
            
            // Datos específicos del mamífero
            System.out.print("Habitat: ");
            String habitat = scanner.nextLine();
            
            // Crear y agregar el mamífero
            Mamifero mamifero = new Mamifero(idAnimal, nombre, edad, peso, alimentoDiario, habitat);
            agregarAnimal(mamifero);
            
            System.out.println("Mamifero agregado con exito.");
            
            // Preguntar si desea agregar otro animal
            preguntarSiAgregarOtro(scanner);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numerico valido.");
        } catch (Exception e) {
            System.out.println("Error al agregar el mamifero: " + e.getMessage());
        }
    }
    
    private static void agregarAve(Scanner scanner) {
        // Verificar si el array está lleno
        if (contadorAnimales >= animales.length) {
            System.out.println("El array ya está lleno");
            return;
        }
        
        try {
            // Solicitar ID
            int idAnimal = solicitarIdUnico(scanner);
            
            // Solicitar datos comunes
            System.out.print("Nombre del ave: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Edad (anos): ");
            int edad = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Peso (kg): ");
            double peso = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Alimento diario (libras): ");
            double alimentoDiario = Double.parseDouble(scanner.nextLine());
            
            // Datos específicos del ave
            System.out.print("¿Puede volar? (s/n): ");
            boolean vuela = scanner.nextLine().toLowerCase().startsWith("s");
            
            // Crear y agregar el ave
            Ave ave = new Ave(idAnimal, nombre, edad, peso, alimentoDiario, vuela);
            agregarAnimal(ave);
            
            System.out.println("Ave agregada con exito.");
            
            // Preguntar si desea agregar otro animal
            preguntarSiAgregarOtro(scanner);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numerico valido.");
        } catch (Exception e) {
            System.out.println("Error al agregar el ave: " + e.getMessage());
        }
    }
    
    private static void agregarReptil(Scanner scanner) {
        // Verificar si el array está lleno
        if (contadorAnimales >= animales.length) {
            System.out.println("El array ya está lleno");
            return;
        }
        
        try {
            // Solicitar ID
            int idAnimal = solicitarIdUnico(scanner);
            
            // Solicitar datos comunes
            System.out.print("Nombre del reptil: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Edad (anos): ");
            int edad = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Peso (kg): ");
            double peso = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Alimento diario (libras): ");
            double alimentoDiario = Double.parseDouble(scanner.nextLine());
            
            // Datos específicos del reptil
            System.out.print("¿Es venenoso? (s/n): ");
            boolean venenoso = scanner.nextLine().toLowerCase().startsWith("s");
            
            // Crear y agregar el reptil
            Reptil reptil = new Reptil(idAnimal, nombre, edad, peso, alimentoDiario, venenoso);
            agregarAnimal(reptil);
            
            System.out.println("Reptil agregado con exito.");
            
            // Preguntar si desea agregar otro animal
            preguntarSiAgregarOtro(scanner);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numerico valido.");
        } catch (Exception e) {
            System.out.println("Error al agregar el reptil: " + e.getMessage());
        }
    }
    
    private static int solicitarIdUnico(Scanner scanner) {
        int id;
        boolean idValido = false;
        
        do {
            System.out.print("ID del animal: ");
            id = Integer.parseInt(scanner.nextLine());
            
            if (existeIdAnimal(id)) {
                System.out.println("El identificador es único para cada animal");
            } else {
                idValido = true;
            }
        } while (!idValido);
        
        return id;
    }
    
    private static void preguntarSiAgregarOtro(Scanner scanner) {
        System.out.print("¿Desea agregar otro animal? (si/no): ");
        String respuesta = scanner.nextLine().toLowerCase();
        
        if (!respuesta.equals("si")) {
            // Si no quiere agregar otro, volvemos al menú principal
            // No hay que hacer nada aquí, el menú de la Fase II se encargará
        }
    }
    
    private static void ordenarArreglo(Scanner scanner) {
        if (contadorAnimales <= 1) {
            System.out.println("No hay suficientes animales para ordenar.");
            return;
        }
        
        System.out.print("¿Desea ordenar de forma ascendente (a) o descendente (d)? ");
        String opcion = scanner.nextLine().toLowerCase();
        
        if (opcion.equals("a")) {
            ordenarAscendente();
            System.out.println("Arreglo ordenado de forma ascendente por ID.");
        } else if (opcion.equals("d")) {
            ordenarDescendente();
            System.out.println("Arreglo ordenado de forma descendente por ID.");
        } else {
            System.out.println("Opcion no valida.");
        }
    }
    
    private static void ordenarAscendente() {
        for (int i = 0; i < contadorAnimales - 1; i++) {
            for (int j = 0; j < contadorAnimales - i - 1; j++) {
                if (animales[j].getIdAnimal() > animales[j + 1].getIdAnimal()) {
                    // Intercambiar elementos
                    Animal temp = animales[j];
                    animales[j] = animales[j + 1];
                    animales[j + 1] = temp;
                }
            }
        }
    }
    
    private static void ordenarDescendente() {
        for (int i = 0; i < contadorAnimales - 1; i++) {
            for (int j = 0; j < contadorAnimales - i - 1; j++) {
                if (animales[j].getIdAnimal() < animales[j + 1].getIdAnimal()) {
                    // Intercambiar elementos
                    Animal temp = animales[j];
                    animales[j] = animales[j + 1];
                    animales[j + 1] = temp;
                }
            }
        }
    }
    
    private static void mostrarArreglo() {
        System.out.println("\n===== ARREGLO DE ANIMALES =====");
        
        if (contadorAnimales == 0) {
            System.out.println("No hay animales registrados.");
            return;
        }
        
        System.out.println("IDs de los animales:");
        for (int i = 0; i < contadorAnimales; i++) {
            System.out.println((i+1) + ". ID: " + animales[i].getIdAnimal());
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
            
            // Verificar si el array está lleno
            if (contadorAnimales >= animales.length) {
                System.out.println("El array ya está lleno");
                return;
            }
            
            // Solicitar ID
            int idAnimal = solicitarIdUnico(scanner);
            
            // Datos comunes para todos los animales
            System.out.print("Nombre del animal: ");
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
                    System.out.print("Habitat (terreno en el que vive): ");
                    String habitat = scanner.nextLine();
                    nuevoAnimal = new Mamifero(idAnimal, nombre, edad, peso, alimentoDiario, habitat);
                    break;
                    
                case 2: // Ave
                    System.out.print("¿Puede volar? (s/n): ");
                    boolean vuela = scanner.nextLine().toLowerCase().startsWith("s");
                    nuevoAnimal = new Ave(idAnimal, nombre, edad, peso, alimentoDiario, vuela);
                    break;
                    
                case 3: // Reptil
                    System.out.print("¿Es venenoso? (s/n): ");
                    boolean venenoso = scanner.nextLine().toLowerCase().startsWith("s");
                    nuevoAnimal = new Reptil(idAnimal, nombre, edad, peso, alimentoDiario, venenoso);
                    break;
                    
                default:
                    System.out.println("Tipo de animal no valido.");
                    return;
            }
            
            if (nuevoAnimal != null) {
                agregarAnimal(nuevoAnimal);
                System.out.println("Animal agregado con exito.");
                
                // Calcular consumo de alimento
                System.out.print("Calcular alimento para cuantos dias? ");
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
        
        if (contadorAnimales == 0) {
            System.out.println("No hay animales registrados en el zoologico.");
            return;
        }
        
        for (int i = 0; i < contadorAnimales; i++) {
            System.out.println(animales[i]);
        }
    }
    
    private static void exportarDatos(Scanner scanner) {
        if (contadorAnimales == 0) {
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