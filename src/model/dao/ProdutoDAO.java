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
import model.bean.Produto;

/**
 *
 * @author Igor Santana
 */
public class ProdutoDAO {

    private Connection con = null;

    public ProdutoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void create(Produto p) { //INSERINDO PRODUTOS NO BD

        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "insert into produto(descricao, valor_unitario) values(?,?)";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValor_unitario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public List<Produto> read() { //LER OS PRODUTOS DO BD
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from produto";

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo_produto(rs.getInt("codigo_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor_unitario(rs.getDouble("valor_unitario"));

                produtos.add(produto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar produtos");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public List<Produto> readForDesc(String desc) { //BUSCAR UM PRODUTO PELA DESCRIÇÃO
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM PRODUTO WHERE descricao like ?");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo_produto(rs.getInt("codigo_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor_unitario(rs.getDouble("valor_unitario"));

                produtos.add(produto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar produtos");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public void update(Produto p) { //ATUALIZAR PRODUTO DO BD
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "update produto set descricao=?, valor_unitario=? where codigo_produto=?";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValor_unitario());
            stmt.setInt(3, p.getCodigo_produto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public void delete(Produto p) { // DELETAR PRODUTO DO BD

        PreparedStatement stmt = null;
        String sql = "delete from produto where codigo_produto = ?";

        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getCodigo_produto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

}
