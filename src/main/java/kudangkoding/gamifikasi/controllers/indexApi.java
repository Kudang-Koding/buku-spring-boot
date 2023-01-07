package kudangkoding.gamifikasi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexApi {

    @GetMapping
    public String index(){
        return "Gamifikasi Service V1";
    }

}
