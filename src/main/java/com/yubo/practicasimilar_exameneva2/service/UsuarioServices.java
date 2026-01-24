package com.yubo.practicasimilar_exameneva2.service;

import com.yubo.practicasimilar_exameneva2.dto.UsuarioDto;
import com.yubo.practicasimilar_exameneva2.model.Usuario;
import com.yubo.practicasimilar_exameneva2.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto login(String username, String password) {
        Usuario user = usuarioRepository.findByCredentials(username, password);
        if (user != null) {
            return new UsuarioDto(user.getUsername(), null);
        }
        return null;
    }


}
