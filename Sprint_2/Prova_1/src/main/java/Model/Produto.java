package Model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    int id;
    String nome;
    String descricao;
    int qnt;
    double preco;
}
