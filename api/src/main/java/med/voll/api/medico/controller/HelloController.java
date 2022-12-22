package med.voll.api.medico.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Profile("prod")
public class HelloController {

    @GetMapping
    public String ola() {
        return "Escolha ingles ou portugues!";
    }

    @GetMapping
    @RequestMapping("/ingles")
    public String olaMundo() {
        return "Hello World!";
    }

    @GetMapping
    @RequestMapping("/portugues")
    public String olaWorld() {
        return "Olá Mundo!";
    }
}
