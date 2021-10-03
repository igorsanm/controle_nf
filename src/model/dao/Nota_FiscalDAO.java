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
import model.bean.Produto;
import model.bean.Vendedor;
import model.bean.Nota_Fiscal;

/**
 *
 * @author Igor Santana
 */
public class Nota_FiscalDAO {

    private Connection con = null;

    public Nota_FiscalDAO() {
        con = ConnectionFactory.getConnection();

    }

    public void create(Nota_Fiscal nota_fiscal) {
        String sql = "insert into nota_fiscal(numero_nota, data_emissao, valor_total, cliente_id, produto_codigo, vendedor_id) VALUES (?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, nota_fiscal.getNumero_nota());
            stmt.setString(2, nota_fiscal.getData_emissao());
            stmt.setDouble(3, nota_fiscal.getValor_total());

            stmt.setInt(4, nota_fiscal.getCliente().getId_cliente());
            stmt.setInt(5, nota_fiscal.getProduto().getCodigo_produto());
            stmt.setInt(6, nota_fiscal.getVendedor().getId_vendedor());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Nota_Fiscal> read() { //SELECIONAR AS NOTAS FISCAIS DO BD
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "select * from notas_emitidas";

        List<Nota_Fiscal> notas_fiscais = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Nota_Fiscal nota = new Nota_Fiscal();
                nota.setNumero_nota(rs.getInt("numero_nota"));
                nota.setData_emissao(rs.getString("data_emissao"));
                nota.setValor_total(rs.getDouble("valor_nota"));
                
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setRazao_social(rs.getString("razao_social"));
                cliente.setCnpj(rs.getString("cnpj"));
                nota.setCliente(cliente);
                
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("produto"));
                nota.setProduto(produto);
                
                Vendedor vendedor = new Vendedor();
                vendedor.setNome(rs.getString("vendedor"));
                nota.setVendedor(vendedor);
                
                notas_fiscais.add(nota);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar as notas" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return notas_fiscais;
    }

    public List<Nota_Fiscal> readForNum(String nome) { //SELECIONAR AS NOTAS FISCAIS DO BD
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = ("select * from notas_emitidas where razao_social like ?");

        List<Nota_Fiscal> notas_fiscais = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Nota_Fiscal nf = new Nota_Fiscal();
                Cliente cliente = new Cliente();
                Produto p = new Produto();
                Vendedor v = new Vendedor();

                nf.setNumero_nota(rs.getInt("numero_nota"));
                nf.setData_emissao(rs.getString("data_emissao"));
                nf.setValor_total(rs.getDouble("valor_nota"));

                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setRazao_social(rs.getString("razao_social"));
                cliente.setCnpj(rs.getString("cnpj"));
                nf.setCliente(cliente);

                p.setDescricao(rs.getString("produto"));
                nf.setProduto(p);


                v.setNome(rs.getString("vendedor"));
                nf.setVendedor(v);

                notas_fiscais.add(nf);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar clientes");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return notas_fiscais;
    }

    public void update(Nota_Fiscal nf) { //ATUALIZAR NOTAS

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("update nota_fiscal set data_emissao=?, valor_total=?, id_cliente=?, codigo_produto=?, id_vendedor=? where numero_nota=?");

            stmt.setString(1, nf.getData_emissao());
            stmt.setDouble(2, nf.getValor_total());
            stmt.setInt(3, nf.getCliente().getId_cliente());
            stmt.setInt(4, nf.getProduto().getCodigo_produto());
            stmt.setInt(5, nf.getVendedor().getId_vendedor());
            stmt.setInt(6, nf.getNumero_nota());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public void delete(Nota_Fiscal nf) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("delete from nota_fiscal where numero_nota = ?");
            stmt.setInt(1, nf.getNumero_nota());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

}
