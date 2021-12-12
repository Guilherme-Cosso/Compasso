package Sprint_3.dto.Form;

// Foi criado essa Classe form com o intuito de somente Atualizar compos mutáveis de Estado

import Sprint_3.modelo.Estado;
import Sprint_3.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateEstadoForm {

    private long populacao;
    private float area;

    public Estado atualizar(short id, EstadoRepository estadoRepository) {
        Estado estado = estadoRepository.getOne(id);
        estado.setArea(this.area);
        estado.setPopulacao(this.populacao);
        return estado;
    }


}
