package br.com.ctmait.cachectmait.domain.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import br.com.ctmait.cachectmait.domain.exception.dto.ExceptionDTO;
import br.com.ctmait.cachectmait.domain.exception.service.ExceptionService;

@ControllerAdvice
public class HandleException {

	@Autowired
	ExceptionService service;
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseBody
	public ResponseEntity<ExceptionDTO> handleException(HttpClientErrorException ex) {
		return new ResponseEntity<ExceptionDTO>(
				service.httpClientErrorException(ex),
				ex.getStatusCode());
	}

	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseBody
	public ResponseEntity<ExceptionDTO> handleException(HttpServerErrorException ex) {
		return new ResponseEntity<ExceptionDTO>(
				service.httpServerErrorException(ex),
				ex.getStatusCode());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDTO> handleException(Exception ex) {
		return new ResponseEntity<ExceptionDTO>(
				service.exception(ex),
				HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDTO> handleException(MethodArgumentNotValidException ex) {
		return new ResponseEntity<ExceptionDTO>(
				service.methodArgumentNotValidException(ex),
				HttpStatus.BAD_REQUEST);
	}
}
