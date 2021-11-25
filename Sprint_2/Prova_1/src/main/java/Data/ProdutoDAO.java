package Data;
import Model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoDAO {
    Connection con;

    private PreparedStatement executeLine() throws SQLException {
        PreparedStatement pstm = con.prepareStatement(
                "SELECT idproduto " +
                    "FROM produto" );
        pstm.execute();
        return  pstm;
    }

    public ArrayList<Integer> readTable() throws SQLException {
        ResultSet rst = executeLine().getResultSet();
        ArrayList<Integer> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getInt(1));
        }
        return ids;
    }


    /**
     * @method: commit os valores do ArrayList ou da rollback caso de algum erro
     * @param: ArrayList<Filme> p
     * @return: void
     */
    public void createLine(ArrayList<Produto> p) throws SQLException {
        con.setAutoCommit(false);
        ArrayList<Integer> IdsGenereds = new ArrayList<>();
        try(PreparedStatement pstm =
                    con.prepareStatement("INSERT INTO produto (nome, descricao, quantidade, preco) " +
                                    "VALUES (?, ?, ?, ?)"
                            , Statement.RETURN_GENERATED_KEYS)){
            for (Produto produto : p){
                createLine(produto, pstm);
                IdsGenereds.add(listProductC(pstm.getGeneratedKeys()));
            }
            System.out.println(IdsGenereds);
            con.commit();
        }catch (Exception e){
            e.printStackTrace();
            con.rollback();
        }
    }

    /**
     * @method: Prepara os dados para serem laçados no MySql
     * @param: Filme p
     * @param: PreparedStatment pstm
     * @return: void
     */
    private void createLine(Produto p,PreparedStatement pstm)throws SQLException{
        pstm.setString(1,p.getNome());
        pstm.setString(2,p.getDescricao());
        pstm.setInt(3,p.getQnt());
        pstm.setDouble(4,p.getPreco());
        pstm.execute();
    }

    /**
     * @method: Retorna o Id criado
     * @param: ResultSet rst
     * @return: int
     */
    private int listProductC(ResultSet rst) throws SQLException {
        int id = -1;
        while(rst.next()){
            id = rst.getInt(1);
        }
        return id;
    }

    public int deleteTable(int number) throws SQLException {
        PreparedStatement pstm = con.prepareStatement("DELETE FROM produto " +
                                                        "WHERE idproduto = ?");
        pstm.setInt(1,number);
        pstm.execute();
        return pstm.getUpdateCount();
    }

    public int updateTable(int number) throws SQLException{
        PreparedStatement pstm = con.prepareStatement(
                "UPDATE produto " +
                    "SET nome = 'Alterado'" +
                    "WHERE idproduto = ?");
        pstm.setInt(1,number);
        pstm.execute();
        return pstm.getUpdateCount();
    }



}
