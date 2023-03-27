package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientBinding implements DbEntityBinding<Client> {
    @Override
    public String getTableName() {
        return "clients";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.NAME, DbAttributeName.SURNAME, DbAttributeName.PATRONYMIC,
                DbAttributeName.PASSPORT_ID, DbAttributeName.HOME_PHONE, DbAttributeName.MOBILE_PHONE,
                DbAttributeName.EMAIL, DbAttributeName.DISCOUNT_CARD_ID);
    }

    @Override
    public Client build(ResultSet rs) throws SQLException, DbBindingException {
        Client client = new Client();

        client.setId(rs.getInt(DbAttributeName.ID));
        client.setName(rs.getString(DbAttributeName.NAME));
        client.setSurname(rs.getString(DbAttributeName.SURNAME));
        client.setPatronymic(rs.getString(DbAttributeName.PATRONYMIC));
        client.setPassportId(rs.getString(DbAttributeName.PASSPORT_ID));
        client.setHomePhone(rs.getString(DbAttributeName.HOME_PHONE));
        client.setMobilePhone(rs.getString(DbAttributeName.MOBILE_PHONE));
        client.setEmail(rs.getString(DbAttributeName.EMAIL));
        client.setDiscountCardId(rs.getInt(DbAttributeName.DISCOUNT_CARD_ID));

        return client;
    }

    @Override
    public void bind(PreparedStatement statement, Client entity) throws SQLException, DbBindingException {
        int index = 0;

        statement.setString(++index, entity.getName());
        statement.setString(++index, entity.getSurname());
        statement.setString(++index, entity.getPatronymic());
        statement.setString(++index, entity.getPassportId());
        statement.setString(++index, entity.getHomePhone());
        statement.setString(++index, entity.getMobilePhone());
        statement.setString(++index, entity.getEmail());
        statement.setInt(++index, entity.getDiscountCardId());

        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        return DbAttributeName.PASSPORT_ID;
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Client entity) throws SQLException {
        statement.setString(1, entity.getPassportId());
    }
}
