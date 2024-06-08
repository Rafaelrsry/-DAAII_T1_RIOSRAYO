package pe.edu.cibertec.DAAII_T1_RIOS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping({"/inicio","/","/home","/index"})
    public String listadoTotal(Model model) {


        return "auth/home";
    }
}