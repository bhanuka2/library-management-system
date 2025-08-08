package edu.icet.service.custom;

import edu.icet.model.dto.User;
import edu.icet.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends SuperService {
    Boolean addUser(User user) throws SQLException;
    Boolean updateUser(User user) throws SQLException;
    Boolean deleteUser(String userId) throws SQLException;
    User searchById(String user) throws SQLException;
    List<User> getAll() throws SQLException;
}
