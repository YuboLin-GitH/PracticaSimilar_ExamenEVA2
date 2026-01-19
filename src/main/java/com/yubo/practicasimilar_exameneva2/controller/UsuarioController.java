package com.yubo.practicasimilar_exameneva2.controller;

import com.yubo.practicasimilar_exameneva2.dto.UsuarioDto;
import com.yubo.practicasimilar_exameneva2.service.UsuarioServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Controlador de Usuarios", description = "Para la gestión de usuarios y autenticación JWT")
public class UsuarioController {
    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }



    @Operation(
            summary = "Login de usuario",
            description = "Autentica al usuario con nombre y contraseña, devolviendo un objeto User con su JWT Token."
    )
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa")
    @ApiResponse(responseCode = "406", description = "Credenciales incorrectas")

    @PostMapping("/login")

    //   http://localhost:9000/api/usuario/login
    // public User login(  @PathVariable String username,@PathVariable String pwd)
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {



        try{
            UsuarioDto dbUser = usuarioServices.login(username, password);

            if (dbUser != null ) {

                System.out.println("bien: " + username);


                String token = getJWTToken(username);


                dbUser.setToken(token);

                return new ResponseEntity<>(dbUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error de Contraseña", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de búsqueda", e);
        }


    }


    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
