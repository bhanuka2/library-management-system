package edu.icet.repository;

import java.util.List;

public interface CrudRepository<T,ISBN> extends SuperRepository{
    boolean add(T entity);
    boolean update(T entity);
    boolean deleteById(ISBN id);
    T searchById(ISBN id);
    List<T> getAll();
}
