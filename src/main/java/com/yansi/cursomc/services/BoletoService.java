package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.PagamentoComBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {

    public void preenchePagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());

    }
}
