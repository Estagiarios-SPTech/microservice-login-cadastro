package org.acme.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.Collaborator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class CollaboratorDAO {
    @Inject
    Conexao conexao;

    public Collaborator insert(Collaborator collaborator){
        try(Connection conectar = conexao.conectarBanco()){
            PreparedStatement query = conectar.prepareStatement("Insert into employees (user, rt, manager) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            query.setObject(1, collaborator.getCollaborator().getId());
            query.setObject(2, collaborator.getRt().getId());
            query.setObject(3, collaborator.getManager().getId());
            query.executeUpdate();

            ResultSet chavePrimariaGerada = query.getGeneratedKeys();
            while(chavePrimariaGerada.next()){
                return new Collaborator(chavePrimariaGerada.getInt(1),
                        collaborator.getCollaborator(),
                        collaborator.getRt(),
                        collaborator.getManager());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
