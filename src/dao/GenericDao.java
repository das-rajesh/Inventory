/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public interface GenericDao<T> {

    List<T> getAll() throws ClassNotFoundException, SQLException;

    void save(T model) throws ClassNotFoundException, SQLException;

    int delete(int id) throws ClassNotFoundException, SQLException;

    T getById(int id) throws ClassNotFoundException, SQLException;

}
