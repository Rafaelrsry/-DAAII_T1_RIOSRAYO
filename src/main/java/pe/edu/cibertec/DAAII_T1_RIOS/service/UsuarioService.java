package pe.edu.cibertec.DAAII_T1_RIOS.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAAII_T1_RIOS.model.db.Rol;
import pe.edu.cibertec.DAAII_T1_RIOS.model.db.Usuario;
import pe.edu.cibertec.DAAII_T1_RIOS.repository.RolRepository;
import pe.edu.cibertec.DAAII_T1_RIOS.repository.UsuarioRepository;
import pe.edu.cibertec.DAAII_T1_RIOS.util.RandomPassword;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private RolRepository rolRepository;
    private RandomPassword randomPassword;

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public void CambiarPassword(String username, String newPassword) {
        Usuario usuario = usuarioRepository.findByNomusuario(username);
        if (usuario != null) {
            if (!validarPassword(newPassword)) {
                throw new IllegalArgumentException("La contraseña no cumple con los requisitos.");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordCifrada = passwordEncoder.encode(newPassword);
            usuario.setPassword(passwordCifrada);
            usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado: " + username);
        }
    }


    private boolean validarPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }
    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario buscarUsuarioXIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }
}

