package Sprint4.dto;

import Sprint4.costantes.Cargo;
import Sprint4.costantes.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AssociadoFormDto {
    @NotEmpty @NotNull
    private String nome;
    private Cargo cargoPolitico;
    private Sexo sexo;
    @JsonFormat(pattern = "dd/MM/yyyy" , shape = JsonFormat.Shape.STRING )
    private LocalDate dataNascimento;

}
