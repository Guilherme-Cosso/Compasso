package Sprint4.services;

import Sprint4.costantes.Cargo;
import Sprint4.dto.AssociadoDto;
import Sprint4.dto.AssociadoFormDto;
import Sprint4.dto.VincularAssociadoPatidoDtoForm;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface AssociadoService {

    AssociadoDto save(AssociadoFormDto body);
    AssociadoDto getAssociado(Long id);
    AssociadoDto updateAssociado(@PathVariable  Long id, AssociadoFormDto body);
    void deletAssociado(Long id);
    AssociadoDto saveAssociadoPartido(VincularAssociadoPatidoDtoForm body);
    AssociadoDto deletAssociadoPartido(Long id, Long ids);
    List<AssociadoDto> getAssociados();

    List<AssociadoDto> getAssociadosS(Cargo cargo);
}
