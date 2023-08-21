
package sistemadeventas.accesoadatos;
import java.util.*;
import java.sql.*;
import sistemasdeventa.entiidadesdenegicios.Cliente;

public class ClienteDAL {
    static String obtenerCampos() {
    return "c.Id, c.Nombre";
}

private static String obtenerSelect(Cliente pCliente) {
    String sql;
    sql = "SELECT ";
    if (pCliente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
        sql += "TOP " + pCliente.getTop_aux() + " ";
    }
    sql += (obtenerCampos() + " FROM Cliente c");
    return sql;
}

private static String agregarOrderBy(Cliente pCliente) {
    String sql = " ORDER BY c.Id DESC";
    if (pCliente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
        sql += " LIMIT " + pCliente.getTop_aux() + " ";
    }
    return sql;
}

public static int crear(Cliente pCliente) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) {
        sql = "INSERT INTO Cliente(Nombre) VALUES(?)";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setString(1, pCliente.getNombre());
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

public static int modificar(Cliente pCliente) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) {
        sql = "UPDATE Cliente SET Nombre=? WHERE Id=?";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setString(1, pCliente.getNombre());
            /*ps.setInt(2, pCliente.getId());*/
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

public static int eliminar(Cliente pCliente) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) {
        sql = "DELETE FROM Cliente WHERE Id=?";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setString(1, pCliente.getId());
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

static int asignarDatosResultSet(Cliente pCliente, ResultSet pResultSet, int pIndex) throws Exception {
    pIndex++;
    /*pCliente.setId(pResultSet.getInt(pIndex));*/
    pIndex++;
    pCliente.setNombre(pResultSet.getString(pIndex));
    return pIndex;
}

private static void obtenerDatos(PreparedStatement pPS, ArrayList<Cliente> pClientes) throws Exception {
    try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            asignarDatosResultSet(cliente, resultSet, 0);
            pClientes.add(cliente);
        }
        resultSet.close();
    } catch (SQLException ex) {
        throw ex;
    }
}

public static Cliente obtenerPorId(Cliente pCliente) throws Exception {
    Cliente cliente = new Cliente();
    ArrayList<Cliente> clientes = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = obtenerSelect(pCliente);
        sql += " WHERE c.Id=?";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setString(1, pCliente.getId());
            obtenerDatos(ps, clientes);
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    } catch (SQLException ex) {
        throw ex;
    }

    if (!clientes.isEmpty()) {
        cliente = clientes.get(0);
    }

    return cliente;
}

public static ArrayList<Cliente> obtenerTodos() throws Exception {
    ArrayList<Cliente> clientes = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = obtenerSelect(new Cliente());
        sql += agregarOrderBy(new Cliente());
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            obtenerDatos(ps, clientes);
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    } catch (SQLException ex) {
        throw ex;
    }

    return clientes;
}



}
