package Data;

import Model.Filme;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor

public class FilmeDAO {

    Connection con;

    private PreparedStatement executeLine() throws SQLException {
        PreparedStatement pstm = con.prepareStatement(
                "SELECT idfilme, NOME, DATA ,DESCRICAO " +
                    "FROM FILME" );
        pstm.execute();
        return  pstm;
    }

    public ArrayList<Filme> readTable() throws SQLException {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ResultSet rst = executeLine().getResultSet();
        ArrayList<Filme> filme = new ArrayList<>();
        while (rst.next()) {
            int id = rst.getInt("idfilme");
            String name = rst.getNString("NOME");
            LocalDate data = LocalDate.parse(dateFormat.format(rst.getDate(3)), formato);
            String descricao = rst.getNString(4);
            Filme p = new Filme(id, name, data,descricao);
            filme.add(p);
        }

        return filme;
    }


    /**
     * @method: commit os valores do ArrayList ou da rollback caso de algum erro
     * @param: ArrayList<Filme> p
     * @return: void
     */
    public void createLine(ArrayList<Filme> p) throws SQLException {
        con.setAutoCommit(false);
        ArrayList<Integer> IdsGenereds = new ArrayList<>();
        try(PreparedStatement pstm =
                    con.prepareStatement("INSERT INTO filme (nome, data, descricao) VALUES (?, ?, ?)"
                            , Statement.RETURN_GENERATED_KEYS)){
            for (Filme filme : p){
                createLine(filme, pstm);
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
    private void createLine(Filme p,PreparedStatement pstm)throws SQLException{
        pstm.setString(1,p.getNome());
        pstm.setDate(2, Date.valueOf(p.getAno()));
        pstm.setString(3,p.getDescricao());
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
}
