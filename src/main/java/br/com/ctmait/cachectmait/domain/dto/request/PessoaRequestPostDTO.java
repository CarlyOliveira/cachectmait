package br.com.ctmait.cachectmait.domain.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequestPostDTO	{
	@NotBlank
	private String nome;
	@NotNull
	private Integer idade;
}
