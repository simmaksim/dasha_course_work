package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeBinding implements DbEntityBinding<Employee> {
    @Override
    public String getTableName() {
        return "employees";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.LOGIN, DbAttributeName.PASSWORD);
    }

    @Override
    public Employee build(ResultSet rs) throws SQLException, DbBindingException {
        Employee employee = new Employee();

        employee.setId(rs.getInt(DbAttributeName.ID));
        employee.setLogin(rs.getString(DbAttributeName.LOGIN));
        employee.setPassword(rs.getString(DbAttributeName.PASSWORD));

        return employee;
    }

    @Override
    public void bind(PreparedStatement statement, Employee entity) throws SQLException, DbBindingException {
        int index = 0;

        statement.setString(++index, entity.getLogin());
        statement.setString(++index, entity.getPassword());

        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        return DbAttributeName.LOGIN;
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Employee entity) throws SQLException {
        statement.setString(1, entity.getLogin());
    }
}
