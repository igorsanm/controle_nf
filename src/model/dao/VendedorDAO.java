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
import model.bean.Vendedor;

/**
 *
 * @author Igor Santana
 */
public class VendedorDAO {

    private Connection con = null;

    public VendedorDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void create(Vendedor v) { //CADASTRAR VENDEDOR

        PreparedStatement stmt = null;
        String sql = "insert into vendedor(nome) values(?)";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, v.getNome());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);

        } finally {
            ConnectionFactory.closeConnection(con);

        }

    }

    public List<Vendedor> read() { //SELECIONAR OS VENDEDORES DO BD

        String sql = "SELECT * FROM VENDEDOR";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Vendedor> vendedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId_vendedor(rs.getInt("id_vendedor"));
                vendedor.setNome(rs.getString("nome"));

                vendedores.add(vendedor);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar clientes");

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return vendedores;
    }

    public List<Vendedor> readForNome(String nome) { //SELECIONAR UM VENDEDOR
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Vendedor> vendedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM VENDEDOR WHERE nome like ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId_vendedor(rs.getInt("id_vendedor"));
                vendedor.setNome(rs.getString("nome"));

                vendedores.add(vendedor);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar produtos");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return vendedores;
    }

    public void update(Vendedor vendedor) { //ATUALIZAR DADOS NA TABELA VENDEDOR
        String sql = "UPDATE vendedor SET nome = ? WHERE id_vendedor = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, vendedor.getNome());
            stmt.setInt(2, vendedor.getId_vendedor());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Vendedor v) {

        PreparedStatement stmt = null;
        String sql = "delete from vendedor where id_vendedor = ?";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, v.getId_vendedor());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

}
