import Config.MyIO;
import Data.ConnectionFactory;
import Data.ProdutoDAO;
import Model.*;

import java.sql.Connection;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        boolean resp = true;
        while(resp){
            welcome();
            switch (verificaInt()){
                case 1:
                    ArrayList<Produto> produtos = new ArrayList<>();
                    produtos.add(0,new Produto(0,"Telefone","Iphone Max",4,15000.50));
                    produtos.add(new Produto(0,"Televisao","Samsung",2,2000.50));
                    produtos.add(new Produto(0,"Copo","Plástico",20,90.80));
                    try(Connection con = new ConnectionFactory().makeConnection()){
                        ProdutoDAO daoF = new ProdutoDAO(con);
                        daoF.createLine(produtos);
                        System.out.println("Inserido  \n\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try(Connection con = new ConnectionFactory().makeConnection()){
                        ProdutoDAO daoF = new ProdutoDAO(con);
                        ArrayList<Integer> ids = daoF.readTable();
                        if(ids.size() > 1){
                            daoF.updateTable(ids.get(0));
                            System.out.println("Atualizado \n\n");
                        }else {
                            System.out.println("Nao temos produdo para Atualiza \n\n");
                        }
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try(Connection con = new ConnectionFactory().makeConnection()){
                        ProdutoDAO daoF = new ProdutoDAO(con);
                        ArrayList<Integer> ids = daoF.readTable();
                        if(ids.size() > 2){
                            daoF.deleteTable(ids.get(1));
                            System.out.println("Deletado  \n\n");
                        }else {
                            System.out.println("Nao temos protudo para deletar \n\n");
                        }
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo ...");
                    resp = false;
                    break;
                default:
                    System.out.println("Valor invalido: Digite um Numero valido");
                    break;
            }
        }

    }

    public static void welcome(){
        System.out.println("Seja Bem vindo(a): ");
        System.out.println("Digite: ");
        System.out.println("1) Cadastra 3 produtos na Data_Base");
        System.out.println("2) Atualizar o 1 Produto Cadastrado");
        System.out.println("3) Excluir o 2 produto cadastrado");
        System.out.println("0) Sair");
    }


    public static int verificaInt(){
        String dado;
        do {
            System.out.print("Digite um Numero: ");
            dado = MyIO.readLine();
            if(!isStringInt(dado)) System.out.println("Valor Invalido: Digite um Valor Inteiro");
        }while (!isStringInt(dado));
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
