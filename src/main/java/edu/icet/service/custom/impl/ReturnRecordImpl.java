package edu.icet.service.custom.impl;

import edu.icet.model.dto.IssueRecord;
import edu.icet.model.entity.IssueRecordEntity;
import edu.icet.repository.DAOFactory;
import edu.icet.repository.custom.IssuerecordRepository;
import edu.icet.service.custom.ReturnRecordService;
import edu.icet.util.RepositoryType;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ReturnRecordImpl implements ReturnRecordService {

    IssuerecordRepository issuerecordRepository= DAOFactory.getInstance().getRepositoryType(RepositoryType.IssueRecord);

    @Override
    public Boolean AddRecord(IssueRecord issueRecord) throws SQLException {
        IssueRecordEntity entity = new ModelMapper().map(issueRecord, IssueRecordEntity.class);
        return issuerecordRepository.add(entity);
    }

    @Override
    public List<IssueRecord> getAllRecords() throws SQLException {
        List<IssueRecordEntity> entities = issuerecordRepository.getAll();
        return entities.stream()
                .map(entity -> new ModelMapper().map(entity, IssueRecord.class))
                .collect(Collectors.toList());
    }


}