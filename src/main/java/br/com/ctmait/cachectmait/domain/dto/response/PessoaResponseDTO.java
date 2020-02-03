package br.com.ctmait.cachectmait.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaResponseDTO {
	private String uuid;
	private String nome;
	private Integer idade;
}
