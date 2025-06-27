package org.acme.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.exceptions.UserNotRegisteredException;
import org.acme.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class UserDAO {

    private final Conexao conexao;

    @Inject
    public UserDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Construtor sem argumentos para CDI
    public UserDAO() {
        this.conexao = null; // Será substituído pela injeção de dependência
    }
    public User insert(User user){
        try{
            Connection conectar = conexao.conectarBanco();
            PreparedStatement query = conectar.prepareStatement("insert into user (name, email, role, password) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            query.setString(1, user.getName());
            query.setString(2, user.getEmail());
            query.setString(3, user.getRole());
            query.setString(4, user.getPassword());
            query.executeUpdate();

            ResultSet chavePrimariaCriada = query.getGeneratedKeys();
            if(chavePrimariaCriada.next()){
                user.setId(chavePrimariaCriada.getInt(1));
                return user;
            }

            conectar.close();
        }
        catch (SQLException e){
            throw new RuntimeException("Insercao mal-sucedida");
        }
        return null;
    }

    public Boolean verificarEmailExistente(String email){
        try(Connection conectar = conexao.conectarBanco()){
            PreparedStatement query = conectar.prepareStatement("select count(email) from `user` where email = ?");
            query.setString(1, email);
            ResultSet resultado = query.executeQuery();
            resultado.next();
            return resultado.getInt("count(email)") > 0;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByName(String name){
        try{
            Connection conectar = conexao.conectarBanco();
            PreparedStatement query = conectar.prepareStatement("select * from user where name = ?");
            query.setString(1, name);
            ResultSet resultado = query.executeQuery();
            if(resultado.next()){
                return new User(resultado.getInt("id"),
                        resultado.getString("name"),
                        resultado.getString("email"),
                        resultado.getString("role"),
                        resultado.getString("password"));
            }

            conectar.close();
        }
        catch (SQLException e){
            throw new RuntimeException("Erro ao procurar o usuário");
        }
        return null;
    }

    public User autenticarLogin(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
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
            } else {
                System.out.println(email + "ola" + password);
                throw new UserNotRegisteredException("Usuário não encontrado");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar login: " + e.getMessage(), e);
        } catch (UserNotRegisteredException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}