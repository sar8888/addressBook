package com.example.addressbook.DAO;

import com.example.addressbook.DTO.AddressDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j2
public class AddressDAO {
    public static AddressDAO instance;

    private AddressDAO() {

    }

    public static AddressDAO getInstance() {
        if(instance == null) {
            instance = new AddressDAO();
        }
        return instance;
    }

    public boolean insertDB(AddressDTO addressDTO) throws SQLException, ClassNotFoundException {
       String query = "INSERT INTO addrbook VALUES (null, ?, ?, ?, ?, ?, ?)";
       int result;

       @Cleanup Connection connection = DBConnection.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);

       connection = DBConnection.getConnection();
       preparedStatement = connection.prepareStatement(query);
       preparedStatement.setString(1, addressDTO.getAb_name());
       preparedStatement.setString(2, addressDTO.getAb_email());
       preparedStatement.setString(3, addressDTO.getAb_comdept());
       preparedStatement.setString(4, addressDTO.getAb_birth());
       preparedStatement.setString(5, addressDTO.getAb_tel());
       preparedStatement.setString(6, addressDTO.getAb_memo());
       result = preparedStatement.executeUpdate();

       return result == 1;
    }

    public AddressDTO getAddressOne(int ab_id) throws SQLException, ClassNotFoundException {
        log.info("getAddressOne()...");

        AddressDTO addressDTO = null;
        String query = "SELECT * FROM addrbook WHERE ab_id = ?";
        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ab_id);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            addressDTO = new AddressDTO();
            addressDTO.setAb_name(resultSet.getString("ab_name"));
            addressDTO.setAb_email(resultSet.getString("ab_email"));
            addressDTO.setAb_comdept(resultSet.getString("ab_comdept"));
            addressDTO.setAb_birth(resultSet.getString("ab_birth"));
            addressDTO.setAb_tel(resultSet.getString("ab_tel"));
            addressDTO.setAb_memo(resultSet.getString("ab_memo"));
        }
        return addressDTO;
    }

    public ArrayList<AddressDTO> getList() throws SQLException, ClassNotFoundException {
        log.info("getList()...");
        String query = "SELECT * FROM addrbook";

        ArrayList<AddressDTO> list = new ArrayList<AddressDTO>();

        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        //result.absolute() : 특정 위치로 이동
        while (resultSet.next()) {
            AddressDTO dto = new AddressDTO();
            dto.setAb_name(resultSet.getString("ab_name"));
            dto.setAb_email(resultSet.getString("ab_email"));
            dto.setAb_comdept(resultSet.getString("ab_comdept"));
            dto.setAb_birth(resultSet.getString("ab_birth"));
            dto.setAb_tel(resultSet.getString("ab_tel"));
            dto.setAb_memo(resultSet.getString("ab_memo"));
            log.info(dto); //ToString()필요
            list.add(dto);
        }
        return list;
    }

    public boolean modifyAddress(AddressDTO addressDTO) throws Exception {
        log.info("updateAddress()...");
        String query = "UPDATE addrbook SET ab_name=?, ab_email=?, ab_comdept, ab_birth, ab_tel, ab_memo WHERE ab_id=?";

        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);

        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, addressDTO.getAb_name());
        preparedStatement.setString(2, addressDTO.getAb_email());
        preparedStatement.setString(3, addressDTO.getAb_comdept());
        preparedStatement.setString(4, addressDTO.getAb_birth());
        preparedStatement.setString(5, addressDTO.getAb_tel());
        preparedStatement.setString(6, addressDTO.getAb_memo());
        preparedStatement.setInt(7, addressDTO.getAb_id());
        return preparedStatement.executeUpdate() == 1;
    }

    public boolean removeAction(int ab_id) throws SQLException, ClassNotFoundException{
        log.info("removeAction()...");
        String query = "DELETE FROM addrbook WHERE ab_id=?";
        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ab_id);
        int result = preparedStatement.executeUpdate();
        return result == 1;

    }


}