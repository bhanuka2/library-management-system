package edu.icet.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IssueRecordEntity {

    private Integer RecordID;
    private Integer UserID;
    private String BookID;
    private String BorrowDate;
    private String ReturnDate;
    private String Fine;
}
