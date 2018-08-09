/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constant.db;

import constant.DbConstant;
import constant.SqlConstant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class JdbcTemplate<T> {

    public List<T> query(String sql, RowMapper<T> mapper) throws ClassNotFoundException, SQLException {

        List<T> rows = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DbConstant.DB_URL, DbConstant.USER_NAME, DbConstant.PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(SqlConstant.GET_ALL);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            rows.add(mapper.mapRow(rs));
        }
        return rows;

    }

    public int excute(String sql, Object[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DbConstant.DB_URL, DbConstant.USER_NAME, DbConstant.PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(SqlConstant.GET_ALL);

        int index = 1;
        for (Object obj : args) {
            stmt.setObject(index, obj);
            index++;
        }
        int result = stmt.executeUpdate();
        conn.close();
        return result;
    }

    public T queryForObject(String sql, Object[] args, RowMapper<T> mapper) throws ClassNotFoundException, SQLException {
        T row = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DbConstant.DB_URL, DbConstant.USER_NAME, DbConstant.PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(SqlConstant.GET_ALL);

        int index = 1;
        for (Object obj : args) {
            stmt.setObject(index, obj);
            index++;
        }
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            row = mapper.mapRow(rs);
        }
        return row;
    }

}
