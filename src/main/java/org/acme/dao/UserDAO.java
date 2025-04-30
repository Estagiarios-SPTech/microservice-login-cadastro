package org.acme.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class UserDAO {
    @Inject
    Conexao conexao;

    public User insert(User user){
        try(Connection conectar = conexao.conectarBanco() ){
            PreparedStatement query = conectar.prepareStatement("insert into users (name, email, role, password) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            query.setString(1, user.getName());
            query.setString(2, user.getEmail());
            query.setString(3, user.getRole());
            query.setString(4, user.getPassword());
            query.executeUpdate();

            ResultSet chavePrimariaCriada = query.getGeneratedKeys();
            while(chavePrimariaCriada.next()){
                return new User(chavePrimariaCriada.getInt(1),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getPassword());
            }

        }
        catch (SQLException e){
            throw new RuntimeException("Insercao mal-sucedida\n" + e.getMessage());
        }
        return null;
    }
}