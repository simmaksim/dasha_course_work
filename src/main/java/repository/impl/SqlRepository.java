package repository.impl;

import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Entity;
import repository.ConnectionManager;
import repository.Repository;
import repository.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlRepository<T extends Entity> implements Repository<T> {
    private Connection connection;
    private DbEntityBinding<T> binding;

    public SqlRepository(DbEntityBinding<T> binding) {
        this.binding = binding;
        connection = ConnectionManager.INSTANCE.getConnection();
    }

    @Override
    public int getEntityId(T entity) throws RepositoryException {
        try {
            PreparedStatement statement = connection.prepareStatement(getEntityIdQuery());
            binding.bindIdentityData(statement, entity);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new RepositoryException();
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public T getEntityById(int id) throws RepositoryException {
        try {
            PreparedStatement statement = connection.prepareStatement(getGetEntityQuery());
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return binding.build(rs);
            } else {
                throw new RepositoryException();
            }
        } catch (SQLException | DbBindingException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public List<T> getAllEntities() throws RepositoryException {
        List<T> entities = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(getGetAllEntitiesQuery());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                entities.add(binding.build(rs));
            }
        } catch (SQLException | DbBindingException e) {
            throw new RepositoryException(e);
        }

        return entities;
    }

    @Override
    public void addEntity(T entity) throws RepositoryException {
        try {
            PreparedStatement statement = connection.prepareStatement(getAddEntityQuery());
            binding.bind(statement, entity);
            statement.execute();
        } catch (SQLException | DbBindingException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updateEntity(T entity) throws RepositoryException {
        try {
            PreparedStatement statement = connection.prepareStatement(getUpdateEntityQuery());
            binding.bind(statement, entity);
            statement.setInt(binding.getColumns().size() + 1, entity.getId());
            statement.execute();
        } catch (SQLException | DbBindingException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void removeEntity(T entity) throws RepositoryException {
        try {
            PreparedStatement statement = connection.prepareStatement(getRemoveEntityQuery());
            statement.setInt(1, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private String getEntityIdQuery() {
        return "SELECT id FROM " + binding.getTableName() + " WHERE " + binding.getIdentityColumn() + "=?";
    }

    private String getGetEntityQuery() {
        return "SELECT * FROM " + binding.getTableName() + " WHERE id=?";
    }

    private String getGetAllEntitiesQuery() {
        return "SELECT * FROM " + binding.getTableName() + " ORDER BY id ASC";
    }

    private String getRemoveEntityQuery() {
        return "DELETE FROM " + binding.getTableName() + " WHERE id=?";
    }

    private String getAddEntityQuery() {
        StringBuilder builder = new StringBuilder("INSERT INTO ")
                .append(binding.getTableName())
                .append(" VALUES(NULL, ");

        for (int i = 0; i < binding.getColumns().size(); i++) {
            if (i == binding.getColumns().size() - 1) {
                builder.append("?)");
            } else {
                builder.append("?, ");
            }
        }

        return builder.toString();
    }

    private String getUpdateEntityQuery() {
        StringBuilder builder = new StringBuilder("UPDATE ")
                .append(binding.getTableName())
                .append(" SET ");

        for (int i = 0; i < binding.getColumns().size(); i++) {
            builder.append(binding.getColumns().get(i)).append("=?");

            if (i != binding.getColumns().size() - 1) {
                builder.append(", ");
            }
        }

        builder.append(" WHERE id=?");

        return builder.toString();
    }
}
