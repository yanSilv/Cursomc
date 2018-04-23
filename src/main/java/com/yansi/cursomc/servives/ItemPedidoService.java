package com.yansi.cursomc.servives;

import com.yansi.cursomc.domain.ItemPedido;
import com.yansi.cursomc.repositories.ItemPedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repo;

    public void saveList(List<ItemPedido> asList) {
        repo.save(asList);
    }
}
