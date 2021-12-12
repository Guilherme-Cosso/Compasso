package Sprint_3.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Estado {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Regiao regiao;
	private String gambiarraregiao;
	private long populacao;
	private String capital;
	private float area;

	public Estado(String nome, long populacao, String capital, float area, Regiao regiao){
		this.nome = nome;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
		this.gambiarraregiao = regiao.toString();
		this.regiao = regiao;
	}

}
