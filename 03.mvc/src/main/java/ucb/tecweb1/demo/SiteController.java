package ucb.tecweb1.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    public int operacion(int a, int b, Operacion ope) {
        return ope.run(a, b);
    }

    @GetMapping("/")
    public String index() {
        int res = operacion(5, 3, new Multiplicador());
        return "El resultado es: " + res;
    }

    @PostMapping("/saludo")
    public String greeting(@RequestParam String name, @RequestParam int edad) {
        return "Hola " + name + ", tienes " + edad + " años";
    }
}
