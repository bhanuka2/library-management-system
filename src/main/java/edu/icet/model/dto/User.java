package edu.icet.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer UserID;
    private String Name;
    private String Contact_Information;
    private Date Membership_Date;

    //public User(int userid, String name, String contactInformation, String membershipDate) {
   // }
}
