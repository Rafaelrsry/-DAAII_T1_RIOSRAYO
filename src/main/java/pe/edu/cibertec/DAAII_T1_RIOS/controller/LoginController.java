package pe.edu.cibertec.DAAII_T1_RIOS.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.DAAII_T1_RIOS.model.db.Usuario;
import pe.edu.cibertec.DAAII_T1_RIOS.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/frmlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "auth/home";
    }


    @GetMapping("/")
    public String home(){
        return "auth/home";
    }



    @PostMapping("/guardar-usuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "/auth/frmlogin";
    }

    @GetMapping("/register")
    public String register(Model modelo){
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        return "auth/frmregistro";
    }

    @GetMapping("/cambiar-password")
    public String mostrarCambiarPassword(Model model) {
        return "auth/frmpassword";
    }

    @PostMapping("/cambiar-password")
    public String cambiarPassword(@RequestParam("newPassword") String newPassword,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        try {
            usuarioService.CambiarPassword(username, newPassword);
            redirectAttributes.addFlashAttribute("success", "Contraseña cambiada exitosamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/auth/cambiar-password";
    }


}