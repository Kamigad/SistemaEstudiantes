package gm.datos;


import gm.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static gm.conexion.Conexion.getConexion;

public class EstudianteDAO {
    public List<Estudiante> ListarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        }
        catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Ocurrio un error al cerrar la conexion " + e.getMessage());
            }
        }
        return estudiantes;
    }
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e ){
                System.out.println("Ocurrio un error al cerrar la conexion: " + e.getMessage());
            }

        }
        return false;

    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error al agregar estudiante " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }

        }
        return false;

    }
    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.setInt(5,estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("ocurrio un error al modificar estudiante " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;

    }
    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;

    }

    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();

//        //agregar estudiante
//        var estudiante = new Estudiante("Carlos", "Lara", "756789123456", "carlos@gmail.com");
//        var agregado = estudianteDao.agregarEstudiante(estudiante);
//        if(agregado)
//            System.out.println("Estudiante agregado: " + estudiante);
//        else
//            System.out.println("No se agrego el estudiante: " + estudiante);

        //modificamos un estudiante existente (1)
//        var estudianteModificar = new Estudiante(1, "Juan Carlos", "Juarez","1596983214", "juan@yahoo.com");
//        var modificado = estudianteDao.modificarEstudiante(estudianteModificar);
//        if (modificado)
//            System.out.println("estudiante modificado " + estudianteModificar);
//        else
//            System.out.println("No se modifico estudiante " + estudianteModificar);

        //Eliminar estudiante (3)
        var estudianteEliminar = new Estudiante(3);
        var eliminado = estudianteDao.eliminarEstudiante(estudianteEliminar);
        if (eliminado)
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        else
            System.out.println("NO se elimino el estudiante: " + estudianteEliminar);

//        // Listar los estudiantes
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.ListarEstudiantes();
        estudiantes.forEach(System.out::println);

        //Buscar por id
//        var estudiante1 = new Estudiante(3);
//        System.out.println("estudiante antes de la busqueda: " + estudiante1);
//        var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
//        if (encontrado){
//            System.out.println("estudiante encontrado: " + estudiante1);
//        }else
//            System.out.println("No se encotro estudiante: " + estudiante1.getIdEstudiante());
    }
}
