package com.yubo.practicasimilar_exameneva2.dto;




public record UsuarioDto (

        String username,
        String token
){
    @Override
    public String username() {
        return username;
    }

    @Override
    public String token() {
        return token;
    }


}





