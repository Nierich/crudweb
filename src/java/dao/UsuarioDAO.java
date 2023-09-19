/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import entidade.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author gabri
 */
public class UsuarioDAO {
    
    public boolean autenticar(Usuario u) {

        try {
            String sql
                    = "SELECT nome, senha "
                    + "FROM usuario "
                    + "WHERE "
                    + "nome = '" + u.getNome() + "' "
                    + "AND senha = md5('" + u.getSenha() + "') ";

            System.out.println("SQL: " + sql);

            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultadoQ.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e);
            return false;
        }
    }
    
}
