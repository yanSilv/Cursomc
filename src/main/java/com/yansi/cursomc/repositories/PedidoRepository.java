package com.yansi.cursomc.repositories;

import com.yansi.cursomc.domain.Pedido;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
