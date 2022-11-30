package com.example.divulgador.ConexaoBanco;

// Classes necessárias para uso de Banco de dados
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;


// Início da classe de conexão
public class ConexaoMySQL {

    //Método de Conexão//
    public static Connection getConexaoMySQL() {

        Connection connection = null;
        // Nomeando a localização do Driver
        String driverName = "com.mysql.cj.jdbc.Driver";
        // Configurando a nossa conexão com um banco de dados
        // A url teoricamente terminaria em "divulgador", porém o sistema estava retornando um erro de
        // time zone, então foi adicionado o restante para evitar esse erro
        String url = "jdbc:mysql://localhost:3306/divulgador?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "A1b2C3d4E5f6!@#$";

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch(Exception e) {
            // Caso não for possível conectar ao Banco, a seguinte mensagem será mostrada
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao Banco de Dados! " + e.getMessage());
            return null;
        }
    }
}
