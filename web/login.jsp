<%-- 
    Document   : cadastro
    Created on : 18 de set. de 2023, 21:25:58
    Author     : gabri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tela de login</title>
        <style>
            body{
                text-align: center;
            }
        </style>
        
    </head>
    <body>
        <h1>Tela de login</h1>
        
        <form method="post" action="acao?a=entrar">
            <label for="nome">Nome</label><br>
            <input type="text" id="nome" name="nome"><br>
            
            <label for="senha">Senha</label><br>
            <input type="password" id="senha" name="senha"><br><br>
            
            <input type="submit" name="entrar" value="Entrar">
        </form>
    </body>
</html>
