package edu.icet.model.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookEntity {

    private String ISBN;
    private String Title;
    private String Author;
    private String Category;
    private Integer Quantity;

}
