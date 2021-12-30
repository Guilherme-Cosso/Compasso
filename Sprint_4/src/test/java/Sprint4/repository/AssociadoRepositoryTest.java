package Sprint4.repository;

import Sprint4.costantes.Cargo;
import Sprint4.costantes.Ideologia;
import Sprint4.entity.Associado;
import Sprint4.entity.Partido;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociadoRepositoryTest {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Test
    public void procuaraCurso() {
        Cargo cargo = Cargo.PRESIDENTE;
        List<Associado> list = associadoRepository.findByCargoPolitico(cargo);
        Assert.assertNotNull(list);
    }

}
