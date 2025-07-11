package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String ISBN;
    private String Title;
    private String Author;
    private String Category;
    private Integer Quantity;

    public Book(String isbn, String title, String author, int quantity, String category) {
    }

//    public Book(String id, String name, String address, double salary) {
//    }
}
