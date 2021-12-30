package Sprint4.controller;

import Sprint4.costantes.Cargo;
import Sprint4.dto.AssociadoDto;
import Sprint4.dto.AssociadoFormDto;
import Sprint4.dto.VincularAssociadoPatidoDtoForm;
import Sprint4.services.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService service;

    // EndPoint POST - /associados
    @PostMapping
    public ResponseEntity<AssociadoDto> saveAssociado(@RequestBody @Valid AssociadoFormDto body){
        AssociadoDto associadoDto = this.service.save(body);
        return ResponseEntity.ok(associadoDto);
    }

    //• POST - /associados/partidos (Vincula um associado a um partido, com o body: {“idAssociado”:    10, “idPartido”: 10})
    @PostMapping
    @RequestMapping("/partidos")
    @Transactional
    public ResponseEntity<AssociadoDto> saveAssociadoPartido(@RequestBody VincularAssociadoPatidoDtoForm body){
        AssociadoDto associadoDto = this.service.saveAssociadoPartido(body);
        return ResponseEntity.ok(associadoDto);
    }

//    @GetMapping
//    public  ResponseEntity<List<AssociadoDto>> getAssociados(){
//        List<AssociadoDto> list = this.service.getAssociados();
//        return ResponseEntity.ok(list);
//    }

    //• GET - /associados/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDto> getAssociado(@PathVariable Long id){
        return ResponseEntity.ok(service.getAssociado(id));
    }

    //• PUT - /associados/{id}
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AssociadoDto> updateAssociado(@PathVariable Long id, @RequestBody AssociadoFormDto body){
        return ResponseEntity.ok(service.updateAssociado(id, body));
    }

    //  DELETE - /associados/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletAssociado(@PathVariable Long id){
        service.deletAssociado(id);
        return ResponseEntity.ok().build();
    }


   @GetMapping
    public ResponseEntity<List<AssociadoDto>> getAssociados(@RequestParam(required = false) Cargo cargo) {
        List<AssociadoDto> list = service.getAssociadosS(cargo);
       if(list != null){
           return ResponseEntity.ok(list);
       }else{
           return ResponseEntity.notFound().build();
       }
    }

    //• DELETE - /associados/{id}/partidos/{id} (Remove determinado associado daquele partido)
    @DeleteMapping("/{id}/partidos/{ids}")
    @Transactional
    public ResponseEntity<AssociadoDto> deletAssociadoPartido(@PathVariable Long id, @PathVariable Long ids){
        return ResponseEntity.ok(service.deletAssociadoPartido(id, ids));
    }

}

