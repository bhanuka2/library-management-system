package edu.icet.service.custom;

import edu.icet.model.dto.IssueRecord;
import edu.icet.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ReturnRecordService extends SuperService {
     Boolean AddRecord(IssueRecord issueRecord ) throws SQLException;
     List<IssueRecord>getAllRecords() throws SQLException;

}
