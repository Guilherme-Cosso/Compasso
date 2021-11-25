import Config.MyIO;
import Data.ConnectionFactory;
import Data.FilmeDAO;
import Model.Filme;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Principal {

    public static void main(String[] args) throws IOException, URISyntaxException, SQLException {
        // aaaa
        // criarDataBase();
        ArrayList<Filme> filmes = listFilmes();
        try(Connection con = new ConnectionFactory().makeConnection()) {
            FilmeDAO daoF = new FilmeDAO(con);
            filmes = daoF.readTable();
        }
        do {
            welcome(filmes);
            System.out.println("Digite:");
            System.out.println("1) Para rever o projeto");
            System.out.println("0) Sair");
        }while (MyIO.readBoolean());
    }

    public static void welcome(ArrayList<Filme> f){
        int totalFilmes = 20;
        System.out.println("Bem vindo temos 20 filmes disponiveis.");
        System.out.print("Deseja ver quantos Filmes: \n\n");
        float numFilme = verificaInt();
        int pag = pagina((float) Math.ceil(totalFilmes/numFilme));
        printList(f,pag,(int) numFilme);
    }

    public static int pagina(float nPag){
        System.out.print("Qual a pagina voce deseja ver: \nPagina");
        for (int i = 1;i <= nPag ;i++){
            System.out.print(" - " + i);
        }
        System.out.println(" ");
        int pag = verificaInt();
              pag --;

        return pag;
    }
    public static void printList(ArrayList<Filme> filmes, int pag, int qnt){
        int pagInicial = pag * qnt;
        int pagFinal = pagInicial + qnt;
        while(pagFinal>pagInicial && pagInicial < filmes.size()){
            System.out.println(filmes.get(pagInicial).toString());
            pagInicial++;
        }

    }

    /**
     * @method: Popular a Table (Filme) da Data_Base (Filmes) com os filmes da Marvel
     * @param: Null
     * @return: Null
     */
    public static void criarDataBase() throws IOException, URISyntaxException {
        ArrayList<Filme> filmes = listFilmes();
        System.out.println(filmes);
        try(Connection con = new ConnectionFactory().makeConnection()){
            FilmeDAO daoF = new FilmeDAO(con);
            daoF.createLine(filmes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * @method: Cria um ArrayList<Filmes> abrindo o arquivo filmes.txt com 20 Filmes
     * @param: Null
     * @return: ArrayList<Filme>
     */
    public static ArrayList<Filme> listFilmes() throws IOException, URISyntaxException {
        ArrayList<Filme> filmes = new ArrayList<>();
        File file ;
        URL resource = Principal.class.getClassLoader().getResource("Filme.txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
             file = new File(resource.toURI());
        }
        Scanner reader = new Scanner(file);
        while(reader.hasNext()){
            String[] dados = reader.nextLine().split("\\|");
            filmes.add(criarFilme(dados[0],dados[1],dados[2]));
        }
        return filmes;
    }

    /**
     * @method: Cria um obj tipo filme
     * @param: nome
     * @param: datas
     * @param: descricao
     * @return: Filme
     */
    public static Filme criarFilme(String nome, String datas, String descricao){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(datas, formato);
        return new Filme(0,nome,data,descricao);
    }

    public static int verificaInt(){
        String dado;
        do {
            System.out.print("Numero: ");
            dado = MyIO.readLine();
            if(!isStringInt(dado) || Integer.parseInt(dado) == 0)
                System.out.println("Valor Invalido: Digite um Valor Inteiro Diferente de 0");
        }while (!isStringInt(dado) || Integer.parseInt(dado) == 0);
        return Integer.parseInt(dado);
    }

    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

}
