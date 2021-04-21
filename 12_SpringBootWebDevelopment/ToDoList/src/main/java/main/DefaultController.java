package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DefaultController {
    @RequestMapping("/")
    public String index(){
        int ran = (int)(Math.random()*50);
        return ("Случайное число " + ran + " и дата " + new Date());
    }
}
