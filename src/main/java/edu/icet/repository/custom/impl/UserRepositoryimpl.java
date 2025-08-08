package edu.icet.repository.custom.impl;

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

public class UserRepositoryimpl implements UserRepository {


    @Override
    public boolean add(UserEntity entity) throws SQLException {
        try {
            CrudUtil.execute("INSERT INTO user VALUES(?,?,?,?)",
                    entity.getUserID(),
                    entity.getName(),
                    entity.getContact_Information(),
                    entity.getMembership_Date()
            );
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean update(UserEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public UserEntity searchById(Integer id) {
        return null;
    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM user");
        List<UserEntity> userList = new ArrayList<>();
        while (resultSet.next()) {
            UserEntity user = new UserEntity();
            user.setUserID(Integer.valueOf(resultSet.getString("userID")));
            user.setName(resultSet.getString("name"));
            user.setContact_Information(resultSet.getString("contact_Information"));
            user.setMembership_Date(resultSet.getDate("membership_Date"));
            userList.add(user);
        }
        return userList;
    }
}
