package edu.icet.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IssueRecordEntity {

    private Integer RecordID;
    private Integer UserID;
    private String BookID;
    private Date BorrowDate;
    private Date ReturnDate;
    private Double Fine;
}
