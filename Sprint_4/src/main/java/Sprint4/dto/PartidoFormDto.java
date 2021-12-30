package Sprint4.dto;
import Sprint4.costantes.Ideologia;
import Sprint4.entity.Associado;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class PartidoFormDto {

    @NotEmpty @NotNull
    private String nomePartido;
    @NotEmpty @NotNull
    private String sigla;
    private Ideologia ideologia;
    @JsonFormat(pattern = "dd/MM/yyyy" , shape = JsonFormat.Shape.STRING )
    private LocalDate dataFundacao;

}
