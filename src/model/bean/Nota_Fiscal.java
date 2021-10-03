/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Igor Santana
 */
public class Nota_Fiscal {
    
    private int numero_nota;
    private String data_emissao;
    private double valor_total;
    
    private Cliente cliente;
    private Produto produto;
    private Vendedor vendedor;

    public Nota_Fiscal() {
    }

    public Nota_Fiscal(int numero_nota, String data_emissao, double valor_total, Cliente cliente, Produto produto, Vendedor vendedor) {
        this.numero_nota = numero_nota;
        this.data_emissao = data_emissao;
        this.valor_total = valor_total;
        this.cliente = cliente;
        this.produto = produto;
        this.vendedor = vendedor;
    }

    
    
    public int getNumero_nota() {
        return numero_nota;
    }

    public void setNumero_nota(int numero_nota) {
        this.numero_nota = numero_nota;
    }

    public String getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(String data_emissao) {
        this.data_emissao = data_emissao;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
    

   


