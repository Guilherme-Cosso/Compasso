// Desitir
//package Sprint4.controller;
//
//
//import lombok.SneakyThrows;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.net.URI;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class PartidoControllerTeste {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @SneakyThrows
//    public void deveriaDevolver200Ok(){
//        URI uri = new URI("/partidos");
//        String json = "{\n" +
//                "    \"nomePartido\": \"Partido de Spring\",\n" +
//                "    \"sigla\": \"PS\",\n" +
//                "    \"ideologia\": \"CENTRO\",\n" +
//                "    \"dataFundacao\": 22/10/2002\n" +
//                "}";
//
//        mockMvc
//                .perform(
//                        MockMvcRequestBuilders
//                        .post(uri)
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(
//                        MockMvcResultMatchers
//                        .status()
//                        .is(200)
//                );
//    }
//
//}
