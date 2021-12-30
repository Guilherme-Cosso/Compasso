package Sprint4.dto;

import Sprint4.costantes.Ideologia;
import Sprint4.entity.Associado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;


@Data
public class PartidoDto {


    private Long id;
    private String nomePartido;
    private String sigla;
    private Ideologia ideologia;
    @JsonFormat(pattern = "dd/MM/yyyy" , shape = JsonFormat.Shape.STRING )
    private LocalDate dataFundacao;
    @JsonBackReference
    List<AssociadoDto> list;
}
