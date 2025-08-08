package edu.icet.service.custom.impl;

import edu.icet.model.dto.User;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.DAOFactory;
import edu.icet.repository.custom.UserRepository;
import edu.icet.service.custom.UserService;
import edu.icet.util.CrudUtil;
import edu.icet.util.RepositoryType;
import org.modelmapper.ModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceimpl implements UserService {

    UserRepository userRepository = DAOFactory.getInstance().getRepositoryType(RepositoryType.User);


    @Override
    public Boolean addUser(User user) throws SQLException {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        return userRepository.add(entity);
    }

    @Override
    public Boolean updateUser(User user) throws SQLException {
        return null;
    }

    @Override
    public Boolean deleteUser(String userId) throws SQLException {
        try {
            return CrudUtil.execute("DELETE FROM user WHERE userID=?", userId);
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public User searchById(String user) throws SQLException {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE userID=?", user);
            if (resultSet.next()) {
                User userObj = new User();
                userObj.setUserID(Integer.valueOf(resultSet.getString("UserID")));
                userObj.setName(resultSet.getString("Name"));
                userObj.setContact_Information(resultSet.getString("Contact_Information"));
                userObj.setMembership_Date(resultSet.getDate("Membership_Date"));
                return userObj;
            }
        } catch (SQLException e) {
            System.err.println("Error searching for user: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<UserEntity> entities = userRepository.getAll();
        return entities.stream()
                .map(entity -> new ModelMapper().map(entity, User.class))
                .collect(Collectors.toList());
    }
}
