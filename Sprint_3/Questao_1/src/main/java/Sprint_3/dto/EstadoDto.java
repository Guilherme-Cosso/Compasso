package Sprint_3.dto;

import Sprint_3.modelo.Estado;
import Sprint_3.modelo.Regiao;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data

public class EstadoDto {

	private String nome;
	private long populacao;
	private Regiao regiao;
	private String gambiarraregiao;
	private String capital;
	private float area;

	public EstadoDto(Estado estado) {
		this.nome = estado.getNome();
		this.populacao = estado.getPopulacao();
		this.regiao = estado.getRegiao();
		this.gambiarraregiao = estado.getGambiarraregiao();
		this.capital = estado.getCapital();
		this.area = estado.getArea();
	}

	public static List<EstadoDto> converter(List<Estado> estados) {
		return estados.stream().map(EstadoDto::new).collect(Collectors.toList());
	}

}
