package Sprint4.controller;

import Sprint4.dto.AssociadoDtoSemPartido;
import Sprint4.dto.PartidoDto;
import Sprint4.dto.PartidoFormDto;
import Sprint4.costantes.Ideologia;
import Sprint4.entity.Partido;
import Sprint4.services.PartidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    private PartidoService service;

    @Autowired
    private ModelMapper modelMapper;

    // EndPoint POST - /partidos
    @PostMapping
    public ResponseEntity<PartidoDto> savePartido(@RequestBody @Valid PartidoFormDto body){
        PartidoDto partidoDto = this.service.save(body);
        return ResponseEntity.ok(partidoDto);
    }

    // EndPoint GET - /partidos (Ter uma opção de filtrar partidos de acordo com sua ideologia)
    @GetMapping
    public ResponseEntity<List<PartidoDto>> getPartido(@RequestParam(required = false) Ideologia ideologia){
        List<PartidoDto> list = service.getPartidos(ideologia);
        return ResponseEntity.ok(list);
    }

    // EndPoint GET - /partidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PartidoDto> getPartido(@PathVariable Long id){
        PartidoDto partido = service.getPartido(id);
        if(partido != null){
            return ResponseEntity.ok(partido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - /partidos/{id}
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PartidoDto> updatePartido(@PathVariable Long id, @RequestBody PartidoFormDto body){
        return ResponseEntity.ok(service.updatePartido(id, body));
    }

    //GET - /partidos/{id}/associados (Listar todos os associados daquele partido)
    @GetMapping("/{id}/associados")
    public ResponseEntity<List<AssociadoDtoSemPartido>> getPartidosAsc(@PathVariable Long id){
        PartidoDto partido = service.getPartido(id);
        service.getPartidosAsc(modelMapper.map(partido, Partido.class));
        if(partido != null){
            return ResponseEntity.ok(service.getPartidosAsc(modelMapper.map(partido, Partido.class)));
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletePartido(@PathVariable Long id, @RequestBody PartidoFormDto body){
        service.deletPartido(id);
        return ResponseEntity.ok().build();
    }




}
