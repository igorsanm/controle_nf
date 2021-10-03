/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Usuario;


/**
 *
 * @author Igor Santana
 */
public class UsuarioDAO {
    
    private Connection con = null;

    public UsuarioDAO() {
        con = ConnectionFactory.getConnection();
    }
    
        public boolean checkLogin(String login, String senha){

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        
        try {
            stmt = con.prepareStatement("select * from usuario where login=? and senha=?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                
                check = true;
             
            }
            
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar usuario");
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }
    
        
public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario (login, senha) VALUES (?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

    }

public List<Usuario> read() {
        String sql = "SELECT * FROM usuario";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                
                usuarios.add(usuario);

            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);

        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return usuarios;
    }

 public void update(Usuario usuario) {
        String sql = "UPDATE usuario SET login = ?, senha = ? WHERE login = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

    }
 
     public void delete(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE login = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso.");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

    }
    
}
