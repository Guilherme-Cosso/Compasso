package Sprint_3.repository;

import Sprint_3.modelo.Estado;
import Sprint_3.modelo.Regiao;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Short> {

    List<Estado> findByNome(String nome);

//
    List<Estado> findByGambiarraregiao(String regiao);


//



}
