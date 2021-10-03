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
import model.bean.Cliente;

/**
 *
 * @author Igor Santana
 */
public class ClienteDAO {

    private Connection con = null;

    public ClienteDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void create(Cliente c) { //CADASTRAR CLIENTES

        PreparedStatement stmt = null;
        String sql = "insert into cliente(cnpj, razao_social, endereco, telefone) values(?,?,?,?)";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getCnpj());
            stmt.setString(2, c.getRazao_social());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getTelefone());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public List<Cliente> read() { //SELECIONAR OS CLIENTES DO BD
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM CLIENTE";

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setRazao_social(rs.getString("razao_social"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar clientes");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }

    public List<Cliente> readForNome(String nome) { //SELECIONAR UM DOS CLIENTES BUSCANDO PELO NOME
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM CLIENTE WHERE razao_social like ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setRazao_social(rs.getString("razao_social"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar clientes");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }

    public void update(Cliente c) { //ATUALIZAR DADOS DA TABELA CLIENTE

        PreparedStatement stmt = null;
        String sql = "update cliente set cnpj=?, razao_social=?, endereco=?, telefone=? where id_cliente=?";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getCnpj());
            stmt.setString(2, c.getRazao_social());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getTelefone());
            stmt.setInt(5, c.getId_cliente());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public void delete(Cliente c) { //DELETAR CLIENTE DA TABELA

        PreparedStatement stmt = null;
        String sql = "delete from cliente where id_cliente = ?";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId_cliente());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

}
