package Sprint4.services;

import Sprint4.costantes.Cargo;
import Sprint4.dto.AssociadoDto;
import Sprint4.dto.AssociadoFormDto;
import Sprint4.dto.PartidoDto;
import Sprint4.dto.VincularAssociadoPatidoDtoForm;
import Sprint4.entity.Associado;
import Sprint4.entity.Partido;
import Sprint4.repository.AssociadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoServiceImple implements AssociadoService{

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PartidoService serviceP;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssociadoDto save(AssociadoFormDto body) {
        Associado associado = modelMapper.map(body, Associado.class);
        Associado associadoSave = this.associadoRepository.save(associado);
        return  modelMapper.map(associadoSave, AssociadoDto.class);
    }

    @Override
    public AssociadoDto getAssociado(Long id) {
        Optional<Associado> associado = this.associadoRepository.findById(id);
        if (associado.isPresent()){
            return  modelMapper.map(associado.get(), AssociadoDto.class);
        }else{
            return null;
        }
    }

    @Override
    public AssociadoDto updateAssociado(Long id, AssociadoFormDto body) {
          Associado associado = associadoRepository.getOne(id);
          associado.setNome(body.getNome());
          associado.setCargoPolitico(body.getCargoPolitico());
          associado.setSexo(body.getSexo());
          return  modelMapper.map(associado, AssociadoDto.class);
    }

    @Override
    public void deletAssociado(Long id) {
        associadoRepository.deleteById(id);
    }

    @Override
    public AssociadoDto saveAssociadoPartido(VincularAssociadoPatidoDtoForm body) {
        if(serviceP.getPartido(body.getIdPartido()) != null && getAssociado(body.getIdAssociado()) != null){
            Associado associado = associadoRepository.getOne(body.getIdAssociado());
            associado.setPartido(modelMapper.map( serviceP.getPartido(body.getIdPartido()),Partido.class));
            return modelMapper.map(associado, AssociadoDto.class);
        }else{
            return null;
        }
    }

    @Override
    public AssociadoDto deletAssociadoPartido(Long idAssociado, Long idPartido ) {
        if(serviceP.getPartido(idPartido) != null && getAssociado(idAssociado) != null){
            Associado associado = associadoRepository.getOne(idAssociado);
            associado.setPartido(null);
            return modelMapper.map(associado, AssociadoDto.class);
        }else{
            return null;
        }


    }

    @Override
    public List<AssociadoDto> getAssociados() {
        List<Associado> list =  this.associadoRepository.findAll();
        return  list.stream().map(pa ->  modelMapper.map(pa, AssociadoDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<AssociadoDto> getAssociadosS(Cargo cargo) {
        List<Associado> list;
        if (cargo == null){
            list =  this.associadoRepository.findAll();
        }
        else{
            list =  this.associadoRepository.findByCargoPolitico(cargo);
        }
        return  list.stream().map(pa ->  modelMapper.map(pa,AssociadoDto.class)).collect(Collectors.toList());
    }

}
