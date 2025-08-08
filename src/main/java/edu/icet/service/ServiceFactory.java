package edu.icet.service;


import edu.icet.service.custom.impl.BookServiceImpl;
import edu.icet.service.custom.impl.ReturnRecordImpl;
import edu.icet.service.custom.impl.UserServiceimpl;
import edu.icet.util.ServiceType;

public class ServiceFactory {

    private static ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType type) {
        switch (type) {
            case Book:
                return (T) new BookServiceImpl();
            case IssueRecord:
                return (T) new ReturnRecordImpl();
            case User:
                return (T) new UserServiceimpl();
            default:
                return null;
        }
    }
}