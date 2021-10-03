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
public class Produto {
    private int codigo_produto;
    private String descricao;
    private double valor_unitario;

    public Produto() {
    }

    public Produto(String descricao, double valor_unitario) {
        this.descricao = descricao;
        this.valor_unitario = valor_unitario;
    }
    
    public int getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
    
    
    
}
