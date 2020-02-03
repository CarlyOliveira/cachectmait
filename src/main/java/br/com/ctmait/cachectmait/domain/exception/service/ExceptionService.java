package br.com.ctmait.cachectmait.domain.exception.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import br.com.ctmait.cachectmait.domain.exception.dto.ExceptionDTO;
	
@Service
public class ExceptionService {

	public ExceptionDTO methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ExceptionDTO.builder()
						   .error("MethodArgumentNotValidException")
						   .error_description(ex.getBindingResult().getAllErrors().toString())
						   .build();
	}
	
	public ExceptionDTO exception(Exception ex) {
		return ExceptionDTO.builder()
						   .error("Internal Server Error")
						   .error_description(ex.getMessage())
						   .build();
	}
	
	public ExceptionDTO httpServerErrorException(HttpServerErrorException ex) {
		return ExceptionDTO.builder()
						   .error(ex.getStatusCode().getReasonPhrase())
						   .error_description(ex.getStatusText())
						   .build();
	}
	
	public ExceptionDTO httpClientErrorException(HttpClientErrorException ex) {
		return ExceptionDTO.builder()
						   .error(ex.getStatusCode().getReasonPhrase())
						   .error_description(ex.getStatusText())
						   .build();
	}
	
}
