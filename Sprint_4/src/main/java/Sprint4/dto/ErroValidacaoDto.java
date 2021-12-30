package Sprint4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroValidacaoDto {
    String campo;
    String mensagem;
}
