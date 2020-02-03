package br.com.ctmait.cachectmait.domain.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
	private final String error;
	private final String error_description;
}
