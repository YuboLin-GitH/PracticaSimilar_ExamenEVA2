package com.yubo.practicasimilar_exameneva2.repository;



import com.yubo.practicasimilar_exameneva2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query(value = "SELECT * FROM usuarios WHERE username = :username AND password = SHA2(:password, 256)", nativeQuery = true)
    Usuario findByCredentials(@Param("username") String username, @Param("password") String password);
}
