package Sprint4.entity;

import Sprint4.costantes.Cargo;
import Sprint4.costantes.Sexo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity

public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Cargo cargoPolitico;
    private Sexo sexo;
    private LocalDate dataNascimento;
    @OneToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;

}
