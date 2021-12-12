package Sprint_3.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import Sprint_3.dto.Form.EstadoForm;
import Sprint_3.dto.Form.UpdateEstadoForm;
import Sprint_3.modelo.Estado;
import Sprint_3.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Sprint_3.dto.EstadoDto;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static java.lang.Math.max;

@RestController
@RequestMapping("/api/states")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;


	@GetMapping
	public List<EstadoDto> lista(@RequestParam String key, String regiao) {
		List<Estado> estados = null;
		if (regiao != null){
			estados = estadoRepository.findByGambiarraregiao(regiao);
			if (key != null && key.equalsIgnoreCase("populacao")) {
				Collections.sort(estados ,(Estado a2, Estado a1) -> comparePopulacao(a1,a2));
			}
			else if(key != null && key.equalsIgnoreCase("area")){
				Collections.sort(estados ,(Estado a2, Estado a1) -> compareArea(a1,a2));
			}
		}
		else {
			estados = estadoRepository.findAll();
			if (key != null && key.equalsIgnoreCase("populacao")) {
				Collections.sort(estados ,(Estado a2, Estado a1) -> comparePopulacao(a1,a2));
			}
			else if(key != null && key.equalsIgnoreCase("area")){
				Collections.sort(estados ,(Estado a2, Estado a1) -> compareArea(a1,a2));
			}
		}
		return EstadoDto.converter(estados);
	}

	private int comparePopulacao(Estado a1, Estado a2) {
		int i =0;
		if (a1.getPopulacao() > a2.getPopulacao()){
			i =1;
		}else if(a1.getPopulacao() < a2.getPopulacao()){
			i = -1;
		}
		return i;
	}

	private int compareArea(Estado a1, Estado a2){
		int i =0;
		if (a1.getArea() > a2.getArea()){
			i =1;
		}else if(a1.getArea() < a2.getArea()){
			i = -1;
		}
		return i;
	}


	@PostMapping
	public ResponseEntity<EstadoDto> cadastra(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder){
		Estado estado = form.converte(estadoRepository);
		estadoRepository.save(estado);
		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}

	@GetMapping("/{id}")
	public EstadoDto detalhar(@PathVariable("id") short id){
		System.out.println(estadoRepository.findById(id));
		return new EstadoDto(estadoRepository.getOne(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstadoDto> atualizar(@RequestBody @Valid UpdateEstadoForm form, @PathVariable("id") short id){
		Estado estado = form.atualizar(id, estadoRepository);
		estadoRepository.save(estado);
		return ResponseEntity.ok(new EstadoDto(estado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EstadoDto> delete(@RequestBody @Valid UpdateEstadoForm form, @PathVariable("id") short id){
		Estado estado = estadoRepository.getOne(id);
		estadoRepository.delete(estado);
		return ResponseEntity.ok(new EstadoDto(estado));
	}
}
