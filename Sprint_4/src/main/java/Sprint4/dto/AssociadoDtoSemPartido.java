package Sprint4.dto;

import Sprint4.costantes.Cargo;
import Sprint4.costantes.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssociadoDtoSemPartido {

    private Long id;
    private String nome;
    private Cargo cargoPolitico;
    private Sexo sexo;
    @JsonFormat(pattern = "dd/MM/yyyy" , shape = JsonFormat.Shape.STRING )
    private LocalDate dataNascimento;


}
