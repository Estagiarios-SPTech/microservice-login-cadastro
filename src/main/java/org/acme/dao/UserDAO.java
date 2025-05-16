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
                user.setId(chavePrimariaCriada.getInt(1));
                return user;
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Insercao mal-sucedida");
        }
        return null;
    }

    public User findById(Integer id){
        try(Connection conectar = conexao.conectarBanco()){
            PreparedStatement query = conectar.prepareStatement("select * from users where id = ?");
            query.setInt(1, id);
            ResultSet resultado = query.executeQuery();
            while(resultado.next()){
                return new User(resultado.getInt("id"),
                        resultado.getString("name"),
                        resultado.getString("email"),
                        resultado.getString("role"),
                        resultado.getString("password"));
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Erro ao procurar o usuário");
        }
        return null;
    }

    public User autenticarLogin(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = conexao.conectarBanco();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("password"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar login: " + e.getMessage(), e);
        }
        return null;
    }
}