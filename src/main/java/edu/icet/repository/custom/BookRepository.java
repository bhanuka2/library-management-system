package edu.icet.repository.custom;

import edu.icet.model.entity.BookEntity;
import edu.icet.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity , String> {
}
