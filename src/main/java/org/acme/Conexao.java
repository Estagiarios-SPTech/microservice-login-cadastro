package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;

@ApplicationScoped
public class Conexao {
    private String url = "jdbc:mysql://localhost:3306/teste";
    private String usuario = "superColaborador";
    private String senha = "senha123!";

    public Connection conectarBanco() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

//    private String url = "jdbc:h2:mem:test";
//    public Connection conectarBanco() throws SQLException {
//        Connection conexao = DriverManager.getConnection(url);
//
//        Statement query = conexao.createStatement();
//        query.execute("create table if not exists users ("
//                + "id int primary key auto_increment, "
//                + "name varchar(120), "
//                + "email varchar(120), "
//                + "role varchar(50), "
//                + "password varchar(35)"
//                + ");");
//
//        query.execute("create table if not exists employees ("
//                + "id int primary key auto_increment, "
//                + "`user` int, "
//                + "rt int, "
//                + "manager int, "
//                + "`status` varchar(30),"
//                + "foreign key (`user`) references users(id),"
//                + "foreign key (rt) references users(id),"
//                + "foreign key (manager) references users(id)"
//                + ");");
//
//        ResultSet resultado = query.executeQuery("select * from users where id in (1, 2, 3);");
//        if(!resultado.next()){
//            query.executeUpdate("insert into users (name, email, role, password) " +
//                    "values ('Rafael', 'rafael@stefanini.com', 'Admin', '123')");
//            query.executeUpdate("insert into users (name, email, role, password) " +
//                    "values ('Shirley', 'shirley@stefanini.com', 'RT', '123')");
//            query.executeUpdate("insert into users (name, email, role, password) " +
//                    "values ('Ezequiel', 'ezequiel@stefanini.com', 'Gerente', '123')");
//        }
//
//        return conexao;
//    }
}
