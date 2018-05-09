package com.yansi.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {

    private static final Long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private double desconto;
    private Integer quantidade;
    private double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, double desconto, Integer quantidade, double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubTotal() {
        return (preco - desconto) * quantidade;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        this.id.setProduto(produto);
    }

    public void setPedido(Pedido pedido) {
        this.id.setPedido(pedido);
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
