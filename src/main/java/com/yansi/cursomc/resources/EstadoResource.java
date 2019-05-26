package com.yansi.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yansi.cursomc.domain.Cidade;
import com.yansi.cursomc.domain.Estado;
import com.yansi.cursomc.dto.CidadeDTO;
import com.yansi.cursomc.dto.EstadoDTO;
import com.yansi.cursomc.services.CidadeService;
import com.yansi.cursomc.services.EstadoService;

@Controller
@RequestMapping("/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService serviceCidade;
	
	@GetMapping()
	public ResponseEntity<?> findAll(){
		List<Estado> obj = service.findAllOrderNome();
		List<EstadoDTO> objDto = obj.stream().map(objAux -> new EstadoDTO(objAux)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping("/{id}/cidades")
	public ResponseEntity<?> findEstadoCidade(@PathVariable Integer id) {
		List<Cidade> cidade = serviceCidade.findCidades(id);
		List<CidadeDTO> cidadeDto = cidade.stream().map(obj1 -> new CidadeDTO(obj1)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadeDto);
	}
	
}
