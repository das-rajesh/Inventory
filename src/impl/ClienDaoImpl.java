/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import constant.DbConstant;
import constant.SqlConstant;
import dao.ClientDao;
import entity.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ClienDaoImpl implements ClientDao{

    @Override
    public List<Client> getAll() throws ClassNotFoundException, SQLException {
        List<Client> clients=new ArrayList<>();
        Class.forName(DbConstant.DRIVER_NAME);
          Connection conn=DriverManager.getConnection(DbConstant.DB_URL,DbConstant.USER_NAME,DbConstant.PASSWORD);
          PreparedStatement stmt=conn.prepareStatement(SqlConstant.GET_ALL);
          System.out.println(SqlConstant.GET_ALL);
          ResultSet rs=stmt.executeQuery();
          
          while(rs.next()){
              Client client=new Client();
              client.setId(rs.getInt("id"));
              client.setName(rs.getString("client_name"));
              client.setEmail(rs.getString("email"));
              client.setAddedDate(rs.getDate("added_date"));
              client.setModifiedDate(rs.getDate("modified_date"));
              client.setStatus(rs.getBoolean("status"));
              clients.add(client);
          }
          conn.close();
          return clients;
    }
    
}
