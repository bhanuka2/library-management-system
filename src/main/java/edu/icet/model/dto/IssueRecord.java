package edu.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRecord {

    private Integer RecordID;
    private Integer UserID;
    private String BookID;
    private Date BorrowDate;
    private Date ReturnDate;
    private Double Fine;
}
