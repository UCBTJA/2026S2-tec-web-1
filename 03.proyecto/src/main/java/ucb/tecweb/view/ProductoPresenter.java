package ucb.tecweb.view;

import java.util.List;

import ucb.tecweb.model.producto.ProductoDTO;

public class ProductoPresenter {
    private String buildMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        sb.append("<li><a href='/'>Inicio</a></li>");
        sb.append("<li><a href='/create.html'>Crear</a></li>");
        sb.append("<li><a href='/json'>JSON</a></li>");
        sb.append("</ul>");
        return sb.toString();
    }

    public String index(List<ProductoDTO> catalogo) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB' crossorigin='anonymous'>")
                .append("<head>")
                .append("<body><h1>Productos</h1><ul>");
        sb.append(buildMenu());
        sb.append("<table class='table table-striped table-bordered'><tr><th>ID</th><th>Nombre</th></tr>");
        for (ProductoDTO producto : catalogo) {
            sb.append("<tr><td>")
                    .append(producto.id())
                    .append("</td><td>")
                    .append(producto.nombre())
                    .append("</td></tr>");
        }
        sb.append("</table>");
        sb.append("</body></html>");
        return sb.toString();
    }
}
