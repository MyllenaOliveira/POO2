package controlevendas;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;


public class Servidor{ 
    
    public static void	main(String args[]) throws SQLException, IOException{
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta!");
        Socket cliente = servidor.accept();
        System.out.println("Nova conexao com o cliente " + cliente.getInetAddress().getHostAddress());
        
        
        Scanner s = new Scanner(cliente.getInputStream()); 
        while (s.hasNextLine()) {
            String c = s.nextLine();
            System.out.println(c);
            executaComando(c);
        }
    }
    
    static void executaComando(String comando) throws IOException, SQLException {
        String[] cmd = comando.split(":");
        String op = cmd[0];
        
        ProdutoDAO prod = new ProdutoDAO();
        FuncionarioDAO fun = new FuncionarioDAO();
        ClienteDAO cli = new ClienteDAO();
        VendaDAO venda = new VendaDAO();
        
        if (op.equals("cadProd")) {
            prod.Cadastrar(cmd[1]);
        } else if (op.equals("cadFunc")) {
            fun.Cadastrar(cmd[1]);
        } else if (op.equals("cadCli")) {
            cli.Cadastrar(cmd[1]);
        } else if (op.equals("venda")) {
            venda.Venda(cmd[1]);
        }
    }
}
