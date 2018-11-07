/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlevendas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mylle
 */
public class ProdutoDAO{
    
    private Connection connection;
    
     public ProdutoDAO()throws SQLException{
        this.connection = ConnectionFactory.getConnection();
     }
    
     public void Cadastrar(String produto)throws SQLException{
        String[] dados = produto.split(",");
        Produto p = new Produto();
        p.setNomeProd(dados[0]);
        p.setValor(Double.parseDouble(dados[1]));
        p.setQtd(Integer.parseInt(dados[2]));
        String sql = "insert into produtos(nome, valor, quantidade) values(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1,p.getNomeProd());
        stmt.setDouble(2,p.getValor());
        stmt.setInt(3,p.getQtd());
        
        stmt.execute();
        stmt.close();
    }
    
    public Produto buscar(String nome)throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("Select * from produtos where nome=?");
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        Produto produto = new Produto();
        
        rs.first();
        
        produto.setNomeProd(rs.getString("nome"));
        produto.setValor(rs.getDouble("valor"));
        produto.setQtd(rs.getInt("quantidade"));
        
        rs.close();
        stmt.close();
        
        return produto;
    }
    
}
