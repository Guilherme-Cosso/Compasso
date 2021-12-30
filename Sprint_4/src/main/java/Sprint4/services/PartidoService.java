package Sprint4.services;

import Sprint4.dto.AssociadoDtoSemPartido;
import Sprint4.dto.PartidoDto;
import Sprint4.dto.PartidoFormDto;
import Sprint4.costantes.Ideologia;
import Sprint4.entity.Partido;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface PartidoService  {

    public PartidoDto save(PartidoFormDto body);

    public List<PartidoDto> getPartidos(@RequestParam(required = false) Ideologia ideologia);

    public PartidoDto getPartido(@PathVariable Long id);

    PartidoDto updatePartido(@PathVariable Long id, PartidoFormDto body);

    public List<AssociadoDtoSemPartido> getPartidosAsc(Partido partido);

    void deletPartido(Long id);
}
