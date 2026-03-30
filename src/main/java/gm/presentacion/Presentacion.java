package gm.presentacion;

import gm.datos.EstudianteDAO;
import gm.dominio.Estudiante;

import java.util.Scanner;

public class Presentacion {
    public static void main(String[] args) {
        var salir = false;
        var leer = new Scanner(System.in);
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        while (!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(leer,estudianteDAO);
            } catch (Exception e){
                System.out.println("Ocurrio error al ejecutar el menu: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu(){
        System.out.print("""
                    \n*** Sistema de Estudiante ***
                    1.  Listar Estudiantes
                    2.  Buscar Estudiantes
                    3.  Agregar Estudiante
                    4.  Modificar Estudiante
                    5.  Eliminar Estudiante
                    6.  Salir
                    Elige una opcion: """ );
    }

    private static boolean ejecutarOpciones(Scanner leer, EstudianteDAO estudianteDAO){

        var opcion = Integer.parseInt(leer.nextLine());
        var salir = false;

        switch (opcion){
            case 1 -> {// listar estudiantes
                System.out.println("Listado de Estudiantes...");
                var estudiantes = estudianteDAO.ListarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {// buscar estudiante
                ;
                System.out.print("Introduce el ID del estudiante a buscar: ");
                var idBuscar = Integer.parseInt(leer.nextLine());
                Estudiante estudiante = new Estudiante(idBuscar);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (encontrado)
                    System.out.println("Estudiante ha sido encontrado: " + estudiante);
                else
                    System.out.println("Estudiante no ha sido encontrado: " + estudiante);
            }
            case 3 -> {// agregar estudiante
                System.out.print("Ingresa el nombre del estudiante: ");
                var nombre = leer.nextLine();
                System.out.print("Ingresa el apellido del estudiante: ");
                var apellido = leer.nextLine();
                System.out.print("Ingresa el telefono del estudiante: ");
                var telefono = leer.nextLine();
                System.out.print("Ingresa el email del estudiante: ");
                var email = leer.nextLine();
                Estudiante estudiante = new Estudiante(nombre,apellido,telefono,email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Estudiante ha sido agregado al sistema: " + estudiante);
                else
                    System.out.println("Estudiante no ha sido agregado al sistema: " + estudiante);
            }
            case 4 -> {//modificar estudiante
                System.out.print("Introduce el ID del estudiante a modificar: ");
                var idBuscar = Integer.parseInt(leer.nextLine());
                System.out.print("Ingresa el nuevo nombre del estudiante: ");
                var nombre = leer.nextLine();
                System.out.print("Ingresa el nuevo apellido del estudiante: ");
                var apellido = leer.nextLine();
                System.out.print("Ingresa el nuevo telefono del estudiante: ");
                var telefono = leer.nextLine();
                System.out.print("Ingresa el nuevo email del estudiante: ");
                var email = leer.nextLine();
                Estudiante estudiante = new Estudiante(idBuscar,nombre,apellido,telefono,email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                    if (modificado)
                        System.out.println("El estudiante ha sido modifcado: " + estudiante);
                    else
                        System.out.println("El estudiante no ha sido modificado: " + estudiante);
            }
            case 5 -> {//eliminar estudiante
                System.out.print("Ingresa el ID del estudiante a eliminar: ");
                var idEliminar =Integer.parseInt(leer.nextLine());
                Estudiante estudiante = new Estudiante(idEliminar);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if (eliminado)
                    System.out.println("El estudiante ha sido eliminado: " + estudiante);
                else
                    System.out.println("El estudiante no ha sido eliminado: " + estudiante);
            }
            case 6 -> {//salir
                System.out.println("Hasta Pronto!");
                salir = true;
            }
            default -> System.out.println("La opcion elegida no existe: " + opcion);
        }
        return salir;
    }
}
