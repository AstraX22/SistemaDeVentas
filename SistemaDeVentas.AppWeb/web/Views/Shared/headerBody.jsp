<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sistemadeventas.appweb.utils.*"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>

<nav>
    <div class="nav-wrapper blue">
        <a href="Home" class="brand-logo">Sistema de Ventas</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down">  
            <% if (SessionUser.isAuth(request)) {  %>
            <li><a href="Home">Inicio</a></li>
            <li><a href="Categoria">Categorias</a></li>
            <li><a href="Cliente">Clientes</a></li>
            <li><a href="Producto">Productoss</a></li>
            <li><a href="Usuario">Usuarios</a></li>
            <li><a href="Rol">Roles</a></li>
            <li><a href="Usuario?accion=cambiarpass">Cambiar password</a></li>
            <li><a href="Usuario?accion=login">Cerrar sesión</a></li>
            <%}%>
        </ul>
    </div>
</nav>

<ul class="sidenav" id="mobile-demo">
     <% if (SessionUser.isAuth(request)) {  %>
     <li><a href="Home">Inicio</a></li>
     <li><a href="Categoria">Categorias</a></li>
     <li><a href="Cliente">Clientes</a></li>
     <li><a href="Producto">Productoss</a></li>
     <li><a href="Usuario">Usuarios</a></li>
      <li><a href="Rol">Roles</a></li>
     <li><a href="Usuario?accion=cambiarpass">Cambiar password</a></li>
     <li><a href="Usuario?accion=login">Cerrar sesión</a></li>
     <%}%>
</ul>
