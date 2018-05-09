package com.yansi.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.yansi.cursomc.enums.EstadoPagamento;
import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private static final Long serialVersionUID = 1L;

    private Integer numeroParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public PagamentoComCartao(Integer numeroParcelas, Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}
