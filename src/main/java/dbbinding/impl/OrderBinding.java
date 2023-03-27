package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderBinding implements DbEntityBinding<Order> {
    @Override
    public String getTableName() {
        return "orders";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.CLIENT_ID, DbAttributeName.CAR_ID,
                DbAttributeName.START_DATE, DbAttributeName.END_DATE);
    }

    @Override
    public Order build(ResultSet rs) throws SQLException, DbBindingException {
        Order order = new Order();

        order.setId(rs.getInt(DbAttributeName.ID));
        order.setClientId(rs.getInt(DbAttributeName.CLIENT_ID));
        order.setCarId(rs.getInt(DbAttributeName.CAR_ID));
        order.setStartDate(rs.getDate(DbAttributeName.START_DATE).toLocalDate());
        order.setEndDate(rs.getDate(DbAttributeName.END_DATE).toLocalDate());

        return order;
    }

    @Override
    public void bind(PreparedStatement statement, Order entity) throws SQLException, DbBindingException {
        int index = 0;

        statement.setInt(++index, entity.getClientId());
        statement.setInt(++index, entity.getCarId());
        statement.setDate(++index, Date.valueOf(entity.getStartDate()));
        statement.setDate(++index, Date.valueOf(entity.getEndDate()));

        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Order entity) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
