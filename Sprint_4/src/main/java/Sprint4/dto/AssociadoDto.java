package Sprint4.dto;

import Sprint4.costantes.Cargo;
import Sprint4.costantes.Sexo;
import Sprint4.entity.Associado;
import Sprint4.entity.Partido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
public class AssociadoDto {

    private Long id;
    private String nome;
    private Cargo cargoPolitico;
    private Sexo sexo;
    @JsonFormat(pattern = "dd/MM/yyyy" , shape = JsonFormat.Shape.STRING )
    private LocalDate dataNascimento;
    @JsonManagedReference
    private PartidoDto partido;

}
