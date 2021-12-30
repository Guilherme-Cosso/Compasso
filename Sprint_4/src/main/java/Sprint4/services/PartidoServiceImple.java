package Sprint4.services;

import Sprint4.dto.AssociadoDtoSemPartido;
import Sprint4.dto.PartidoDto;
import Sprint4.dto.PartidoFormDto;
import Sprint4.costantes.Ideologia;
import Sprint4.entity.Associado;
import Sprint4.entity.Partido;
import Sprint4.repository.PartidoRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImple implements PartidoService{

    @Autowired
    private PartidoRepository partidoRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    @JsonFormat(pattern = "dd/MM/yyyy")
    public PartidoDto save(PartidoFormDto body) {
        // o nome dos atributos deve estar igual.
        Partido partido = modelMapper.map(body, Partido.class);
        Partido partidosave = this.partidoRepository.save(partido);
        return  modelMapper.map(partidosave, PartidoDto.class);
    }


    @Override
    public List<PartidoDto> getPartidos(@RequestParam(required = false) Ideologia ideologia) {
        List<Partido> list;
        if (ideologia == null){
            list =  this.partidoRepository.findAll();
        }
        else{
            list =  this.partidoRepository.findByIdeologia(ideologia);
        }
        return  list.stream().map(pa ->  modelMapper.map(pa,PartidoDto.class)).collect(Collectors.toList());
    }

    @Override
    public PartidoDto getPartido(Long id) {
        Optional<Partido> partido = this.partidoRepository.findById(id);
        if (partido.isPresent()){
            return  modelMapper.map( partido.get(), PartidoDto.class);
        }else{
            return null;
        }
    }

    @Override
    public PartidoDto updatePartido(Long id, PartidoFormDto body) {
        Partido partido = partidoRepository.getOne(id);
        partido.setNomePartido(body.getNomePartido());
        partido.setIdeologia(body.getIdeologia());
        partido.setSigla(body.getSigla());
//        partido.setDataFundacao(body.getDataFundacao());
        return  modelMapper.map(partido, PartidoDto.class);
    }

    @Override
    public List<AssociadoDtoSemPartido> getPartidosAsc(Partido partido) {
        List<Associado> associados = partido.getList();
        return associados.stream().map(pa ->  modelMapper.map(pa, AssociadoDtoSemPartido.class)).collect(Collectors.toList());
    }


    @Override
    public void deletPartido(Long id) {
        partidoRepository.deleteById(id);
    }


}
