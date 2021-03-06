package Sprint4.repository;


import Sprint4.costantes.Ideologia;
import Sprint4.entity.Partido;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartidoRepositoryTest {

    @Autowired
    private PartidoRepository partidoRepository;

    @Test
    public void procuaraIdeologia() {
        Ideologia ideologia = Ideologia.CENTRO;
        List<Partido> list = partidoRepository.findByIdeologia(ideologia);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(1).getIdeologia(),ideologia);
    }


}
