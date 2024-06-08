package pe.edu.cibertec.DAAII_T1_RIOS.service;

import pe.edu.cibertec.DAAII_T1_RIOS.model.db.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);

    void CambiarPassword(String username, String newPassword);

    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXIdUsuario(Integer idusuario);
}
