package Sprint4.entity;

import Sprint4.costantes.Ideologia;
import Sprint4.entity.Associado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePartido;
    private String sigla;
    private Ideologia ideologia;
    private LocalDate dataFundacao;
    @OneToMany(mappedBy="partido")
    List<Associado> list;

}
