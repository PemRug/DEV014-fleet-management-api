package fleetmanagementapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/")
    public Map<String, String> foo(){
        Map<String, String> json = new HashMap<>();
        json.put("message", "Hola mundo spring boot api rest");
        return json;
    }
}
