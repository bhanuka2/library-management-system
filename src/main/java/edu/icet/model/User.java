package edu.icet.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer UserID;
    private String Name;
    private String Contact_Information;
    private String Membership_Date;

    public User(int userid, String name, String contactInformation, String membershipDate) {
    }
}
