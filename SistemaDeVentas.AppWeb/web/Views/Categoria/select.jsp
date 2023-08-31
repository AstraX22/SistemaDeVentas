<%-- 
    Document   : select
    Created on : 21 ago 2023, 10:49:25
    Author     : gerardo
--%>

<% ArrayList<Rol> roles = RolDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slRol" name="idRol">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Categoria categoria : categorias) {%>
    <option <%=(id == rol.getId()) ? "selected" : ""%>  value="<%=categoria.getId()%>"><%= categoria.getNombre()%></option>
    <%}%>
</select>
<label for="idCategoria">Rol</label>