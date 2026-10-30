package ucb.tecweb.endpoints;

import java.util.List;

import tools.jackson.databind.json.JsonMapper;
import ucb.tecweb.model.producto.ProductoDTO;

public class ProductoJsonPresenter {
    public String index(List<ProductoDTO> catalogo) {
        return JsonMapper.builder().build().writeValueAsString(catalogo);
    }

}
