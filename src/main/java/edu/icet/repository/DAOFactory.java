package edu.icet.repository;

import edu.icet.repository.custom.impl.BookRepositoryimpl;
import edu.icet.repository.custom.impl.IssueRepositoryimpl;
import edu.icet.repository.custom.impl.UserRepositoryimpl;
import edu.icet.util.RepositoryType;

public class DAOFactory {
    private static DAOFactory instance;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance == null ? instance = new DAOFactory() : instance;
    }

    public <T extends SuperRepository> T getRepositoryType(RepositoryType type) {
        switch (type) {
            case Book:
                return (T) new BookRepositoryimpl();
            case IssueRecord:
                return (T) new IssueRepositoryimpl();
            case User:
                return (T) new UserRepositoryimpl();
            default:
                return null;
        }
    }
}