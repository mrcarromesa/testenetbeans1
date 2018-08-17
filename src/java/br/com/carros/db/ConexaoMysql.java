/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carros.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosrodolfosantos
 */
public class ConexaoMysql {
    private static Connection conexao;
    private static final String URL = "jdbc:mysql://localhost/sistemacarros";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";
    
    
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
                } catch (SQLException ex) {
                    Logger.getLogger(ConexaoMysql.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return conexao;
    }
    
    public static void fechaConexao(){
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
