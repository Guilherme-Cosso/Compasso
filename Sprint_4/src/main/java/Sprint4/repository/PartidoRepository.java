package Sprint4.repository;

import Sprint4.costantes.Ideologia;
import Sprint4.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido,Long> {

    List<Partido> findByIdeologia(Ideologia ideologia);


}
