
package sistemadeventas.accesoadatos;
import java.util.*;
import java.sql.*;
import sistemasdeventa.entiidadesdenegicios.Cliente;
import sistemasdeventa.entiidadesdenegicios.Producto;

public class ProductoDAL {
   static String obtenerCampos() {
        return "p.Id, p.Nombre, p.Precio, p.IdCategoria, p.IdUsuario";
    }
    
    private static String obtenerSelect(Producto pProducto) {
        String sql;
        sql = "SELECT ";
        if (pProducto.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pProducto.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Cliente c");
        return sql;
    }
    
    private static String agregarOrderBy(Producto pProducto) {
        String sql = " ORDER BY c.Id DESC";
        if (pProducto.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pProducto.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "INSERT INTO Cliente(Nombre, Precio, IdCategoria, IdUsuario) VALUES(?, ?, ?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pProducto.getNombre());
                ps.setDouble(2,  pProducto.getPrecio());
                ps.setInt(3, pProducto.getIdCategoria());
                ps.setInt(4, pProducto.getIdUsuario());
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
    
    public static int modificar(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Cliente SET Nombre=?, Precio = ?, IdCategoria =?, IdUsuario =? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pProducto.getNombre());
                ps.setDouble(2,  pProducto.getPrecio());
                  ps.setInt(3, pProducto.getIdCategoria());
                ps.setInt(4, pProducto.getIdUsuario());
                ps.setInt(5, pProducto.getId());
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
    
    public static int eliminar(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Cliente WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pProducto.getId());
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
    
    static int asignarDatosResultSet(Producto pProducto, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pProducto.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pProducto.setNombre(pResultSet.getString(pIndex));
         pIndex++;
        pProducto.setPrecio(pResultSet.getDouble(pIndex));
        pIndex++;
         pIndex++;
        pProducto.setIdCategoria(pResultSet.getInt(pIndex));
        pProducto.setIdUsuario(pResultSet.getInt(pIndex));
        pIndex++;
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Producto> pProducto) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Producto producto = new Producto(); 
                asignarDatosResultSet(producto, resultSet, 0);
                pProducto.add(producto);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static Producto obtenerPorId(Producto pProducto) throws Exception {
        Producto producto = new Producto();
        ArrayList<Producto> productos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pProducto);
            sql += " WHERE c.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pProducto.getId());
                obtenerDatos(ps, productos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (productos.size() > 0) {
            producto = productos.get(0);
        }
        return producto;
    }
    
    public static ArrayList<Producto> obtenerTodos() throws Exception {
        ArrayList<Producto> productos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Producto());
            sql += agregarOrderBy(new Producto());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, productos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return productos;
    }
    
    static void querySelect(Producto pProducto, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pProducto.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" p.Id=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getId()); 
            }
        }

        if (pProducto.getNombre() != null && pProducto.getNombre().trim().isEmpty() == false) {
           pUtilQuery.AgregarNumWhere(" p.Nombre LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getNombre() + "%"); 
            }
        }
        if (pProducto.getPrecio() > 0) {
            pUtilQuery.AgregarNumWhere(" p.Precio=? ");
            if (statement != null) { 
                statement.setDouble(pUtilQuery.getNumWhere(), pProducto.getPrecio()); 
            }
        }
         if (pProducto.getIdCategoria() > 0) {
            pUtilQuery.AgregarNumWhere(" p.IdCategoria=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdCategoria()); 
            }
        }
           if (pProducto.getIdUsuario() > 0) {
            pUtilQuery.AgregarNumWhere(" p.IdUsuario=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdUsuario()); 
            }
        }
        
       
    }
    
    public static ArrayList<Producto> buscar(Producto pProducto) throws Exception {
        ArrayList<Producto> productos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pProducto);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0); 
            querySelect(pProducto, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pProducto);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pProducto, utilQuery);
                obtenerDatos(ps, productos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return productos;
    }
}

