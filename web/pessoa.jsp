<%-- 
    Document   : pessoal
    Created on : 14 de set. de 2023, 06:08:28
    Author     : gabri
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.PessoaDAO"%>
<%@page import="entidade.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CADASTRO PESSOA</title>
    </head>
    
    <%@include file="menu.jsp" %>
    
    <%
        Pessoa p = (Pessoa) request.getAttribute("pessoa");

        if (p == null) {
            p = new Pessoa();
        }
    %>
    
    <body>
        <h1>Cadastro De Pessoas</h1>
        
        <form method="post" action="acao?a=salvarPessoa">
            <label for="id">ID</label><br>
            <input type="text" id="id" name="id" readonly="" value="<%= p.getId() %>"><br>
            
            <label for="nome">Nome:</label><br>
            <input type="text" id="nome" name="nome" value="<%= p.getNome() %>"><br>

            <label for="email">Email:</label><br>
            <input type="text" id="email" name="email" value="<%= p.getEmail() %>"><br>

            <label for="telefone">Telefone:</label><br>
            <input type="text" id="telefone" name="telefone" value="<%= p.getTelefone() %>"><br>
            
            <label for="data_nascimento">Data de nascimento:</label><br>
            <input type="text" id="data_nascimento" name="data_nascimento" value="<%= p.getData_nascimento() %>"><br>
            
            
            <br> <br>
            <input type="submit" name="salvar" value="Salvar">
            <input type="Reset" name="reset" value="Limpar">
        </form>
        
        <h3>Listagem das categorias</h3>

        <%            ArrayList<Pessoa> pess = new PessoaDAO().consultar();
        %>

        <table class="table">
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Data de nascimento</th>
            
                <%
                    for (int i = 0; i < pess.size(); i++) {
                %>
            <tr>
                <td><%= pess.get(i).getId()%></td>
                <td><%= pess.get(i).getNome() %></td>
                <td><%= pess.get(i).getEmail() %></td>
                <td><%= pess.get(i).getTelefone() %></td>
                <td><%= pess.get(i).getData_nascimento() %></td>      
                <td><a href="acao?a=editarPessoa&id=<%= pess.get(i).getId()%>" class="btn btn-success">Editar</a></td>
                <td><a href="acao?a=excluirPessoa&id=<%= pess.get(i).getId()%>" class="btn btn-danger">Excluir</a></td>
            </tr>
            <%
                }
            %>

        </table>
    </body>
</html>
