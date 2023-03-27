package dbbinding;

import entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbEntityBinding<T extends Entity> {
    String getTableName();
    List<String> getColumns();

    T build(ResultSet rs) throws SQLException, DbBindingException;
    void bind(PreparedStatement statement, T entity) throws SQLException, DbBindingException;

    String getIdentityColumn();
    void bindIdentityData(PreparedStatement statement, T entity) throws  SQLException;
}
