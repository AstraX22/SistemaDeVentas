
package sistemadeventas.accesoadatos;
import java.util.*;
import java.sql.*;
import sistemasdeventa.entiidadesdenegicios.Usuario;

public class UsuarioDAL {
    static String obtenerCampos() {
    return "u.Id, u.Nombre";
}

private static String obtenerSelect(Usuario pUsuario) {
    String sql;
    sql = "SELECT ";
    if (pUsuario.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
        sql += "TOP " + pUsuario.getTop_aux() + " ";
    }
    sql += (obtenerCampos() + " FROM Usuario u");
    return sql;
}

private static String agregarOrderBy(Usuario pUsuario) {
    String sql = " ORDER BY u.Id DESC";
    if (pUsuario.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
        sql += " LIMIT " + pUsuario.getTop_aux() + " ";
    }
    return sql;
}

public static int crear(Usuario pUsuario) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) {
        sql = "INSERT INTO Usuario(Nombre) VALUES(?)";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setString(1, pUsuario.getNombre());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    } catch (SQLException ex) {
        throw ex;
    }
    return result;
}

public static int modificar(Usuario pUsuario) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) {
        sql = "UPDATE Usuario SET Nombre=? WHERE Id=?";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setString(1, pUsuario.getNombre());
            ps.setInt(2, pUsuario.getId());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    } catch (SQLException ex) {
        throw ex;
    }
    return result;
}

public static int eliminar(Usuario pUsuario) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) {
        sql = "DELETE FROM Usuario WHERE Id=?";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setInt(1, pUsuario.getId());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    } catch (SQLException ex) {
        throw ex;
    }
    return result;
}

static int asignarDatosResultSet(Usuario pUsuario, ResultSet pResultSet, int pIndex) throws Exception {
    pIndex++;
    pUsuario.setId(pResultSet.getInt(pIndex));
    pIndex++;
    pUsuario.setNombre(pResultSet.getString(pIndex));
    return pIndex;
}

private static void obtenerDatos(PreparedStatement pPS, ArrayList<Usuario> pUsuarios) throws Exception {
    try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            asignarDatosResultSet(usuario, resultSet, 0);
            pUsuarios.add(usuario);
        }
        resultSet.close();
    } catch (SQLException ex) {
        throw ex;
    }
}

public static Usuario obtenerPorId(Usuario pUsuario) throws Exception {
    Usuario usuario = new Usuario();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = obtenerSelect(pUsuario);
        sql += " WHERE u.Id=?";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setInt(1, pUsuario.getId());
            obtenerDatos(ps, usuarios);
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    }
    catch (SQLException ex) {
        throw ex;
    }

    if (!usuarios.isEmpty()) {
        usuario = usuarios.get(0);
    }

    return usuario;
}

public static ArrayList<Usuario> obtenerTodos() throws Exception {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = obtenerSelect(new Usuario());
        sql += agregarOrderBy(new Usuario());
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            obtenerDatos(ps, usuarios);
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    } 
    catch (SQLException ex) {
        throw ex;
    }

    return usuarios;
}
}
