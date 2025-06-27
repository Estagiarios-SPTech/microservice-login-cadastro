package org.acme.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.Employee;
import org.acme.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CollaboratorDAO {
    @Inject
    Conexao conexao;

    public Employee insert(Employee collaborator){
        try{
            Connection conectar = conexao.conectarBanco();
            PreparedStatement query = conectar.prepareStatement("insert into employee (`user`, rt, manager, `status`) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            query.setObject(1, collaborator.getUser().getId());
            query.setObject(2, collaborator.getRt().getId());
            query.setObject(3, collaborator.getManager().getId());
            query.setObject(4, collaborator.getStatus());
            query.executeUpdate();

            ResultSet chavePrimariaGerada = query.getGeneratedKeys();
            if(chavePrimariaGerada.next()){
                collaborator.setId(chavePrimariaGerada.getInt(1));
                return collaborator;
            }

            conectar.close();
        } catch (SQLException e) {
            throw new RuntimeException("Insercao mal-sucedida");
        }
        return null;
    }

    public List<Employee> mostrarColaboradoresRelacionados(Integer id){
        List<Employee> employees = new ArrayList<>();
        try{
            Connection conectar = conexao.conectarBanco();
            PreparedStatement query = conectar.prepareStatement(
                    "select `user`.id,`name`, email, `status` from `user`\n" +
                            "join employee on `user` = `user`.id where rt = ?;"
            );
            query.setInt(1, id);
            ResultSet resultado = query.executeQuery();
            while(resultado.next()){
                User user = new User();
                user.setId(resultado.getInt(1));
                user.setName(resultado.getString(2));
                user.setEmail(resultado.getString(3));

                Employee employee = new Employee();
                employee.setUser(user);
                employee.setStatus(resultado.getString(4));
                employees.add(employee);
            }
            conectar.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
