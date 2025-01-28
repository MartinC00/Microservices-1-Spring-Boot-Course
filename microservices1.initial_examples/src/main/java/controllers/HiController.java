package controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hi")
public class HiController {

    @GetMapping(value = "{name}/{lastname}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hiPathVarible(@PathVariable String name, @PathVariable String lastname){
        return "Path Variable. Hi "+ name + " " + lastname + ", how're you doing?";
        // http://localhost:8080/myApp/hi/Maria/Perez
    }

    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hiRequestParam(@RequestParam String name, @RequestParam int age){
        return "Request Param. Hi "+name+ ", you are " + age;
        // http://localhost:8080/myApp/hi?name=Juan&age=15
    }
}
