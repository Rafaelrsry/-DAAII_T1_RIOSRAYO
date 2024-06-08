package pe.edu.cibertec.DAAII_T1_RIOS.model.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Integer idusuario;
    private String nomusuario;
    private String password;
    private String nombres;
    private String apellidos;
    private Boolean activo;
    private String email;
}
