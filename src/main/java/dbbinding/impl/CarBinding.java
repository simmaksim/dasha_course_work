package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarBinding implements DbEntityBinding<Car> {
    @Override
    public String getTableName() {
        return "cars";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.YEAR, DbAttributeName.MAKE_ID, DbAttributeName.MODEL,
                DbAttributeName.REGISTRATION_NUMBER, DbAttributeName.MILEAGE, DbAttributeName.GEARBOX_TYPE,
                DbAttributeName.PRICE_PER_DAY, DbAttributeName.PRICE_PER_DAY2);
    }

    @Override
    public Car build(ResultSet rs) throws SQLException, DbBindingException {
        Car car = new Car();

        car.setId(rs.getInt(DbAttributeName.ID));
        car.setYear(rs.getInt(DbAttributeName.YEAR));
        car.setMakeId(rs.getInt(DbAttributeName.MAKE_ID));
        car.setModel(rs.getString(DbAttributeName.MODEL));
        car.setRegistrationNumber(rs.getString(DbAttributeName.REGISTRATION_NUMBER));
        car.setMileage(rs.getInt(DbAttributeName.MILEAGE));
        car.setGearboxType(rs.getInt(DbAttributeName.GEARBOX_TYPE));
        car.setPricePerDay(rs.getInt(DbAttributeName.PRICE_PER_DAY));
        car.setPricePerDay2(rs.getInt(DbAttributeName.PRICE_PER_DAY2));

        return car;
    }

    @Override
    public void bind(PreparedStatement statement, Car entity) throws SQLException, DbBindingException {
        int index = 0;

        statement.setInt(++index, entity.getYear());
        statement.setInt(++index, entity.getMakeId());
        statement.setString(++index, entity.getModel());
        statement.setString(++index, entity.getRegistrationNumber());
        statement.setInt(++index, entity.getMileage());
        statement.setInt(++index, entity.getGearboxType());
        statement.setFloat(++index, entity.getPricePerDay());
        statement.setFloat(++index, entity.getPricePerDay2());

        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Car entity) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
