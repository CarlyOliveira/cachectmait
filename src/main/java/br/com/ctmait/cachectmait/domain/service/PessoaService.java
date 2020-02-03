package br.com.ctmait.cachectmait.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.ctmait.cachectmait.domain.converter.request.PessoaRequestConverter;
import br.com.ctmait.cachectmait.domain.converter.response.PessoaResponseConverter;
import br.com.ctmait.cachectmait.domain.dto.request.PessoaRequestPostDTO;
import br.com.ctmait.cachectmait.domain.dto.request.PessoaRequestPutDTO;
import br.com.ctmait.cachectmait.domain.dto.response.PessoaResponseDTO;
import br.com.ctmait.cachectmait.domain.model.Pessoa;
import br.com.ctmait.cachectmait.domain.repository.PessoaRepository;
import lombok.SneakyThrows;

@Service
public class PessoaService {

	private static final String PESSOA_NAO_ENCONTRADA_GET_MSG = "A pessoa não foi encontrada para ser recuperada.";

	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PessoaResponseConverter pessoaResponseConverter;
	
	@Autowired
	PessoaRequestConverter pessoaRequestConverter;
	
	@SneakyThrows
	@Cacheable(cacheNames = "Pessoa", key = "#uuid" )
	public PessoaResponseDTO getPessoaByUuid(final String uuid) {
		Thread.sleep(2000L);
		return pessoaResponseConverter.convert(getByUuid(uuid));
	}
	
	@SneakyThrows
	@Cacheable(cacheNames = "Pessoas", key = "#root.method.name" )
	public List<PessoaResponseDTO> getAll(){
		Thread.sleep(2000L);
		return pessoaRepository.findAll()
							   .stream()
							   .map(pessoaResponseConverter::convert)
							   .collect(Collectors.toList());
	}
	
	@CachePut(cacheNames = "Pessoa", key="#request.getUuid()")
	@CacheEvict(cacheNames = "Pessoas", allEntries = true)
	public PessoaResponseDTO put(final PessoaRequestPutDTO request) {
		return pessoaRepository.getByUuid(request.getUuid())
					    	   .map(pessoa -> pessoaRequestConverter.convert(request, pessoa))
					    	   .map(pessoaRepository::save)
					    	   .map(pessoaResponseConverter::convert)
					    	   .orElseThrow(this::generateNotFound);
	}
	
    @CacheEvict(cacheNames = "Pessoas", allEntries = true)
	public PessoaResponseDTO post(final PessoaRequestPostDTO request) {
		return pessoaResponseConverter.convert(pessoaRepository.save(pessoaRequestConverter.convert(request)));
	}
    
    @CachePut(cacheNames = "Pessoa", key="#uuid")
	@CacheEvict(cacheNames = "Pessoas", allEntries = true)
	public void delete(final String uuid) {
		pessoaRepository.delete(getByUuid(uuid));						
    }
    
    private Pessoa getByUuid(String uuid) {
    	return pessoaRepository.getByUuid(uuid)
    						   .orElseThrow(this::generateNotFound);
    }
    
    private HttpClientErrorException generateNotFound() {
    	return new HttpClientErrorException(HttpStatus.NOT_FOUND, PESSOA_NAO_ENCONTRADA_GET_MSG);
    }
}
