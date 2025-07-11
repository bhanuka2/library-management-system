package edu.icet.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T,ISBN> extends SuperRepository{
    boolean add(T entity) throws SQLException;
    boolean update(T entity);
    boolean deleteById(ISBN id);
    T searchById(ISBN id) throws SQLException;
    List<T> getAll() throws SQLException;
}
