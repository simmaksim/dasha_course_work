package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.CarMake;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarMakeBindingCommand implements DbEntityBinding<CarMake> {
    @Override
    public String getTableName() {
        return "car_makes";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.CAR_MAKE_NAME);
    }

    @Override
    public CarMake build(ResultSet rs) throws SQLException, DbBindingException {
        CarMake make = new CarMake();

        make.setId(rs.getInt(DbAttributeName.ID));
        make.setName(rs.getString(DbAttributeName.CAR_MAKE_NAME));

        return make;
    }

    @Override
    public void bind(PreparedStatement statement, CarMake entity) throws SQLException, DbBindingException {
        statement.setString(1, entity.getName());
    }

    @Override
    public String getIdentityColumn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, CarMake entity) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
