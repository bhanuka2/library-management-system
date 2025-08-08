package edu.icet.repository.custom.impl;

import edu.icet.model.entity.IssueRecordEntity;
import edu.icet.repository.custom.IssuerecordRepository;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IssueRepositoryimpl implements IssuerecordRepository {
    @Override
    public boolean add(IssueRecordEntity entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO issue_table VALUES(?,?,?,?,?,?)",
                entity.getRecordID(),
                entity.getUserID(),
                entity.getBookID(),
                entity.getBorrowDate(),
                entity.getReturnDate(),
                entity.getFine()
        );
    }

    @Override
    public boolean update(IssueRecordEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public IssueRecordEntity searchById(Integer id) {
        return null;
    }

    @Override
    public List<IssueRecordEntity> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from issue_table");
        List<IssueRecordEntity> issueRecords = new ArrayList<>();
        while (resultSet.next()) {
            IssueRecordEntity issueRecord = new IssueRecordEntity();

            issueRecord.setRecordID(resultSet.getInt("RecordID"));
            issueRecord.setUserID(resultSet.getInt("UserID"));
            issueRecord.setBookID(resultSet.getString("BookID"));
            issueRecord.setBorrowDate(resultSet.getDate("BorrowDate"));
            issueRecord.setReturnDate(resultSet.getDate("ReturnDate"));
            issueRecord.setFine(resultSet.getDouble("Fine"));

            issueRecords.add(issueRecord);
        }
        return issueRecords;
    }
}
