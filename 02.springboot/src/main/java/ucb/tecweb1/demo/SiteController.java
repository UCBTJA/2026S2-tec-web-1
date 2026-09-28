package ucb.tecweb1.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @GetMapping("/")
    public String index() {
        return "<h1>Bienvenido a mi sitio web!</h1>";
    }

    @GetMapping("/contact")
    public String contact() {
        return "<h1>Contáctanos</h1><p>Envíanos un correo a info@miempresa.com</p>";
    }

    @GetMapping("/datos")
    public String datos() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Datos</h1>");
        sb.append("<table>");
        sb.append("<tr><th>Nombre</th><th>Edad</th></tr>");
        sb.append("<tr><td>Juan</td><td>25</td></tr>");
        sb.append("<tr><td>María</td><td>30</td></tr>");
        sb.append("<tr><td>Carlos</td><td>28</td></tr>");
        sb.append("</table>");

        return sb.toString();
    }

    @PostMapping("/saludo")
    public String greeting(@RequestParam String name, @RequestParam int edad) {
        return "Hola " + name + ", tienes " + edad + " años";
    }
}
