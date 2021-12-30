package Sprint4.repository;

import Sprint4.costantes.Cargo;
import Sprint4.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado,Long>{
//
     List<Associado> findByCargoPolitico(Cargo cargo);

//


}
