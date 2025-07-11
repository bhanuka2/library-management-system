package edu.icet.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {

    private Integer UserID;
    private String Name;
    private String Contact_Information;
    private Date Membership_Date;
}
