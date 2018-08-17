/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carros.dao;

import br.com.carros.db.ConexaoMysql;
import br.com.carros.entity.Carro;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosrodolfosantos
 */
public class CarrosDAO {
    public int salvar(Carro carro) {
        Connection conexao = ConexaoMysql.getConexao();
        try {
            String sql = "INSERT INTO carro SET modelo = ?, fabricante = ?, ano = ?, cor = ?";
            PreparedStatement ps = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getFabricante());
            ps.setInt(3, carro.getAno());
            ps.setString(4, carro.getCor());
            ps.executeUpdate();
            
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
              int rid = rs.getInt(1); 
              //what you get is only a RowId ref, try make use of it anyway U could think of
              return rid;
                //System.out.println(rid);
            }
            
            ConexaoMysql.fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public List<Carro> lista() {
        Connection conexao = ConexaoMysql.getConexao();
        List<Carro> carros = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM carro");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setModelo(rs.getString("modelo"));
                carro.setFabricante(rs.getString("fabricante"));
                carro.setCor(rs.getString("cor"));
                carro.setAno(rs.getInt("ano"));
                
                carros.add(carro);
                //rs.next();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return carros;
    }
    
   public void alterar(Carro carro, int id){
       Connection conexao = ConexaoMysql.getConexao();
        try {
            PreparedStatement ps = conexao.prepareCall("UPDATE carro SET modelo = ?, fabricante = ?, ano = ?, cor = ? WHERE id= ?");
            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getFabricante());
            ps.setInt(3, carro.getAno());
            ps.setString(4, carro.getCor());
            ps.setInt(5, id);
            ps.execute();
            ConexaoMysql.fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void deletar(int id){
       Connection conexao = ConexaoMysql.getConexao();
        try {
            PreparedStatement ps = conexao.prepareCall("DELETE FROM carro WHERE id= ?");
            ps.setInt(1, id);
            ps.execute();
            ConexaoMysql.fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
