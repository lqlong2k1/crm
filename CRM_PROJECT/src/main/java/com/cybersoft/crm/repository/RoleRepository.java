package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> getRoles() {
        List<RoleModel> list = new ArrayList<>();
        try {
            String query = "select*from roles";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoleModel roleModel = new RoleModel();
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));

                list.add(roleModel);
            }

            connection.close();

        } catch (Exception e) {
            System.out.println("Error getRoles " + e.getMessage());
        }

        return list;
    }

    public int delateRoleById(int id) {
        int result =0;
        try {
            String query = "delete from roles where id=?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            result = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error getRoles " + e.getMessage());
        }
        return result;
    }
}
