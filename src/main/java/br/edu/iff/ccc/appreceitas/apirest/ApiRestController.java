package br.edu.iff.ccc.appreceitas.apirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ApiRestController {

    @GetMapping("/api/v1")
    public Map<String, String> apiStatus() {
        // Retorna um JSON simples: {"status":"Em construção"}
        return Map.of("status", "Em construção");
    }
}
