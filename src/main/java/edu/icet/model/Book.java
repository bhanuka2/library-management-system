package edu.icet.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String ISBN;
    private String Author;
    private String Title;
    private String Category;
    private Integer Quantity;

    public Book(String id, String name, String address, double salary) {
    }
}
