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
import javax.swing.JOptionPane;

/**
 *
 * @author mylle
 */
public class VendaDAO {
    
    private Connection connection;
    
    public VendaDAO()throws SQLException{
        this.connection = ConnectionFactory.getConnection();
    }
    
    public void Venda(String vend)throws SQLException{
        String[] dados = vend.split(",");
        String nomeProd = dados[0];
        int qtd = Integer.parseInt(dados[1]);
        String cpfCli = dados[2];
        String cpfFun = dados[3];
        
        ProdutoDAO p = new ProdutoDAO();
        Produto produto = p.buscar(nomeProd);
        
        if (qtd <= 0 || qtd > produto.getQtd()) {
            JOptionPane.showMessageDialog(null, "Quantidade Inválida!");
            return;
        }
        
        Venda venda = new Venda();
        venda.setValorVenda(qtd * produto.getValor());
        
        int NovaQtd = produto.getQtd() - qtd;
        
        String sql = "select id from produtos where nome=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1, nomeProd);
        
        ResultSet rs = stmt.executeQuery();
        rs.first();
        
        int idProduto = rs.getInt("id");
        
        sql = "insert into venda(cpf_cli, cpf_fun, id_prod, valor) values(?,?,?,?)";
        stmt = connection.prepareStatement(sql);
       
        stmt.setString(1,cpfCli);
        stmt.setString(2, cpfFun);
        stmt.setInt(3, idProduto);
        stmt.setDouble(4,venda.getValorVenda());
       
        stmt.execute();
        
        stmt = this.connection.prepareStatement("update produtos set quantidade = ? where id = ?");
        stmt.setInt(1, NovaQtd);
        stmt.setInt(2,idProduto);
        
        stmt.execute();
        
        rs.close();
        stmt.close();
    }
    
}
