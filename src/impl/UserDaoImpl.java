/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import constant.db.JdbcTemplate;
import constant.db.RowMapper;
import dao.UserDao;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDaoImpl implements UserDao {

    JdbcTemplate<User> template = new JdbcTemplate<>();

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {

        return template.query("select * from users", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {

                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        });
    }

    @Override
    public void save(User model) throws ClassNotFoundException, SQLException {

        template.excute("insert into user(username,password)"
                + "values(?,?)", new Object[]{model.getUserName(), model.getPassWord()});
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException {
     return template.excute("delete from user"
                + "where id=?", new Object[]{id});  
    
    }  

    @Override
    public User getById(int id) throws ClassNotFoundException, SQLException {

        return template.queryForObject("select * from user where id=?", new Object[]{id},new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        });
    }
    
    public void update(User model) throws ClassNotFoundException,SQLException{
             template.excute("update  user username=? ,password=?"
                + "where id=?", new Object[]{model.getUserName(), model.getPassWord(),model.getId()});
    }

}
