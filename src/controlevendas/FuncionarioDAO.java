/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlevendas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mylle
 */
public class FuncionarioDAO {
    private Connection connection;
    
    public FuncionarioDAO()throws SQLException{
        this.connection = ConnectionFactory.getConnection();
    }

     public void Cadastrar(String funcionario)throws SQLException{
        String[] func = funcionario.split(",");
        Funcionario f = new Funcionario();
        f.setNome(func[0]);
        f.setCpf(func[2]);
        f.setIdade(Integer.parseInt(func[1]));
        String sql = "insert into funcionarios(nome, cpf, idade) values(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1,f.getNome());
        stmt.setString(2,f.getCpf());
        stmt.setInt(3,f.getIdade());
        
        stmt.execute();
        stmt.close();
    }
    
    public Funcionario buscar(String cpf)throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("Select * from funcionrios where cpf = ?");
        stmt.setString(1, cpf);
        
        ResultSet rs = stmt.executeQuery();
        Funcionario funcionario = new Funcionario();
        while(rs.next()){
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setIdade(rs.getInt("idade"));
        }
        
        rs.close();
        stmt.close();
        
        return funcionario;
    }
}