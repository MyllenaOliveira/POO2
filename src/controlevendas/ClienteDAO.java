/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlevendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mylle
 */
public class ClienteDAO {
    private Connection connection;
    
    public ClienteDAO()throws SQLException{
        this.connection = ConnectionFactory.getConnection();
    }

     public void Cadastrar(String cliente)throws SQLException{
        String[] cli = cliente.split(",");
        Cliente c = new Cliente();
        c.setNome(cli[0]);
        c.setCpf(cli[2]);
        c.setIdade(Integer.parseInt(cli[1]));
        String sql = "insert into clientes(nome, cpf, idade) values(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1,c.getNome());
        stmt.setString(2,c.getCpf());
        stmt.setInt(3,c.getIdade());
        
        stmt.execute();
        stmt.close();
    }
    
    public Cliente buscar(String cpf)throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("Select * from clientes where cpf = ?");
        stmt.setString(1, cpf);
        
        ResultSet rs = stmt.executeQuery();
        Cliente cliente = new Cliente();
        while(rs.next()){
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setIdade(rs.getInt("idade"));
        }
        
        rs.close();
        stmt.close();
        
        return cliente;
    }
}
