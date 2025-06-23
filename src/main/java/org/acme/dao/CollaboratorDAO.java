package org.acme.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class CollaboratorDAO {
    @Inject
    Conexao conexao;

    public Employee insert(Employee collaborator){
        try{
            Connection conectar = conexao.conectarBanco();
            PreparedStatement query = conectar.prepareStatement("insert into employee (`user`, rt, manager) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            query.setObject(1, collaborator.getUser().getId());
            query.setObject(2, collaborator.getRt().getId());
            query.setObject(3, collaborator.getManager().getId());
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
}
