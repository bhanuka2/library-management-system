package edu.icet.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {

    private Integer UserID;
    private String Name;
    private String Contact_Information;
    private String Membership_Date;
}
