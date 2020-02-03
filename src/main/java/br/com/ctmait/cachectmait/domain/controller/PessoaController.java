package br.com.ctmait.cachectmait.domain.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ctmait.cachectmait.domain.dto.request.PessoaRequestPostDTO;
import br.com.ctmait.cachectmait.domain.dto.request.PessoaRequestPutDTO;
import br.com.ctmait.cachectmait.domain.dto.response.PessoaResponseDTO;
import br.com.ctmait.cachectmait.domain.service.PessoaService;

@RequestMapping("/api")
@RestController
public class PessoaController {

	@Autowired
	PessoaService service;
	
	@GetMapping("/pessoa/{uuid}")
	public ResponseEntity<PessoaResponseDTO> get(@PathVariable("uuid") String uuid) throws Exception {
		return new ResponseEntity<>(service.getPessoaByUuid(uuid),HttpStatus.OK);
	}
	
	@GetMapping("/pessoas")
	public ResponseEntity<List<PessoaResponseDTO>> get() throws Exception {
		return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
	}
	
	@PutMapping("/pessoa")
	public ResponseEntity<PessoaResponseDTO> put(@RequestBody @Valid PessoaRequestPutDTO request) throws Exception {
		return new ResponseEntity<>(service.put(request),HttpStatus.OK);
	}	

	@PostMapping("/pessoa")
	public ResponseEntity<PessoaResponseDTO> post(@RequestBody @Valid PessoaRequestPostDTO request) throws Exception {
		return new ResponseEntity<>(service.post(request),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/pessoa/{uuid}")
	public void delete(@PathVariable("uuid") String uuid) throws Exception {
		service.delete(uuid);
	}
	
	
}
