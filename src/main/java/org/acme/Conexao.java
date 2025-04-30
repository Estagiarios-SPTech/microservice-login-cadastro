package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class Conexao {
    private String url = "jdbc:mysql://localhost:3306/ordensof";
    private String usuario = "superColaborador";
    private String senha = "senha123!";

    public Connection conectarBanco() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}
