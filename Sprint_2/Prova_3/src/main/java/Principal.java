import Config.MyIO;

public class Principal {
    public static void main(String[] args) {
        String linha = MyIO.readLine();
        linha += " ";
        String[] femoticom = linha.split(":-\\)");
        String[] temoticom = linha.split(":-\\(");
        System.out.println(femoticom.length);
        System.out.println(temoticom.length);
        int a = femoticom.length;
        int b = temoticom.length;
        if(a-b > 0){
            System.out.println("divertido");
        }else if(a-b == 0){
            System.out.println("neutro");
        }else {
            System.out.println("triste");
        }
    }
}
