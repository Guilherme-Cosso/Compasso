package Sprint_3.dto.Form;

import Sprint_3.modelo.Estado;
import Sprint_3.modelo.Regiao;
import Sprint_3.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EstadoForm {

    @NotNull @NotEmpty
    String nome;
    @Enumerated(EnumType.STRING)
    Regiao regiao;
    @NotNull @NotEmpty
    String capital;
    long populacao;
    float area;

    public Estado converte(EstadoRepository estadoRepository) {
        return new Estado(nome,populacao,capital,area,regiao);
    }
}
